//package com.example.nongsa2;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.EditorInfo;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONObject;
//import androidx.fragment.app.FragmentManager;
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link secondpage.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link secondpage#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class secondpage extends Fragment implements MainActivity.OnBackPressedListener{
//    private EditText mEmailView;
//    private EditText mPasswordView;
//    private View mProgressView;
//    private View mLoginFormView;
//
//
//    public secondpage() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View view =inflater.inflate(R.layout.fragment_secondpage, container, false);
//
//        mPasswordView = (EditText) view.findViewById(R.id.password);
//        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
//                    //attemptLogin("c");
//                    return true;
//                }
//                return false;
//            }
//        });
//
//        mEmailView = (EditText) view.findViewById(R.id.email);
//
//        Button mEmailSignInButton = (Button) view.findViewById(R.id.email_sign_in_button);
//        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                attemptLogin();
//
//
//            }
//        });
//        mLoginFormView = view.findViewById(R.id.login_form);
//        mProgressView = view.findViewById(R.id.login_progress);
//        return view;
//    }
//    private void attemptLogin() {
//
//        final String userID = mEmailView.getText().toString();
//        final String userPW = mPasswordView.getText().toString();
//        Log.e(this.getClass().getName(), "로그인시도!");
//
//        Log.e(this.getClass().getName(), "MAIN 화면으로");
//        Response.Listener<String> responseListener = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e(this.getClass().getName(), "트라이문out");
//                try {
//                    Log.e(this.getClass().getName(), "try in!");
//                    JSONObject jsonResponse = new JSONObject(response);
//                    boolean success = jsonResponse.getBoolean("success");
//                    Log.e(this.getClass().getName(), "success!" + success);
//                    if (success) {
//                        Log.e(this.getClass().getName(), "성공함!");
//                        Log.e(this.getClass().getName(), "jsonResponse!" + jsonResponse);
//                        String ID = jsonResponse.getString("ID");
//                        String PW = jsonResponse.getString("PW");
//                        String Name = jsonResponse.getString("Name");
//                        String Phone = jsonResponse.getString("Phone");
//
//                        Log.e(this.getClass().getName(), "로그인성공!");
//                        Log.e(this.getClass().getName(), "ID!" + ID);
//                        Log.e(this.getClass().getName(), "PW!" + PW);
//                        Log.e(this.getClass().getName(), "Name!" + Name);
//                        Log.e(this.getClass().getName(), "Phone!" + Phone);
//
//                        Intent intent = new Intent( getContext(), MainActivity.class);
//                        intent.putExtra("ID", ID);
//                        intent.putExtra("PW", PW);
//                        intent.putExtra("Name", Name);
//                        intent.putExtra("Phone", Phone);
//
//                        Log.e(this.getClass().getName(), "로그인성공!");
//                        Log.e(this.getClass().getName(), "ID!" + ID);
//                        Log.e(this.getClass().getName(), "PW!" + PW);
//                        Log.e(this.getClass().getName(), "Name!" + Name);
//                        Log.e(this.getClass().getName(), "Phone!" + Phone);
//                        Static_setting.ID=ID;
//                        Static_setting.PW=PW;
//                        Static_setting.Name=Name;
//                        Static_setting.Phone=Phone;
//                        startActivity(intent);
//                        //nextIntent();
//                    } else {
//                        Log.e(this.getClass().getName(), "로그인실패함");
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                        builder.setMessage("로그인 실패")
//                                .setNegativeButton("확인", null)
//                                .create()
//                                .show();
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        LoginRequest login_guardian_request = new LoginRequest(userID, userPW, responseListener);
//        RequestQueue queue =  Volley.newRequestQueue(getActivity().getApplicationContext());
//        queue.add(login_guardian_request);
//
//
//
//    }
//
//
//
//    @Override
//    public void onBack() {
//
//    }
//
//
//}