package com.modernjava.dmo.records;

public class Playground {

    public static void main(String... args) {
        new Playground().doIt();
    }

    public void doIt() {
        final var childRecord = new ChildRecord("childRecordID", 5);
        System.out.println(STR."default method: \{childRecord.uuid()}");
        System.out.println(STR."toString: \{childRecord}");
        System.out.println(STR."default method in constructor filling myString: \{new ChildRecord(null, 5)}");
        System.out.println(STR."alt-constructor calculating myint: \{new ChildRecord("123")}");

        if (childRecord instanceof ChildRecord castRecord) {
            System.out.println(STR."pattern type: \{castRecord}");
        }

        if (childRecord instanceof ChildRecord(final var anID, final var anInt)) {
            System.out.println(STR."record deconstruction vars: \{anID}, \{anInt}");
        }

        final var nestedRecord = new NestedRecord("myID", childRecord);
        for (int i = 0; i < 5; i++) {
            switchPattern(Math.random() < 0.5d ? childRecord : nestedRecord);

        }

        // RecordBuilder.PrivateRecord privateRecord = new PrivateRecordBuilder().buildPrivate(null, 7); // private Type not accessible
        var privateRecordVar = new PrivateRecordBuilder().buildPrivate(null, 7);
        // privateRecordVar.equals(privateRecordVar); // can't access any member or field when var
        System.out.println(STR."Can only use the private record indirectly: \{privateRecordVar}");

        var privateRecordInterfaceVar = new PrivateRecordBuilder().buildInterface(null, 7);
        switch (privateRecordInterfaceVar) {
            case RecordInterface recordInterface ->
                    System.out.println(STR."Record Interface, no pattern vars possible: \{recordInterface.id()}, \{recordInterface.myInt()}");
            // case RecordBuilder.PrivateRecord(var id, var myInt) -> type not accessible
        }

        new AbstractDTOEnforcingNewImplOfEquals.MyDTO(5);

    }

    private void switchPattern(final Object obj) {
        switch (obj) {
            case NestedRecord(var nestedID, ChildRecord(final var childID, final var myint)) ->
                    System.out.println(STR."nested pattern vars: \{nestedID}, (\{childID}, \{myint})");

            case ChildRecord(var _, final var myInt) -> System.out.println(STR."unused id as single '_': \{myInt}");
            default -> throw new IllegalStateException(STR."Unexpected value: \{obj}");
        }
    }

}
