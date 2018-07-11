package com.oracle.truffle.sl.runtime.valueobject;

public abstract class SLValueObject {

    private final int numFields;


    protected SLValueObject(int numFields) {
        this.numFields = numFields;
    }


    public int getNumFields() {
        return numFields;
    }

    public abstract Object get(int index);
}
