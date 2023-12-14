package com.modernjava.dmo.records;

import java.util.UUID;

class PrivateRecordBuilder {
    PrivateRecord buildPrivate(String id, int myInt) {
        return new PrivateRecord(id, myInt);
    }

    RecordInterface buildInterface(String id, int myInt) {
        return buildPrivate(id, myInt);
    }

    private record PrivateRecord(String id, int myInt) implements RecordInterface {
        private PrivateRecord {
            if (id == null) {
                id = UUID.randomUUID().toString();
            }
        }
    }
}
