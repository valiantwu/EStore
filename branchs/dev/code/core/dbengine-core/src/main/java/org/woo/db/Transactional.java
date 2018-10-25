package org.woo.db;

public @interface Transactional {
    PropagationEnum value() default PropagationEnum.Required;
}
