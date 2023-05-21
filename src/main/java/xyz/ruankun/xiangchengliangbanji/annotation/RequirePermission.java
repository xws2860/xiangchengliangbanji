package xyz.ruankun.xiangchengliangbanji.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否需要权限验证
 * 将这个注解加在controller的方法上, 方法就需要权限验证了
 * 拦截参考aspect中的内容
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequirePermission {
    String value() default "";
}

