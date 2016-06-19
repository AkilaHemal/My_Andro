package com.ahcreations.akila.myandro.SMSListener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;

import com.ahcreations.akila.myandro.DataBaseFiles.DataBaseHelper;

public class SmsReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs;

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                DataBaseHelper myDB = new DataBaseHelper(context);
                myDB.insertData(msgs[i].getOriginatingAddress(), msgs[i].getMessageBody());
            }
        }
    }
}
