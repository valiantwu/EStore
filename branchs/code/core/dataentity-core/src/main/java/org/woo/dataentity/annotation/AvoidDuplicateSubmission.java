package org.woo.dataentity.annotation;

/**
 * Created by Administrator on 2017/3/23.
 */
public @interface AvoidDuplicateSubmission {
    boolean needSaveToken() default false;
    boolean needRemoveToken() default false;
}
