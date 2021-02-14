package com.orange.verify.adminweb.config.annotation;

import java.lang.annotation.*;

/**
 * 接口拦截注解
 * @author orange
 * @email 1067357662@qq.com
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiAuth {

    /**
     * 是否关闭接口访问
     */
    boolean close() default false;

    String type() default "";

    String[] ip() default {};

    String[] roles() default {};

    String[] permissions() default {};

    boolean wxIsVerifyUserPhone() default true;

}
