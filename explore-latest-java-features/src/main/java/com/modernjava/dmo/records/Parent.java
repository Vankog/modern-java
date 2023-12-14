package com.modernjava.dmo.records;

import java.util.UUID;

interface Parent {
    default String uuid() {
        return UUID.randomUUID().toString();
    }
}
