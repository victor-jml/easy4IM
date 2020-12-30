package pers.enoch.im.api.annotation;

import java.lang.annotation.*;

/**
 * @Author yang.zhao
 * Date: 2020/12/24
 * Description: 日志注解,在Controller方法上加可以自动添加记录
 **/
@Target( { ElementType.METHOD } )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface Log {

    /**
     * 模块名称
     */
    String modelName() default "";

    /**
     * 操作
     */
    String action()default "";
    /**
     * 描述.
     */
    String description() default "";

}
