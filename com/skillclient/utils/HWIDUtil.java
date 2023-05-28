package com.skillclient.utils;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.net.NetworkInterface;
import java.net.InetAddress;

public class HWIDUtil
{
    public static String getByMAC() {
        try {
            final byte[] bytes = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
            final MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[40];
            md.update(bytes);
            sha1hash = md.digest();
            return convertToHex(sha1hash);
        }
        catch (NoSuchAlgorithmException | SocketException | UnknownHostException ex2) {
            final Exception ex;
            final Exception e = ex;
            return "";
        }
    }
    
    public static String getByOther() {
        try {
            final String text = String.valueOf(System.getProperty("user.name")) + System.getProperty("os.version") + System.getProperty("os.name") + System.getProperty("user.name");
            final byte[] bytes = text.getBytes("iso-8859-1");
            final MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[40];
            md.update(bytes);
            sha1hash = md.digest();
            return convertToHex(sha1hash);
        }
        catch (UnsupportedEncodingException | NoSuchAlgorithmException ex2) {
            final Exception ex;
            final Exception e = ex;
            return "";
        }
    }
    
    private static String convertToHex(final byte[] data) {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; ++i) {
            int halfbyte = data[i] >>> 4 & 0xF;
            int two_halfs = 0;
            do {
                if (halfbyte >= 0 && halfbyte <= 9) {
                    buf.append((char)(48 + halfbyte));
                }
                else {
                    buf.append((char)(97 + (halfbyte - 10)));
                }
                halfbyte = (data[i] & 0xF);
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
}
