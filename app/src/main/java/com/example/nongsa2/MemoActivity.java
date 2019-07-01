package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.activity_memo, container, false);

        final EditText edit_SIDO_NM = (EditText) view.findViewById(R.id.SIDO_NM);
        final EditText edit_OWNER_NM = (EditText) view.findViewById(R.id.OWNER_NM);
        final EditText edit_OWNER_CONTACT = (EditText) view.findViewById(R.id.OWNER_CONTACT);
        final EditText edit_DEAL_AMAUNT = (EditText) view.findViewById(R.id.DEAL_AMAUNT);

        Button mRegister_btn = (Button) view.findViewById(R.id.Register_btn);
        mRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SIDO_NM = edit_SIDO_NM.getText().toString();
                String OWNER_NM = edit_OWNER_NM.getText().toString();
                String OWNER_CONTACT = edit_OWNER_CONTACT.getText().toString();
                String DEAL_AMAUNT = edit_DEAL_AMAUNT.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() { //여기 thirdpage랑 겹치는데 바꿔야함?
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jasonresponse = new JSONObject(response);
                            boolean success = jasonresponse.getBoolean("success");
                            Log.e(this.getClass().getName(), "회원등록시!" + success);
                            Log.e(this.getClass().getName(), "success!" + success);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                MemoRequest memoRequest = new MemoRequest(SIDO_NM, OWNER_NM, OWNER_CONTACT, DEAL_AMAUNT, null);
                RequestQueue queue = Volley.newRequestQueue(MemoActivity.this.getApplicationContext());
                queue.add(memoRequest);
            }
        });
        return view;
    }
}