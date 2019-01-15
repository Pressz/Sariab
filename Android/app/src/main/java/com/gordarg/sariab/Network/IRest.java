package com.gordarg.sariab.Network;

import java.io.IOException;

public interface IRest {
    short testConnection() throws IOException ;
    String get() throws IOException;
    void post() throws IOException;
    void put();
    void delete();
}
