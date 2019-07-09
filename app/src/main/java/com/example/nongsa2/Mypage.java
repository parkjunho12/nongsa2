package com.example.nongsa2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link thirdpage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link thirdpage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Mypage extends Fragment implements MainActivity.OnBackPressedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText ID;
    EditText PW;
    EditText Name;
    EditText Phone;
     String ID1="" ;
     String PW1="" ;
     String Name1="" ;
     String Phone1="" ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Fragment f1,f2,f3;
    private FragmentManager fragmentManager;

    private OnFragmentInteractionListener mListener;

    public Mypage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment thirdpage.
     */
    // TODO: Rename and change types and number of parameters
    public static Mypage newInstance(String param1, String param2) {
        Mypage fragment = new Mypage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mypage, container, false);
          ID=(EditText) view.findViewById(R.id.ID1);
          PW=(EditText) view.findViewById(R.id.password);
          Name=(EditText) view.findViewById(R.id.Name1);
          Phone=(EditText) view.findViewById(R.id.phone1);
        Log.e(this.getClass().getName(), "Static_setting!" + Static_setting.ID);
        Log.e(this.getClass().getName(), "Static_setting!" + Static_setting.PW);
        Log.e(this.getClass().getName(), "Static_setting!" + Static_setting.Name);
        Log.e(this.getClass().getName(), "Static_setting!" + Static_setting.Phone);
        ID.setText((Static_setting.ID));
        PW.setText((Static_setting.PW));
        Name.setText((Static_setting.Name));
        Phone.setText((Static_setting.Phone));
          ID1 =ID.getText().toString();
          PW1 =PW.getText().toString();
          Name1 =Name.getText().toString();
          Phone1 =Phone.getText().toString();
        Button modify_btn=(Button) view.findViewById(R.id.modify_btn);
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modify(Static_setting.ID,ID1,PW1,Name1,Phone1);
            }
        });
        Button cancel=(Button) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private void modify(String M_ID, String ID, String PW, String Name, String Phone) {
        Response.Listener<String> responseListener =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    Log.e(this.getClass().getName(),"회원등록시!"+success);
                    Log.e(this.getClass().getName(),"success!"+success);
                    if(success) /*회원가입성공*/
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setMessage("회원정보 수정 성공")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .create()
                                .show();
                    }
                    else /*회원가입실패*/
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setMessage("회원정보 수정 실패")
                                .setNegativeButton("확인",null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Modify_Myinfo_Request Modify_Myinfo_Request=new Modify_Myinfo_Request(M_ID,ID, PW, Name, Phone,responseListener);
        RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(Modify_Myinfo_Request);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            // throw new RuntimeException(context.toString()
            //   + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onBack() {
        MainActivity activity = (MainActivity) getActivity();
        fragmentManager = activity.getSupportFragmentManager();
        activity.setOnBackPressedListener(null);
        activity.onBackPressed();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container2, fragment);
        fragmentTransaction.commit();
    }

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}