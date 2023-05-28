package com.skillclient.utils;

import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.net.ssl.HostnameVerifier;
import java.net.URL;
import javax.net.ssl.KeyManager;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.HttpsURLConnection;
import com.skillclient.CreativeTabs.SkillTabPlayerhead;
import com.skillclient.gui.GuiChangeLog;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.util.Map;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.skillclient.gui.GuiNewVersion;
import com.skillclient.gui.accountManager.AccountManager;
import java.util.HashMap;
import com.skillclient.main.SkillClient;
import java.util.ArrayList;
import com.skillclient.misc.SCMC;

public class Download implements SCMC
{
    public static ArrayList<String> cmds;
    public static int user_id;
    
    static {
        Download.cmds = new ArrayList<String>();
        Download.user_id = -1;
    }
    
    public static void start() {
        SkillClient.executor.submit(() -> {
            loadClientInfo();
            loadChangeLog();
            loadSkulls();
            loadFeatures();
            System.out.println("[Client] Download finished");
        });
    }
    
    public static void loadClientInfo() {
        try {
            final HashMap<String, String> hashMap;
            final Map<String, String> args = hashMap = new HashMap<String, String>();
            final String s = "version_id";
            final StringBuilder sb = new StringBuilder();
            Download.sc.getClass();
            hashMap.put(s, sb.append(9).toString());
            final Map<String, String> map = args;
            final String s2 = "version";
            Download.sc.getClass();
            map.put(s2, "b8.1");
            final Map<String, String> map2 = args;
            final String s3 = "mc_version";
            Download.sc.getClass();
            map2.put(s3, "1.8");
            args.put("username", AccountManager.orig.username);
            args.put("hwid", HWIDUtil.getByMAC());
            args.put("uuid", AccountManager.orig.uuid.toString().replace("-", ""));
            args.put("os_name", System.getProperty("os.name"));
            final HttpURLConnection connection = getHttpsConnection("https://server.skillclient.com/api2/stats/init.php", args);
            Features.isDonator = (connection.getHeaderField("premium") != null && connection.getHeaderField("premium").equals("true"));
            GuiNewVersion.info = connection.getHeaderField("status_info");
            SkillClient.HAS_NEW_VERSION = Download.VersionState.valueOf(connection.getHeaderField("status"));
            if (SkillClient.HAS_NEW_VERSION.equals((Object)Download.VersionState.shutdown)) {
                System.err.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.err.println("+                                                     +");
                System.err.println("+ Please download newest version from skillclient.com +");
                System.err.println("+                                                     +");
                System.err.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.err.println("");
                System.err.println("");
                System.err.println(GuiNewVersion.info);
                System.err.println("");
                System.err.println("");
                System.exit(133701);
            }
            final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Download.cmds.add(inputLine);
            }
            in.close();
            Download.user_id = Integer.parseInt(connection.getHeaderField("user_id"));
        }
        catch (Exception e) {
            if (Download.sc.indev) {
                e.printStackTrace();
            }
        }
    }
    
    private static void loadChangeLog() {
        try {
            final StringBuilder sb = new StringBuilder("https://server.skillclient.com/changelog/?version=");
            Download.sc.getClass();
            final Scanner scanner = new Scanner(getHttpsConnection(sb.append("1.8").toString()).getInputStream());
            while (scanner.hasNextLine()) {
                GuiChangeLog.changelog.add(scanner.nextLine());
            }
            scanner.close();
        }
        catch (Exception e) {
            if (Download.sc.indev) {
                e.printStackTrace();
            }
        }
    }
    
    public static void loadSkulls() {
        try {
            SkillTabPlayerhead.skulls.clear();
            final Scanner scanner = new Scanner(getHttpsConnection("https://server.skillclient.com/skulls/").getInputStream());
            while (scanner.hasNextLine()) {
                SkillTabPlayerhead.skulls.add(ItemStackUtil.stringtostack("minecraft:skull 1 3 " + scanner.nextLine()));
            }
            scanner.close();
        }
        catch (Exception e) {
            if (Download.sc.indev) {
                e.printStackTrace();
            }
        }
    }
    
    public static void loadFeatures() {
        try {
            final Scanner scanner = new Scanner(getHttpsConnection("https://server.skillclient.com/features/").getInputStream());
            while (scanner.hasNextLine()) {
                SkillTabPlayerhead.skulls.add(ItemStackUtil.stringtostack("minecraft:skull 1 3 " + scanner.nextLine()));
            }
            scanner.close();
        }
        catch (Exception e) {
            if (Download.sc.indev) {
                e.printStackTrace();
            }
        }
    }
    
    public static HttpsURLConnection getHttpsConnection(final String url) throws Exception {
        final TrustManager[] trustAllCerts = { (TrustManager)new Download.Download$1() };
        final SSLContext sc = SSLContext.getInstance("TLSv1.2");
        sc.init(null, trustAllCerts, new SecureRandom());
        final HttpsURLConnection conHttps = (HttpsURLConnection)new URL(url).openConnection();
        conHttps.setSSLSocketFactory(sc.getSocketFactory());
        conHttps.setHostnameVerifier((HostnameVerifier)new Download.Download$2());
        return conHttps;
    }
    
    public static HttpsURLConnection getHttpsConnection(final String url, final Map<String, String> query) throws Exception {
        if (query == null) {
            return getHttpsConnection(url);
        }
        final StringBuilder builder = new StringBuilder();
        for (final Map.Entry<String, String> entry : query.entrySet()) {
            if (builder.length() > 0) {
                builder.append('&');
            }
            try {
                builder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (entry.getValue() != null) {
                builder.append('=');
                try {
                    builder.append(URLEncoder.encode(entry.getValue().replaceAll("§\\w", ""), "UTF-8"));
                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return getHttpsConnection(String.valueOf(url) + "?" + builder.toString());
    }
}
