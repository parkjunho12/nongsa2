package com.example.nongsa2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ChatlistActivity extends AppCompatActivity {
    private FirestoreAdapter firestoreAdapter;

    @Override
    public void onStart() {
        super.onStart();
        if (firestoreAdapter != null) {
            firestoreAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firestoreAdapter != null) {
            firestoreAdapter.stopListening();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);
        firestoreAdapter = new RecyclerViewAdapter(FirebaseFirestore.getInstance().collection("users").orderBy("usernm"));
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        recyclerView.setAdapter(firestoreAdapter);


    }

}
class RecyclerViewAdapter extends FirestoreAdapter<CustomViewHolder> {

    private StorageReference storageReference;
    private String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    RecyclerViewAdapter(Query query) {

        super(query);

        storageReference  = FirebaseStorage.getInstance().getReference();

    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("안농","안농");
        return new CustomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false));

    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, int position) {
        DocumentSnapshot documentSnapshot = getSnapshot(position);
        final UserModel user = documentSnapshot.toObject(UserModel.class);

        if (myUid.equals(user.getUid())) {
            Log.e("마이 유아이디",documentSnapshot.getId());
            viewHolder.itemView.setVisibility(View.INVISIBLE);
            viewHolder.itemView.getLayoutParams().height = 0;
            return;
        }
        viewHolder.user_name.setText(user.getUsernm());
        viewHolder.user_msg.setText(user.getUsermsg());
        Log.e("안농","안농");


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(v.getContext(),ChatActivity.class);
                intent.putExtra("toUid", user.getUid());
                v.getContext().startActivity(intent);
            }
        });

    }
}

class CustomViewHolder extends RecyclerView.ViewHolder {
    public TextView user_name;
    public TextView user_msg;

    CustomViewHolder(View view) {
        super(view);
        Log.e("안농","안농");
        user_name = view.findViewById(R.id.user_name);
        user_msg = view.findViewById(R.id.user_msg);
    }
}


