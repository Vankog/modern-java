package com.modernjava.dmo.records;

public class Playground {

    public static void main(String... args) {
        var playground = new Playground();
        playground.doRecords();
        playground.forceOverridingObjectMethods();
        playground.doQualifiedEnumSwitch();
    }

    void doRecords() {
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
        switchPattern(nestedRecord);
        switchPattern(childRecord);

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
    }

    void switchPattern(final Object obj) {
        switch (obj) {
            case NestedRecord(var nestedID, ChildRecord(final var childID, final var myint)) ->
                    System.out.println(STR."nested pattern vars: \{nestedID}, (\{childID}, \{myint})");

            case ChildRecord(var _, final var myInt) -> System.out.println(STR."unused id as single '_': \{myInt}");
            default -> throw new IllegalStateException(STR."Unexpected value: \{obj}");
        }
    }

    /**
     * {@link Record} is redefining various {@link Object} methods as {@code abstract}.
     * This pattern can be reused for other data structures like DTOs to enforce new implementations of
     * {@link Object#equals(Object)}, {@link Object#hashCode()}, {@link Object#toString()} and {@link Object#clone()}.
     */
    private void forceOverridingObjectMethods() {
        var myDTO = new AbstractDTOEnforcingNewImplOfEquals.MyDTO(new int[]{5});
        var myDTO2 = myDTO.clone();
        System.out.println(STR."enforced override of Object methods: \{myDTO.equals(myDTO2)}");
    }

    void doQualifiedEnumSwitch() {
        qualifiedEnumSwitch(EnumInterface.Ding.DANG, 5);
        qualifiedEnumSwitch(EnumInterface.Ding.DANG, 1);
        qualifiedEnumSwitch(EnumInterface.Foo.BAZZ, 0);
    }

    private void qualifiedEnumSwitch(EnumInterface e, int value) {
        // old style pattern matching
        // guards can be anything, even unrelated checks or random stuff
        // patterns vars are mandatory
        switch (e) {
            case EnumInterface.Ding ding when Math.random() < 0.5d -> ding.dingSysout("random ding ");
            case EnumInterface.Ding ding when value == 5 -> ding.dingSysout("ding old style with value 5 ");
            case EnumInterface.Ding ding when ding == EnumInterface.Ding.DANG -> ding.dingSysout("DANG old style ");
            case EnumInterface.Ding ding when ding == EnumInterface.Ding.DONG -> ding.dingSysout("DONG old style ");
            case EnumInterface.Ding _ -> System.out.println("old style ding something ");
            //case EnumInterface.Foo _ -> System.out.println("old style foo something ");
            case null -> System.out.println("null");
            default -> System.out.println("default");
            //case null, default -> System.out.println("null or default");
        }

        // new qualified enum pattern matching
        // unfortunately: no pattern vars available
        switch (e) {
            //case EnumInterface.Ding.DANG ding -> ding.dingSysout("DANG qualified enum matching ");
            //case EnumInterface.Ding.DONG ding -> ding.dingSysout("DONG qualified enum matching ");
            //case EnumInterface.Foo.BAR foo -> foo.fooSysout("BAR qualified enum matching ");
            //case EnumInterface.Foo.BAZZ foo -> foo.fooSysout("BAZZ qualified enum matching ");
            case EnumInterface.Ding.DANG -> EnumInterface.Ding.DANG.dingSysout("DANG qualified enum matching ");
            case EnumInterface.Ding.DONG -> EnumInterface.Ding.DONG.dingSysout("DANG qualified enum matching ");
            case EnumInterface.Foo.BAR -> EnumInterface.Foo.BAR.fooSysout("BAR qualified enum matching ");
            case EnumInterface.Foo.BAZZ -> EnumInterface.Foo.BAZZ.fooSysout("BAZZ qualified enum matching ");
        }
    }

}
