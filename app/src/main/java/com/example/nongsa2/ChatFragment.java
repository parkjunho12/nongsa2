package com.example.nongsa2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class ChatFragment extends Fragment {

    private Button sendBtn;
    private EditText msg_input;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormatHour = new SimpleDateFormat("aa hh:mm");
    private String roomID;
    private String myUid;
    private String toUid;
    private Map<String, UserModel> userList = new HashMap<>();

    private ListenerRegistration listenerRegistration;
    private FirebaseFirestore firestore=null;
    private StorageReference storageReference;
    private LinearLayoutManager linearLayoutManager;

    private customprogress progressDialog = null;
    private Integer userCount = 0;


    public ChatFragment() {
        // Required empty public constructor
    }
    public static final ChatFragment getInstance(String toUid, String roomID) {
        ChatFragment f = new ChatFragment();
        Bundle bdl = new Bundle();
        bdl.putString("toUid", toUid);
        bdl.putString("roomID", roomID);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view =inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        msg_input = view.findViewById(R.id.msg_input);
        sendBtn = view.findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(sendBtnClickListener);
        view.findViewById(R.id.msg_input).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    Util9.hideKeyboard(getActivity());
                }
            }
        });
        if (getArguments() != null) {
            roomID = getArguments().getString("roomID");
            toUid = getArguments().getString("toUid");
        }
        firestore = FirebaseFirestore.getInstance();
        storageReference  = FirebaseStorage.getInstance().getReference();

        dateFormatDay.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        dateFormatHour.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if (!"".equals(toUid) && toUid!=null) {                     // find existing room for two user
            findChatRoom(toUid);
        } else
        if (!"".equals(roomID) && roomID!=null) { // existing room (multi user)
            setChatRoom(roomID);
        };

        if (roomID==null) {                                                     // new room for two user
            getUserInfoFromServer(myUid);
            getUserInfoFromServer(toUid);
            userCount = 2;
        };

        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v,
                                       int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mAdapter!=null & bottom < oldBottom) {
                    final int lastAdapterItem = mAdapter.getItemCount() - 1;
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            int recyclerViewPositionOffset = -1000000;
                            View bottomView = linearLayoutManager.findViewByPosition(lastAdapterItem);
                            if (bottomView != null) {
                                recyclerViewPositionOffset = 0 - bottomView.getHeight();
                            }
                            linearLayoutManager.scrollToPositionWithOffset(lastAdapterItem, recyclerViewPositionOffset);
                        }
                    });
                }
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }
    // get a user info
    void getUserInfoFromServer(String id){
        firestore.collection("users").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserModel userModel = documentSnapshot.toObject(UserModel.class);
                userList.put(userModel.getUid(), userModel);
                if (roomID != null & userCount == userList.size()) {
                    mAdapter = new RecyclerViewAdapter();
                    recyclerView.setAdapter(mAdapter);
                }
            }
        });
    }
    // Returns the room ID after locating the chatting room with the user ID.
    void findChatRoom(final String toUid){
        firestore.collection("rooms").whereGreaterThanOrEqualTo("users."+myUid, 0).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!task.isSuccessful()) {return;}

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map<String, Long> users = (Map<String, Long>) document.get("users");
                            if (users.size()==2 & users.get(toUid)!=null){
                                setChatRoom(document.getId());
                                break;
                            }
                        }
                    }
                });
    }
    // get user list in a chatting room
    void setChatRoom(String rid) {
        roomID = rid;
        firestore.collection("rooms").document(roomID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (!task.isSuccessful()) {return;}
                DocumentSnapshot document = task.getResult();
                Map<String, Long> users = (Map<String, Long>) document.get("users");

                for( String key : users.keySet() ){
                    getUserInfoFromServer(key);
                }
                userCount = users.size();
                //users.put(myUid, (long) 0);
                //document.getReference().update("users", users);
            }
        });
    }
    public void CreateChattingRoom(final DocumentReference room) {
        Map<String, Integer> users = new HashMap<>();
        String title = "";
        for( String key : userList.keySet() ){
            users.put(key, 0);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("title", null);
        data.put("users", users);

        room.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mAdapter = new RecyclerViewAdapter();
                    recyclerView.setAdapter(mAdapter);
                }
            }
        });
    }
    public Map<String, UserModel> getUserList() {
        return userList;
    }

    Button.OnClickListener sendBtnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            String msg = msg_input.getText().toString();
            sendMessage(msg, "0", null);
            msg_input.setText("");
        }
    };
    private void sendMessage(final String msg, String msgtype, final ChatModel.FileInfo fileinfo) {
        sendBtn.setEnabled(false);

        if (roomID==null) {             // create chatting room for two user
            roomID = firestore.collection("rooms").document().getId();
            CreateChattingRoom( firestore.collection("rooms").document(roomID) );
        }

        final Map<String,Object> messages = new HashMap<>();
        messages.put("uid", myUid);
        messages.put("msg", msg);
        messages.put("msgtype", msgtype);
        messages.put("timestamp", FieldValue.serverTimestamp());

        final DocumentReference docRef = firestore.collection("rooms").document(roomID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (!task.isSuccessful()) {return;}

                WriteBatch batch = firestore.batch();

                // save last message
                batch.set(docRef, messages, SetOptions.merge());

                // save message
                List<String> readUsers = new ArrayList();
                readUsers.add(myUid);
                messages.put("readUsers", readUsers);//new String[]{myUid} );
                batch.set(docRef.collection("messages").document(), messages);

                // inc unread message count
                DocumentSnapshot document = task.getResult();
                Map<String, Long> users = (Map<String, Long>) document.get("users");

                for( String key : users.keySet() ){
                    if (!myUid.equals(key)) users.put(key, users.get(key)+1);
                }
                document.getReference().update("users", users);

                batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //sendGCM();
                            sendBtn.setEnabled(true);
                        }
                    }
                });
            }

        });
    };
    public void showProgressDialog(String title ) {
        if (progressDialog==null) {
            progressDialog = new customprogress(getContext());
        }
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle(title);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    public void setProgressDialog(int value) {
        progressDialog.setProgress(value);
    }
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }
    // =======================================================================================
    void setUnread2Read() {
        if (roomID==null) return;

        firestore.collection("rooms").document(roomID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (!task.isSuccessful()) {return;}
                DocumentSnapshot document = task.getResult();
                Map<String, Long> users = (Map<String, Long>) document.get("users");

                users.put(myUid, (long) 0);
                document.getReference().update("users", users);
            }
        });
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        List<Message> messageList;
        String beforeDay = null;
        MessageViewHolder beforeViewHolder;

        RecyclerViewAdapter() {


            messageList = new ArrayList<Message>();
            setUnread2Read();
            startListening();
        }

        public void startListening() {
            beforeDay = null;
            messageList.clear();

            CollectionReference roomRef = firestore.collection("rooms").document(roomID).collection("messages");
            // my chatting room information
            listenerRegistration = roomRef.orderBy("timestamp").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {return;}

                    Message message;
                    for (DocumentChange change : documentSnapshots.getDocumentChanges()) {
                        switch (change.getType()) {
                            case ADDED:
                                message = change.getDocument().toObject(Message.class);
                                //if (message.msg !=null & message.timestamp == null) {continue;} // FieldValue.serverTimestamp is so late

                                if (message.getReadUsers().indexOf(myUid) == -1) {
                                    message.getReadUsers().add(myUid);
                                    change.getDocument().getReference().update("readUsers", message.getReadUsers());
                                }
                                messageList.add(message);
                                notifyItemInserted(change.getNewIndex());
                                break;
                            case MODIFIED:
                                message = change.getDocument().toObject(Message.class);
                                messageList.set(change.getOldIndex(), message);
                                notifyItemChanged(change.getOldIndex());
                                break;
                            case REMOVED:
                                messageList.remove(change.getOldIndex());
                                notifyItemRemoved(change.getOldIndex());
                                break;
                        }
                    }
                    recyclerView.scrollToPosition(messageList.size() - 1);
                }
            });
        }

        public void stopListening() {
            if (listenerRegistration != null) {
                listenerRegistration.remove();
                listenerRegistration = null;
            }

            messageList.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            Message message = messageList.get(position);
            if (myUid.equals(message.getUid()) ) {
                switch(message.getMsgtype()){

                    default:  return R.layout.item_chatmsg_right;
                }
            } else {
                switch(message.getMsgtype()){

                    default:  return R.layout.item_chatmsg_left;
                }
            }
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = null;
            view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            final MessageViewHolder messageViewHolder = (MessageViewHolder) holder;
            final Message message = messageList.get(position);

            setReadCounter(message, messageViewHolder.read_counter);

            if ("0".equals(message.getMsgtype())) {                                      // text message
                messageViewHolder.msg_item.setText(message.getMsg());
            }

            if (! myUid.equals(message.getUid())) {
                UserModel userModel = userList.get(message.getUid());
                messageViewHolder.msg_name.setText(userModel.getUsernm());


            }
            messageViewHolder.divider.setVisibility(View.INVISIBLE);
            messageViewHolder.divider.getLayoutParams().height = 0;
            messageViewHolder.timestamp.setText("");
            if (message.getTimestamp()==null) {return;}

            String day = dateFormatDay.format( message.getTimestamp());
            String timestamp = dateFormatHour.format( message.getTimestamp());
            messageViewHolder.timestamp.setText(timestamp);

            if (position==0) {
                messageViewHolder.divider_date.setText(day);
                messageViewHolder.divider.setVisibility(View.VISIBLE);
                messageViewHolder.divider.getLayoutParams().height = 60;
            } else {
                Message beforeMsg = messageList.get(position - 1);
                String beforeDay = dateFormatDay.format( beforeMsg.getTimestamp() );

                if (!day.equals(beforeDay) && beforeDay != null) {
                    messageViewHolder.divider_date.setText(day);
                    messageViewHolder.divider.setVisibility(View.VISIBLE);
                    messageViewHolder.divider.getLayoutParams().height = 60;
                }
            }

        }

        void setReadCounter (Message message, final TextView textView) {
            int cnt = userCount - message.getReadUsers().size();
            if (cnt > 0) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(String.valueOf(cnt));
            } else {
                textView.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }


    }

    private class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView msg_item;

        public TextView msg_name;
        public TextView timestamp;
        public TextView read_counter;
        public LinearLayout divider;
        public TextView divider_date;


        public MessageViewHolder(View view) {
            super(view);

            msg_item = view.findViewById(R.id.msg_item);

            timestamp = view.findViewById(R.id.timestamp);
            msg_name = view.findViewById(R.id.msg_name);
            read_counter = view.findViewById(R.id.read_counter);
            divider = view.findViewById(R.id.divider);
            divider_date = view.findViewById(R.id.divider_date);


        }

    }

        // photo view


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


}
