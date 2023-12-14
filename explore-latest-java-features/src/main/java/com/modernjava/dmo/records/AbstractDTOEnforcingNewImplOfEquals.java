package com.modernjava.dmo.records;

import java.util.Objects;

/**
 * Based on the example given by {@link Record},<br/>
 * enforces new implementation of {@link Object#equals(Object)}, {@link Object#hashCode()},
 * {@link Object#toString()} and {@link Object#clone()}.
 */
abstract class AbstractDTOEnforcingNewImplOfEquals implements Cloneable {
    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(final Object obj);

    @Override
    public abstract String toString();

    @Override
    public abstract Object clone();

    static class MyDTO extends AbstractDTOEnforcingNewImplOfEquals {

        final int[] myInt;

        MyDTO(int[] myInt) {
            this.myInt = myInt;
        }

        @Override
        public boolean equals(final Object object) {
            if (this == object) return true;
            if (null == object || getClass() != object.getClass()) return false;
            final MyDTO myDTO = (MyDTO) object;
            return myInt == myDTO.myInt;
        }

        @Override
        public int hashCode() {
            return Objects.hash(myInt);
        }

        @Override
        public String toString() {
            return "MyDTO{" +
                    "myInt=" + myInt +
                    '}';
        }

        @Override
        public Object clone() {
            return new MyDTO(myInt.clone());
        }
        
    }

}
