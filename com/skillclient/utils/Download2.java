package com.skillclient.utils;

import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

class Download$2 implements HostnameVerifier {
    @Override
    public boolean verify(final String hostname, final SSLSession session) {
        return true;
    }
}
