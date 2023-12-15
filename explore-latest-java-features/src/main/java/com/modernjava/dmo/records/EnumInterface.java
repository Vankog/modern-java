package com.modernjava.dmo.records;

sealed interface EnumInterface {

    enum Foo implements EnumInterface {
        BAR, BAZZ;

        void fooSysout(String prefix) {
            System.out.println("Foo " + prefix + this);
        }
    }

    enum Ding implements EnumInterface {
        DANG, DONG;

        void dingSysout(String prefix) {
            System.out.println("Ding " + prefix + this);
        }
    }
}
