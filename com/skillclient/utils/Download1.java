package com.skillclient.utils;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class Download$1 implements X509TrustManager {
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
    
    @Override
    public void checkClientTrusted(final X509Certificate[] certs, final String authType) {
    }
    
    @Override
    public void checkServerTrusted(final X509Certificate[] certs, final String authType) {
    }
}
