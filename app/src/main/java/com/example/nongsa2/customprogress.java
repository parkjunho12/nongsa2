package com.example.nongsa2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class customprogress  extends ProgressDialog {
    private Context c;
    private ImageView imgLogo;
    public customprogress(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

c= context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        imgLogo = (ImageView) findViewById(R.id.img_android);
        Animation anim = AnimationUtils.loadAnimation(c, R.anim.loading);
      imgLogo.setAnimation(anim);
    }
    @Override
    public void show() {
        super.show();
    }
    @Override
    public void dismiss() {
        super.dismiss();
    }

}
