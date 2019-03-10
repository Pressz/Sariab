package com.gordarg.sariab.Network;

import java.io.IOException;

public interface IRss {
    short testConnection() throws IOException;
    String get() throws IOException;
}
