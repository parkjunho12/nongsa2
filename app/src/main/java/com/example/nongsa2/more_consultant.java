package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class more_consultant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_consultant);
        Intent intent = getIntent();
        String count =   intent.getStringExtra("count");
        Log.e("count",count);
        TextView TITLE_text=(TextView) findViewById(R.id.contitle);
        TITLE_text.setText(Con_array.gettitle(Integer.parseInt(count)));
        TextView date_text=(TextView) findViewById(R.id.condate);
        date_text.setText(Con_array.getregDt(Integer.parseInt(count)));
        TextView id_text=(TextView) findViewById(R.id.conid);
        id_text.setText(Con_array.getuserNm(Integer.parseInt(count)));
        TextView content_text=(TextView) findViewById(R.id.concontent);
        content_text.setText(Con_array.getcontents(Integer.parseInt(count)));
        TextView answer_text=(TextView) findViewById(R.id.conadd);
        answer_text.setText(Con_array.getanswerContents(Integer.parseInt(count)));
        Button back =(Button) findViewById(R.id.conback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
