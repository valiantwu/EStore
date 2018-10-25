package org.woo.dataentity.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface GeneratorField {
    /**
     * 是否为主键
     *
     * @return
     */
    boolean primaryKey() default false;

    /**
     * 映射的字段名
     *
     * @return
     */
    public String name() default "";
}
