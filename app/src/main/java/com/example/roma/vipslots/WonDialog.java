package com.example.roma.vipslots;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Roma on 18.05.2018.
 */

public class WonDialog{

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.win_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.coins);
        text.setText(msg+"\r\ncoins");

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                dialog.cancel();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 700);

        dialog.show();
    }
}