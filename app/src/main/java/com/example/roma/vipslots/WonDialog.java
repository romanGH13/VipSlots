package com.example.roma.vipslots;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
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
        text.setText(msg);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                dialog.cancel();
            }
        };

        Timer timer = new Timer();
        //timer.schedule(task, 700);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0; // уровень затемнения от 1.0 до 0.0
        dialog.getWindow().setAttributes(lp);

        dialog.show();
    }
}