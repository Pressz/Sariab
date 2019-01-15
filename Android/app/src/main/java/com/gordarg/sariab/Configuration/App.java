package com.gordarg.sariab.Configuration;

public enum App implements IConfiguration {

    version("0.2"),
    dbversion("3"),
    author("MohammadReza Tayyebi"),
    company("Gordarg");

    private final String value;

    private App(String s) {
        value = s;
    }

    @Override
    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return value.equals(otherName);
    }
    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public int toInt() {
        return Integer.parseInt(this.value);
    }
}
