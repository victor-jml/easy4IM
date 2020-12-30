package pers.enoch.im.api.aspect;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.enoch.im.api.annotation.AccessLimit;
import pers.enoch.im.common.exception.IMException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Author yang.zhao
 * Date: 2020/12/23
 * Description: 限流切面
 **/
@Slf4j
public class AccessLimitAspect extends AbstractAspectManager {
    public AccessLimitAspect(AspectApi aspectApi){
        super(aspectApi);
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        super.doHandlerAspect(pjp,method);
        execute(pjp,method);
        return null;
    }

    /**
     * 添加速率.保证是单例的
     */
    private static RateLimiter rateLimiter = RateLimiter.create(1000);
    /**
     * 使用url做为key,存放令牌桶 防止每次重新创建令牌桶
     */
    private static Map<String,RateLimiter> limiterMap = Maps.newConcurrentMap();

    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        AccessLimit lxRateLimit = method.getAnnotation(AccessLimit.class);
        // 获取Request中的请求地址
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURI();
        if(!limiterMap.containsKey(url)){
            // 没有访问过则创建
            rateLimiter = RateLimiter.create(lxRateLimit.perSecond());
            limiterMap.put(url,rateLimiter);
            log.info("<<=================  请求{},创建令牌桶,容量{} 成功!!!",url,lxRateLimit.perSecond());
        }
        rateLimiter = limiterMap.get(url);
        if(!rateLimiter.tryAcquire(lxRateLimit.timeOut(),lxRateLimit.timeOutUnit())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            log.info("Error ---时间:{},获取令牌失败.", sdf.format(new Date()));
            throw new IMException("服务器繁忙，请稍后再试!");
        }
        return null;
    }
}
