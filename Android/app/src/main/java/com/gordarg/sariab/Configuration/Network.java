package com.gordarg.sariab.Configuration;

public enum Network implements IConfiguration {


    // sudo ifconfig lo:40 192.168.40.1 netmask 255.255.255.0 up
//    api_end_point("http://192.168.42.163/Sariab/Backend/controller/"),
    // api_end_point("http://192.168.40.1/Sariab/Backend/controller/"),
    api_end_point("http://sariab.pressz.ir/controller/"),
    agent("mobile");

    private final String value;

    Network(String s) {
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
