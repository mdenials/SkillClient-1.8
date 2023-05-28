package com.skillclient.gui.accountManager.mcleaks;

import java.net.URLConnection;
import com.google.gson.JsonObject;

class ModApi$1 implements Runnable {
    private final /* synthetic */ String val$token;
    private final /* synthetic */ Callback val$callback;
    
    @Override
    public void run() {
        final URLConnection connection = ModApi.access$0("http://auth.mcleaks.net/v1/redeem", "{\"token\":\"" + this.val$token + "\"}");
        if (connection == null) {
            this.val$callback.done("An error occured! [R1]");
            return;
        }
        final Object o = ModApi.access$1(connection);
        if (o instanceof String) {
            this.val$callback.done(o);
            return;
        }
        final JsonObject jsonObject = (JsonObject)o;
        if (!jsonObject.has("mcname") || !jsonObject.has("session")) {
            this.val$callback.done("An error occured! [R2]");
            return;
        }
        final RedeemResponse response = new RedeemResponse();
        response.setMcName(jsonObject.get("mcname").getAsString());
        response.setSession(jsonObject.get("session").getAsString());
        this.val$callback.done(response);
    }
}
