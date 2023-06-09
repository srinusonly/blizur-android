package com.blizur.android;

import android.app.Activity;
import android.content.Context;

import android.util.Log;

public class BlizurAPI {
    private static BlizurAPI instance;

    public static BlizurAPI getInstance() {
        if (instance == null) {
            instance = new BlizurAPI();
        }
        return instance;
    }

    public static void init(Context context, String apiKey, String secret) {
        if (context instanceof Activity) {
            try {
                AuthenticationManager.getInstance().handleAuthentication(apiKey, secret, context);
                final Activity activity = (Activity) context;
                SocketManager.setup(context, activity);
            } catch (Exception e) {
                Log.e("Error while setting up blizur", e.getMessage());
            }
        } else {
            Log.d("Blizur setup", "Context is not an instance of Activity. To detect inbound App Links, pass an instance of an Activity to getInstance.");
        }
    }
}
