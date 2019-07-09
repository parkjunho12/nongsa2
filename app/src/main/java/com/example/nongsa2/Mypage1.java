package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Mypage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage1);
        Button button =(Button)findViewById(R.id.logout);
        Button button2 =(Button)findViewById(R.id.chatroom);
        Button myhome =(Button)findViewById(R.id.myhome);
        myhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        Button mygarden =(Button)findViewById(R.id.mygarden);
        mygarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Button mycon =(Button)findViewById(R.id.mycon);
        mycon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        TextView myID=(TextView) findViewById(R.id.myID);
        TextView myName=(TextView) findViewById(R.id.myName);
        TextView myPhone=(TextView) findViewById(R.id.myPhone);
        myID.setText(Static_setting.ID);
        myName.setText(Static_setting.Name);
        myPhone.setText(Static_setting.Phone);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Static_setting.ID="비회원";
                Static_setting.PW="";
                Static_setting.Name="";
                Static_setting.Phone="";

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Fragment fragment = new ChatRoomFragment();
                replaceFragment(fragment);

            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frag, fragment);
        fragmentTransaction.commit();
    }
}
