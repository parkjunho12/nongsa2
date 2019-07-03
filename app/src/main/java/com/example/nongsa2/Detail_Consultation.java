package com.example.nongsa2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Detail_Consultation.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Detail_Consultation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Detail_Consultation extends Fragment implements MainActivity.OnBackPressedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Fragment fragment;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Detail_Consultation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Detail_Consultation.
     */
    // TODO: Rename and change types and number of parameters
    public static Detail_Consultation newInstance(String param1, String param2) {
        Detail_Consultation fragment = new Detail_Consultation();
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
        View view =inflater.inflate(R.layout.fragment_detail__consultation, container, false);
        Bundle bundle = getArguments();
        String count = bundle.getString("count");
        TextView TITLE_text=(TextView) view.findViewById(R.id.TITLE);
        TITLE_text.setText(Con_array.gettitle(Integer.parseInt(count)));
        TextView date_text=(TextView) view.findViewById(R.id.date);
        date_text.setText(Con_array.getregDt(Integer.parseInt(count)));
        TextView id_text=(TextView) view.findViewById(R.id.id);
        id_text.setText(Con_array.getuserNm(Integer.parseInt(count)));
        TextView content_text=(TextView) view.findViewById(R.id.content);
        content_text.setText(Con_array.getcontents(Integer.parseInt(count)));
        TextView answer_text=(TextView) view.findViewById(R.id.answer);
        answer_text.setText(Con_array.getanswerContents(Integer.parseInt(count)));
        fragment = new Consultation();

        return view;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container2, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).setOnBackPressedListener(this);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onBack() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnBackPressedListener(null);
        replaceFragment(fragment);
        activity.onBackPressed();
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
