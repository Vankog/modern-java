package com.modernjava.dmo.lambda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;

final class Playground {


    public static void main(final String... args) {
        var playground = new Playground();
        playground.run(BigDecimal.TEN);
    }

    private <T extends Number> void run(T input) {
        // closure ref
        // multiple params
        var name = "Hello";
        BiPredicate<String, Integer> startsClosure = (s, i) -> name.startsWith(s, i);
        BiPredicate<String, Integer> startsClosureMR = name::startsWith;
        System.out.println(startsClosure.test("hel", 0));
        System.out.println(startsClosureMR.test("hel", 0));

        // static ref
        UnaryOperator<String> format = (s) -> String.format(s);
        UnaryOperator<String> formatMR = String::format;
        System.out.println(format.apply("formatted"));
        System.out.println(formatMR.apply("formatted"));

        // instance ref via Type
        BiPredicate<String, String> startsParam = (s, startsWith) -> s.startsWith(startsWith);
        BiPredicate<String, String> startsParamMR = String::startsWith;
        System.out.println(startsParam.test("ello", "el"));
        System.out.println(startsParamMR.test("ello", "el"));

        // instance ref via generic
        Function<T, Integer> generic = i -> i.intValue();
        Function<T, Integer> genericMR = T::intValue;
        System.out.println(generic.apply(input));
        System.out.println(genericMR.apply(input));

        // Constructor ref
        IntFunction<List<?>> constr = (i) -> new ArrayList<>(i);
        IntFunction<List<?>> constrMR = ArrayList::new;
        System.out.println(constr.apply(5));
        System.out.println(constrMR.apply(5));

    }


}
