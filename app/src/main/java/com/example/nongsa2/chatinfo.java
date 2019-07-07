package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class chatinfo extends AppCompatActivity {

            ListView listView;
    EditText editText;
    Button sendButton;
    ArrayAdapter adapter;
    String userName;
    private FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference =firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatinfo);
        listView = (ListView) findViewById(R.id.chatlist);
        editText = (EditText) findViewById(R.id.chatedit);
        sendButton = (Button) findViewById(R.id.chatbut);

        userName = Static_setting.ID;  // 랜덤한 유저 이름 설정 ex) user1234

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String CHAT_NAME="";
                    ChatData chatData = new ChatData(userName, editText.getText().toString());  // 유저 이름과 메세지로 chatData 만들기
                    databaseReference.child("chat").child(CHAT_NAME).push().setValue(chatData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
                    editText.setText("");

            }
        });

// 기본 Text를 담을 수 있는 simple_list_item_1을 사용해서 ArrayAdapter를 만들고 listview에 설정
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);

        databaseReference.child("chat").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);

                 // chatData를 가져오고
                adapter.add(chatData.getUserName() + ": " + chatData.getMessage());  // adapter에 추가합니다.
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }



}
