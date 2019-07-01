package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MoreComplex extends AppCompatActivity {
    String VILL_ID="";
    String VILL_NM="";
    String VILL_NATURE_RESOURCE="";
    String VILL_ECONOMY_RESOURCE="";
    String VILL_NATURE_RESOURCE1="";
    String VILL_NATURE_RESOURCE2="";
    String VILL_ECONOMY_RESOURCE1="";
    String VILL_ECONOMY_RESOURCE2="";
    String VILL_ECONOMY_RESOURCE3="";
    String VILL_COMMUNITY_RESOURCE="";
    String VILL_COMMUNITY_RESOURCE1="";
    String VILL_COMMUNITY_RESOURCE2="";
    String VILL_COMMUNITY_RESOURCE3="";
    String VILL_COMMUNITY_RESOURCE4="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_complex);
        TextView textView =(TextView) findViewById(R.id.morecom);
        Intent intent = getIntent();

         VILL_ID=intent.getStringExtra("VILL_ID");
         VILL_NM=intent.getStringExtra("VILL_NM");
         VILL_NATURE_RESOURCE=intent.getStringExtra("VILL_NATURE_RESOURCE");
         VILL_ECONOMY_RESOURCE=intent.getStringExtra("VILL_ECONOMY_RESOURCE");
         VILL_NATURE_RESOURCE1=intent.getStringExtra("VILL_NATURE_RESOURCE1");
         VILL_NATURE_RESOURCE2=intent.getStringExtra("VILL_NATURE_RESOURCE2");
         VILL_ECONOMY_RESOURCE1=intent.getStringExtra("VILL_ECONOMY_RESOURCE1");
         VILL_ECONOMY_RESOURCE2=intent.getStringExtra("VILL_ECONOMY_RESOURCE2");
         VILL_ECONOMY_RESOURCE3=intent.getStringExtra("VILL_ECONOMY_RESOURCE3");
         VILL_COMMUNITY_RESOURCE=intent.getStringExtra("VILL_COMMUNITY_RESOURCE");
         VILL_COMMUNITY_RESOURCE1=intent.getStringExtra("VILL_COMMUNITY_RESOURCE1");
         VILL_COMMUNITY_RESOURCE2=intent.getStringExtra("VILL_COMMUNITY_RESOURCE2");
         VILL_COMMUNITY_RESOURCE3=intent.getStringExtra("VILL_COMMUNITY_RESOURCE3");
         VILL_COMMUNITY_RESOURCE4=intent.getStringExtra("VILL_COMMUNITY_RESOURCE4");

        String a=""+VILL_ID+"\n"+VILL_NM+"\n"+VILL_NATURE_RESOURCE+"\n"+VILL_ECONOMY_RESOURCE+"\n"+VILL_NATURE_RESOURCE1+"\n"+VILL_NATURE_RESOURCE2+"\n"+VILL_ECONOMY_RESOURCE1
                +"\n"+VILL_ECONOMY_RESOURCE2+"\n"+VILL_ECONOMY_RESOURCE3+"\n"+VILL_COMMUNITY_RESOURCE
                +"\n"+VILL_COMMUNITY_RESOURCE1+"\n"+VILL_COMMUNITY_RESOURCE2+"\n"+VILL_COMMUNITY_RESOURCE3+"\n"+VILL_COMMUNITY_RESOURCE4;

        textView.setText(a);

    }
}
