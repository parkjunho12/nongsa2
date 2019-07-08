package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class more_garden extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_garden);
        Intent intent = getIntent();
        String count =   intent.getStringExtra("count");
        Log.e("count",count);
        TextView formname=(TextView) findViewById(R.id.formname);
        formname.setText(Garden_array.getFARM_NM(Integer.parseInt(count)));
        TextView addre=(TextView) findViewById(R.id.addre);
        addre.setText(Garden_array.getADDRESS1(Integer.parseInt(count)));
        TextView site=(TextView) findViewById(R.id.site);
        site.setText(Garden_array.getSELL_AREA_INFO(Integer.parseInt(count)));
        TextView price=(TextView) findViewById(R.id.price);
        price.setText(Garden_array.getPRICE(Integer.parseInt(count)));
        TextView home=(TextView) findViewById(R.id.home);
        home.setText( Garden_array.getHOMEPAGE(Integer.parseInt(count)));
        TextView fa=(TextView) findViewById(R.id.fa);
        fa.setText(Garden_array.getOFF_SITE(Integer.parseInt(count)));
        TextView condate=(TextView) findViewById(R.id.condate);
        condate.setText(Garden_array.getREGIST(Integer.parseInt(count)));
        TextView id=(TextView) findViewById(R.id.id);
        id.setText(Garden_array.getID(Integer.parseInt(count)));
        TextView phone=(TextView) findViewById(R.id.phone);
        phone.setText(Garden_array.getphone(Integer.parseInt(count)));

        Button back =(Button) findViewById(R.id.conback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
