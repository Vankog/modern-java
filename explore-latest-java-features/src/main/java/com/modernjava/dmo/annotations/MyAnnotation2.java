package com.modernjava.dmo.annotations;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
@Repeatable(MyAnnotation2.Container.class)
@interface MyAnnotation2 {

    int myInt();


    String[] array();

    String TEST_CONST = "FOOBAR";

    @Retention(value = RetentionPolicy.RUNTIME)
    @interface Container {
        MyAnnotation2[] value();

    }
}
