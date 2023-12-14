package com.modernjava.dmo.records;

record ChildRecord(String id, int myint) implements Parent {

    ChildRecord {
        // this(); canonical constructor cannot delegate
        if (id == null) {
            id = uuid();
        }
    }

    ChildRecord(String id) {
        // System.out.println("CANNOT do something before this(...)");
        this(id, calcParam(id)); // except via inline static methods
        System.out.println("can do something after this(...)");
        //this.myint = -99; // can't set components
    }

    private static int calcParam(String id) {
        return Integer.valueOf(id);
    }
}
