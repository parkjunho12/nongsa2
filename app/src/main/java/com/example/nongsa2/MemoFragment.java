package com.example.nongsa2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MemoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MemoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemoFragment newInstance(String param1, String param2) {
        MemoFragment fragment = new MemoFragment();
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
        View view =inflater.inflate(R.layout.fragment_memo, container, false);

        final EditText edit_SIDO_NM = (EditText) view.findViewById(R.id.SIDO_NM);
        final EditText edit_OWNER_NM = (EditText) view.findViewById(R.id.OWNER_NM);
        final EditText edit_OWNER_CONTACT = (EditText) view.findViewById(R.id.OWNER_CONTACT);
        final EditText edit_DEAL_AMAUNT = (EditText) view.findViewById(R.id.DEAL_AMAUNT);

        Spinner spinner1 = (Spinner)view.findViewById(R.id.spinner1);
        String text1 = spinner1.getSelectedItem().toString(); // 스피너에서 선택된 항목의 값을 text1로 받음

        Button mRegister_btn = (Button) view.findViewById(R.id.Register_btn);
        mRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SIDO_NM =edit_SIDO_NM.getText().toString();
                String OWNER_NM =edit_OWNER_NM.getText().toString();
                String OWNER_CONTACT =edit_OWNER_CONTACT.getText().toString();
                String DEAL_AMAUNT =edit_DEAL_AMAUNT.getText().toString();


                Response.Listener<String> responseListener =new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Log.e(this.getClass().getName(),"정보등록시!"+success);
                            Log.e(this.getClass().getName(),"success!"+success);
                            if(success) /*회원가입성공*/
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                                builder.setMessage("정보등록 성공")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent=new Intent(getActivity(),MainActivity.class);
                                                getActivity().getApplicationContext().startActivity(intent);
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                            else /*정보등록실패*/
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                                builder.setMessage("정보등록 실패\n ID가 중복입니다.")
                                        .setNegativeButton("확인",null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                MemoRequest memoRequest =new MemoRequest(SIDO_NM, OWNER_NM, OWNER_CONTACT, DEAL_AMAUNT,responseListener);
                RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(memoRequest);
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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
