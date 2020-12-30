package pers.enoch.im.api.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import pers.enoch.im.api.annotation.Log;
import pers.enoch.im.api.service.OperationLogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author yang.zhao
 * Date: 2020/12/24
 * Description: 记录日志切面
 **/
public class RecordLogAspect extends AbstractAspectManager{

    @Autowired
    private OperationLogService operationLogService;

    public RecordLogAspect(AspectApi aspectApi) {
        super(aspectApi);
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        super.doHandlerAspect(pjp, method);
        return execute(pjp, method);
    }

    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        Log log = method.getAnnotation(Log.class);
        String actionLog = null;
        StackTraceElement[] stackTrace = null;
        // 是否执行异常
        boolean isException = false;
        // 接收时间戳
        long endTime;
        // 开始时间戳
        long operationTime = System.currentTimeMillis();
        try {
            return pjp.proceed(pjp.getArgs());
        } catch ( Throwable throwable ) {
            isException = true;
            actionLog = throwable.getMessage();
            stackTrace = throwable.getStackTrace();
            throw throwable;
        } finally {
            // 日志处理
            logHandle( pjp , method , log , actionLog , operationTime , isException,stackTrace );
        }
    }

    private void logHandle(ProceedingJoinPoint joinPoint,Method method,Log log,String actionLog,Long startTime,boolean isException
    ,StackTraceElement[] stackTraceElements){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();

    }

    private  String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip+":"+request.getRemotePort();
    }
}
