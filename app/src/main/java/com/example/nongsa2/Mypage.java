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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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
    private UserModel userModel;
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
        Button button =(Button) view.findViewById(R.id.logout);
        Button button2 =(Button) view.findViewById(R.id.chatroom);
        Button myhome =(Button) view.findViewById(R.id.myhome);
        myhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Gyesi();
                Bundle bundle = new Bundle();
                bundle.putString("param1", Static_setting.ID);
                bundle.putString("param2", Static_setting.Name);
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
        });
        Button mygarden =(Button)view.findViewById(R.id.mygarden);
        mygarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Garden_board();
                Bundle bundle = new Bundle();
                bundle.putString("param1", Static_setting.ID);
                bundle.putString("param2", Static_setting.Name);
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
        });
        Button mycon =(Button) view.findViewById(R.id.mycon);
        mycon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Consultation();
                Bundle bundle = new Bundle();
                bundle.putString("param1", Static_setting.ID);
                bundle.putString("param2", Static_setting.Name);
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
        });
        TextView myID=(TextView) view.findViewById(R.id.myID);
        TextView myName=(TextView) view.findViewById(R.id.myName);
        TextView myPhone=(TextView) view.findViewById(R.id.myPhone);
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
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ChatRoomFragment();
                replaceFragment(fragment);
            }
        });

        return view;
    }
    void getUserInfoFromServer(){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("users").document(uid);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userModel = documentSnapshot.toObject(UserModel.class);
                Static_setting.ID = userModel.getUserid();
                Static_setting.Name = userModel.getRealname();
                Static_setting.Phone = userModel.getPhone();

            }
        });
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
     Fragment fragment = new Fragment();
     replaceFragment(fragment);
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