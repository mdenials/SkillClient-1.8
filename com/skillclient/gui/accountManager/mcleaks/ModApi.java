package com.skillclient.gui.accountManager.mcleaks;

import com.google.gson.JsonElement;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.net.URLConnection;
import java.util.concurrent.ForkJoinPool;
import com.google.gson.Gson;
import java.util.concurrent.ExecutorService;

public class ModApi
{
    public static final ExecutorService EXECUTOR_SERVICE;
    private static final String API_URL = "http://auth.mcleaks.net/v1/";
    private static final Gson gson;
    
    static {
        EXECUTOR_SERVICE = new ForkJoinPool();
        gson = new Gson();
    }
    
    public static void redeem(final String token, final Callback<Object> callback) {
        ModApi.EXECUTOR_SERVICE.execute((Runnable)new ModApi.ModApi$1(token, (Callback)callback));
    }
    
    private static URLConnection preparePostRequest(final String url, final String body) {
        try {
            HttpURLConnection con;
            if (url.toLowerCase().startsWith("https://")) {
                con = (HttpsURLConnection)new URL(url).openConnection();
            }
            else {
                con = (HttpURLConnection)new URL(url).openConnection();
            }
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.setRequestProperty("User-Agent", "SkillClient xD");
            HttpURLConnection.setFollowRedirects(true);
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            final DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(body.getBytes("UTF-8"));
            wr.flush();
            wr.close();
            return con;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static Object getResult(final URLConnection urlConnection) {
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            final StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            final JsonElement jsonElement = (JsonElement)ModApi.gson.fromJson(result.toString(), (Class)JsonElement.class);
            if (!jsonElement.isJsonObject() || !jsonElement.getAsJsonObject().has("success")) {
                return "An error occured! [G1]";
            }
            if (!jsonElement.getAsJsonObject().get("success").getAsBoolean()) {
                return jsonElement.getAsJsonObject().has("errorMessage") ? jsonElement.getAsJsonObject().get("errorMessage").getAsString() : "An error occured! [G4]";
            }
            if (!jsonElement.getAsJsonObject().has("result")) {
                return "An error occured! [G3]";
            }
            return jsonElement.getAsJsonObject().get("result").isJsonObject() ? jsonElement.getAsJsonObject().get("result").getAsJsonObject() : null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "An error occured! [G2]";
        }
    }
}

