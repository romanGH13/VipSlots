package com.example.roma.vipslots;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Roma on 18.05.2018.
 */

public class AboutDialog {

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.about_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.url_label);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dialog.getContext().getString(R.string.natife_url)));
                dialog.getContext().startActivity(browserIntent);
            }
        });

        dialog.show();
    }
}