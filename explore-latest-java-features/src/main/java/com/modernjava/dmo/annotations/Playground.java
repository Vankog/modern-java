package com.modernjava.dmo.annotations;

import java.util.Arrays;

@MyAnnotation2(myInt = 1, array = {"singletonArray"})
@MyAnnotation2(myInt = 2, array = "singletonArray")
@MyAnnotation(@Deprecated) //value()
class Playground {


    public static void main(String... args) {
        System.out.println(MyAnnotation.TEST_CONST);
        MyAnnotation2 myAnonymousAnnotation = new MyAnnotation2() {

            @Override
            public Class<MyAnnotation> annotationType() {
                return MyAnnotation.class;
            }

            @Override
            public int myInt() {
                return -1;
            }

            @Override
            public String[] array() {
                return new String[]{"default"};
            }
        };
        System.out.println("anonymous.getClass().getInterfaces()[0].isAnnotation: " + myAnonymousAnnotation.getClass().getInterfaces()[0].isAnnotation());
        System.out.println("MyAnnotation.class.isAnnotation" + MyAnnotation.class.isAnnotation());

        var playground = new Playground();
        playground.doIt();

        var child = new Child();
        child.doIt();
    }

    protected void doIt() {
        Class<? extends Playground> clazz = this.getClass();
        System.out.println("Class " + clazz);
        System.out.println("##########");
        System.out.println("getAnnotations: " + Arrays.toString(clazz.getAnnotations()));
        System.out.println("getDeclaredAnnotations: " + Arrays.toString(clazz.getDeclaredAnnotations()));
        System.out.println("getAnnotatedInterfaces: " + Arrays.toString(clazz.getAnnotatedInterfaces()));
        System.out.println("getAnnotationsByType: " + Arrays.toString(clazz.getAnnotationsByType(MyAnnotation2.class)));
        System.out.println("getDeclaredAnnotationsByType: " + Arrays.toString(clazz.getDeclaredAnnotationsByType(MyAnnotation2.class)));
        System.out.println("getAnnotatedSuperclass: " + clazz.getAnnotatedSuperclass());
        System.out.println("getDeclaredAnnotation: " + clazz.getDeclaredAnnotation(MyAnnotation2.class));
        System.out.println("isAnnotationPresent: " + clazz.isAnnotationPresent(MyAnnotation2.class));
        System.out.println("getAnnotation: " + clazz.getAnnotation(MyAnnotation2.class));
        //System.out.println("myInt: " + clazz.getAnnotation(MyAnnotation2.class).myInt());
        System.out.println("===========");

    }

}
