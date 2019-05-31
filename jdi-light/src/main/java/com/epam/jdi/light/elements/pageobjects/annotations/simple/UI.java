package com.epam.jdi.light.elements.pageobjects.annotations.simple;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Repeatable(UIs.class)
public @interface UI {
    String value();
    String group() default "";
}
