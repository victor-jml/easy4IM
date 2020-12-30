package pers.enoch.im.api.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @Author yang.zhao
 * Date: 2020/12/23
 * Description: 装饰者模式
 **/
public interface AspectApi {
    Object doHandlerAspect(ProceedingJoinPoint pjp, Method method)throws Throwable;
}
