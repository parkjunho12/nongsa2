package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        TextView textView =(TextView) findViewById(R.id.maeul);
        TextView textView2 =(TextView) findViewById(R.id.maeul2);
        TextView textView3 =(TextView) findViewById(R.id.maeul3);
        TextView textView4 =(TextView) findViewById(R.id.maeul4);
        TextView textView5 =(TextView) findViewById(R.id.maeul5);
        TextView textView6 =(TextView) findViewById(R.id.maeul6);
        Button back =(Button) findViewById(R.id.morback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
        if(VILL_NATURE_RESOURCE.equals("None"))
        {
            VILL_NATURE_RESOURCE="";
        }
        if(VILL_NATURE_RESOURCE1.equals("None"))
        {
            VILL_NATURE_RESOURCE1="";
        }
        if(VILL_NATURE_RESOURCE2.equals("None"))
        {
            VILL_NATURE_RESOURCE2="";
        }
        if(VILL_ECONOMY_RESOURCE.equals("None"))
        {
            VILL_ECONOMY_RESOURCE="";
        }
        if(VILL_ECONOMY_RESOURCE1.equals("None"))
        {
            VILL_ECONOMY_RESOURCE1="";
        } if(VILL_ECONOMY_RESOURCE2.equals("None"))
        {
            VILL_ECONOMY_RESOURCE2="";
        } if(VILL_ECONOMY_RESOURCE3.equals("None"))
        {
            VILL_ECONOMY_RESOURCE3="";
        } if(VILL_COMMUNITY_RESOURCE.equals("None"))
        {
            VILL_COMMUNITY_RESOURCE="";
        } if(VILL_COMMUNITY_RESOURCE1.equals("None"))
        {
            VILL_COMMUNITY_RESOURCE1="";
        } if(VILL_COMMUNITY_RESOURCE2.equals("None"))
        {
            VILL_COMMUNITY_RESOURCE2="";
        }
        if(VILL_COMMUNITY_RESOURCE3.equals("None"))
        {
            VILL_COMMUNITY_RESOURCE3="";
        }
        if(VILL_COMMUNITY_RESOURCE4.equals("None"))
        {
            VILL_COMMUNITY_RESOURCE4="";
        }
        String a=VILL_NATURE_RESOURCE+"\n"+VILL_ECONOMY_RESOURCE+"\n"+VILL_NATURE_RESOURCE1+"\n"+VILL_NATURE_RESOURCE2+"\n"+VILL_ECONOMY_RESOURCE1
                +"\n"+VILL_ECONOMY_RESOURCE2+"\n"+VILL_ECONOMY_RESOURCE3+"\n"+VILL_COMMUNITY_RESOURCE
                +"\n"+VILL_COMMUNITY_RESOURCE1+"\n"+VILL_COMMUNITY_RESOURCE2+"\n"+VILL_COMMUNITY_RESOURCE3+"\n"+VILL_COMMUNITY_RESOURCE4;

        String b =VILL_NATURE_RESOURCE+"\n"+VILL_NATURE_RESOURCE1+"\n"+VILL_NATURE_RESOURCE2;
        String c =VILL_ECONOMY_RESOURCE+"\n"+VILL_ECONOMY_RESOURCE1+"\n"+VILL_ECONOMY_RESOURCE2+"\n"+VILL_ECONOMY_RESOURCE3;
        String d =VILL_ID;
        String e =VILL_COMMUNITY_RESOURCE+"\n"+VILL_COMMUNITY_RESOURCE1+"\n"+VILL_COMMUNITY_RESOURCE2+"\n"+VILL_COMMUNITY_RESOURCE3+"\n"+VILL_COMMUNITY_RESOURCE4;







        if(d.equals("위치 정보가 정확 하지 않습니다.")|| d.equals("선택지와 근처 농촌마을 간의 직선거리가 10km이내인 경우만 농촌 마을 상세정보를 제공합니다."))
        {
            a=VILL_ID;
            b=VILL_ID;
            c=VILL_ID;
            d=VILL_ID;
            e=VILL_ID;
            textView3.setText(VILL_ID);
        }
        else {
            textView3.setText(VILL_NM);
        textView.setText(VILL_NM+" 정보");
        textView2.setText(VILL_NM+"의 유용한 정보를 한꺼번에 확인");
        TextView textView1 = findViewById(R.id.main);
        textView1.setText(VILL_NM+" 정보");
        }
        textView4.setText(b);
        textView5.setText(c);
        textView6.setText(e);
    }
}
