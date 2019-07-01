package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MoreComplex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_complex);
        TextView textView =(TextView) findViewById(R.id.morecom);
        Intent intent = getIntent();
        String a=""+intent.getStringExtra("VILL_ID")+"\n"+intent.getStringExtra("VILL_NM")+"\n"+intent.getStringExtra("VILL_NATURE_RESOURCE")+"\n"+intent.getStringExtra("VILL_ECONOMY_RESOURCE")
                +"\n"+intent.getStringExtra("VILL_NATURE_RESOURCE1")+"\n"+intent.getStringExtra("VILL_NATURE_RESOURCE2")+"\n"+intent.getStringExtra("VILL_ECONOMY_RESOURCE1")+"\n"+intent.getStringExtra("VILL_ECONOMY_RESOURCE2")
                +"\n"+intent.getStringExtra("VILL_ECONOMY_RESOURCE3")+"\n"+intent.getStringExtra("VILL_COMMUNITY_RESOURCE")+"\n"+intent.getStringExtra("VILL_COMMUNITY_RESOURCE1")+"\n"+intent.getStringExtra("VILL_COMMUNITY_RESOURCE2")
                +"\n"+intent.getStringExtra("VILL_COMMUNITY_RESOURCE3")+"\n"+intent.getStringExtra("VILL_COMMUNITY_RESOURCE4");



        textView.setText(a);

    }
}
