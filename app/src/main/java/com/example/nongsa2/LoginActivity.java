package com.example.nongsa2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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


public class LoginActivity extends AppCompatActivity {
    private EditText user_id;
    private EditText user_pw;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_id = findViewById(R.id.user_id);
        user_pw = findViewById(R.id.user_pw);
        Button loginBtn = findViewById(R.id.loginBtn);
        Button signupBtn = findViewById(R.id.signupBtn);

        loginBtn.setOnClickListener(loginClick);
        signupBtn.setOnClickListener(signupClick);

        sharedPreferences = getSharedPreferences("junho", Activity.MODE_PRIVATE);
        String id = sharedPreferences.getString("user_id", "");
        if (!"".equals(id)) {
            user_id.setText(id);
        }
    }

    Button.OnClickListener loginClick = new View.OnClickListener() {
        public void onClick(View view) {
            if (!validateForm()) return;

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e(this.getClass().getName(), "트라이문out");
                    try {
                        Log.e(this.getClass().getName(), "try in!");
                        final JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        Log.e(this.getClass().getName(), "success!" + success);
                        if (success) {
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(user_id.getText().toString(), user_pw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        sharedPreferences.edit().putString("user_id", user_id.getText().toString()).commit();

                                        Log.e(this.getClass().getName(), "성공함!");
                                        Log.e(this.getClass().getName(), "jsonResponse!" + jsonResponse);
                                        String ID = null;
                                        try {
                                            ID = jsonResponse.getString("ID");

                                        String PW = jsonResponse.getString("PW");
                                        String Name = jsonResponse.getString("Name");
                                        String Phone = jsonResponse.getString("Phone");

                                        Log.e(this.getClass().getName(), "로그인성공!");
                                        Log.e(this.getClass().getName(), "ID!" + ID);
                                        Log.e(this.getClass().getName(), "PW!" + PW);
                                        Log.e(this.getClass().getName(), "Name!" + Name);
                                        Log.e(this.getClass().getName(), "Phone!" + Phone);
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        sendRegistrationToServer();
                                        intent.putExtra("ID", ID);
                                        intent.putExtra("PW", PW);
                                        intent.putExtra("Name", Name);
                                        intent.putExtra("Phone", Phone);

                                        Log.e(this.getClass().getName(), "로그인성공!");
                                        Log.e(this.getClass().getName(), "ID!" + ID);
                                        Log.e(this.getClass().getName(), "PW!" + PW);
                                        Log.e(this.getClass().getName(), "Name!" + Name);
                                        Log.e(this.getClass().getName(), "Phone!" + Phone);
                                        Static_setting.ID=ID;
                                        Static_setting.PW=PW;
                                        Static_setting.Name=Name;
                                        Static_setting.Phone=Phone;
                                        startActivity(intent);
                                        finish();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        Util9.showMessage(getApplicationContext(), task.getException().getMessage());
                                    }
                                }
                            }
                            );


                            //nextIntent();
                        } else {
                            Log.e(this.getClass().getName(), "로그인실패함");
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("로그인 실패")
                                    .setNegativeButton("확인", null)
                                    .create()
                                    .show();

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            LoginRequest login_guardian_request = new LoginRequest(user_id.getText().toString(), user_pw.getText().toString(), responseListener);
            RequestQueue queue =  Volley.newRequestQueue(LoginActivity.this);
            queue.add(login_guardian_request);
        }
    };

    Button.OnClickListener signupClick = new View.OnClickListener() {
        public void onClick(View view) {

            Fragment fragment = new thirdpage();
            replaceFragment(fragment);

        }
    };
    private void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.log_frag, fragment);
        fragmentTransaction.commit();
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
