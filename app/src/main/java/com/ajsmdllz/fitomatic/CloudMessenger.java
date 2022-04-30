package com.ajsmdllz.fitomatic;

import android.content.Context;

public class CloudMessenger {
    private final String Base_URL = "https://fcm.googleapis.com/fcm/send";
    private final String Server_Key = "AAAA63xkzkQ:APA91bFDuNoKJaAMjROdi8rkkf7vumf_LmgL3DMxSBO4QrRuFoxJ0mMZxLXl0P2fc3hIzirbjx7hbrx2TG3_StNr6YQy9BcVrpnftqiKqPLra7OzU0rXt8iQlas9MAqvdA-iluJn7Rz6";

    private static CloudMessenger instance = null;
    private CloudMessenger(){};

    public static CloudMessenger getInstance() {
        if (instance == null) {
            instance = new CloudMessenger();
        }
        return instance;
    }

    private static void sendNotification(Context context, String token, String title, String body) {



    }

}
