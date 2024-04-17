package com.ulpdroid.trabajopractico4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class LlamarReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean modoWifi = intent.getBooleanExtra("connected", false);
        if (modoWifi) {
            Intent intentLlamar = new Intent(Intent.ACTION_CALL);
            intentLlamar.setData(Uri.parse("tel:" + "2664553747"));
            intentLlamar.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentLlamar);
        }
    }
}
