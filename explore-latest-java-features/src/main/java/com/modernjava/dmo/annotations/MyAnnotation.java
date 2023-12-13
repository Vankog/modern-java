package com.modernjava.dmo.annotations;


import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@interface MyAnnotation {

    int myInt() default 1;

    Deprecated value() default @Deprecated;

    String TEST_CONST = "asdf";
}
