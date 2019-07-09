package com.example.nongsa2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link thirdpage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link thirdpage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class thirdpage extends Fragment {


    public thirdpage() {
        // Required empty public constructor
    }

   private EditText user_id;
    private EditText user_pw;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_thirdpage, container, false);

        user_id = (EditText) view.findViewById(R.id.ID);
         user_pw = (EditText) view.findViewById(R.id.PW);
        final EditText ed_Name = (EditText) view.findViewById(R.id.Name);
        final EditText ed_Phone = (EditText) view.findViewById(R.id.Phone);
        sharedPreferences = getActivity().getSharedPreferences("junho", Activity.MODE_PRIVATE);
        String id = sharedPreferences.getString("user_id", "");
        if (!"".equals(id)) {
            user_id.setText(id);
        }

        Button mRegister_btn = (Button) view.findViewById(R.id.Register_btn);
        mRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!validateForm()) return;
                final String id = user_id.getText().toString();

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(id, user_pw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sharedPreferences.edit().putString("user_id", id).commit();
                            final String uid = FirebaseAuth.getInstance().getUid();

                            UserModel userModel = new UserModel();
                            userModel.setUid(uid);
                            userModel.setUserid(id);
                            userModel.setUsernm(extractIDFromEmail(id));
                            userModel.setUsermsg("...");

                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            Log.e("존나 ",uid+":uid  "+userModel.getToken()+"token"+userModel.getUserid()+"id");
                            db.collection("users").document(uid)
                                    .set(userModel)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {



                                            Response.Listener<String> responseListener =new Response.Listener<String>() {

                                                @Override
                                                public void onResponse(String response) {
                                                    try {

                                                        JSONObject jsonResponse = new JSONObject(response);
                                                        boolean success = jsonResponse.getBoolean("success");

                                                        if(success) /*회원가입성공*/
                                                        {

                                                        }
                                                        else /*회원가입실패*/
                                                        {

                                                        }

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            };
                                            String ID =user_id.getText().toString();
                                            String PW =user_pw.getText().toString();
                                            String Name =ed_Name.getText().toString();
                                            String Phone =ed_Phone.getText().toString();
                                            RegisterRequest registerRequest=new RegisterRequest(ID, PW, Name, Phone,responseListener);
                                            RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
                                            queue.add(registerRequest);


                                            Intent intent = new Intent(getContext(), MainActivity.class);
                                            sendRegistrationToServer();
                                            startActivity(intent);

                                            Log.d(String.valueOf(R.string.app_name), "DocumentSnapshot added with ID: " + uid);
                                        }
                                    });
                        } else {
                            Util9.showMessage(getContext(), task.getException().getMessage());
                        }
                    }
                });



            }
        });

        ///////////////////////////////////
        ////////////* 뒤로가기*////////////
        ///////////////////////////////////
        Button mBack_btn = (Button) view.findViewById(R.id.Back_btn);
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }
    String extractIDFromEmail(String email){
        String[] parts = email.split("@");
        return parts[0];
    }
    void sendRegistrationToServer() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String token = FirebaseInstanceId.getInstance().getToken();
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        FirebaseFirestore.getInstance().collection("users").document(uid).set(map, SetOptions.merge());
    }
    private boolean validateForm() {
        boolean valid = true;

        String email = user_id.getText().toString();
        if (TextUtils.isEmpty(email)) {
            user_id.setError("Required.");
            valid = false;
        } else {
            user_id.setError(null);
        }

        String password = user_pw.getText().toString();
        if (TextUtils.isEmpty(password)) {
            user_pw.setError("Required.");
            valid = false;
        } else {
            user_pw.setError(null);
        }

        return valid;
    }


}