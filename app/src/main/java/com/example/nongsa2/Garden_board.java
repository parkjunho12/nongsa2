package com.example.nongsa2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Gyesi.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Gyesi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Garden_board extends Fragment  implements MainActivity.OnBackPressedListener {

    Fragment fragment;
    static int gyesictn=0;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Garden_array Garden_array=new Garden_array();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Garden_board() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Gyesi.
     */
    // TODO: Rename and change types and number of parameters
    public static Garden_board newInstance(String param1, String param2) {
        Garden_board fragment = new Garden_board();
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
    private ArrayAdapter sidoAdapter;
    private Spinner sidoSpinner;




    private ListView boardlistview;
    private Garden_BoardListAdapter adapter;
    private List<Garden_board_Board> boardList;
    static int pandan=0;

    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);

        sidoSpinner =(Spinner)getView().findViewById(R.id.sidospin);


        sidoAdapter =ArrayAdapter.createFromResource(getActivity(),R.array.sido,android.R.layout.simple_spinner_dropdown_item);
        sidoSpinner.setAdapter(sidoAdapter);


        boardlistview=(ListView) getView().findViewById(R.id.listview);
        boardList = new ArrayList<Garden_board_Board>();
        adapter = new Garden_BoardListAdapter(getContext().getApplicationContext(),boardList,this);
        boardlistview.setAdapter(adapter);
        boardlistview.setAdapter(adapter);
        boardlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext().getApplicationContext(),more_garden.class);
                int count=i;
                Bundle bundle=new Bundle();
                bundle.putString("count", String.valueOf(count));
                intent.putExtra("count",String.valueOf(count));
                startActivity(intent);
            }
        });

        Button searchbtn = (Button) getView().findViewById(R.id.search);


        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundTask().execute(sidoSpinner.getSelectedItem().toString());
            }
        });
        FloatingActionButton floatingactionbutton = (FloatingActionButton) getView().findViewById(R.id.floatingActionButton);
        floatingactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Static_setting.ID=="비회원")
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setMessage("비회원입니다. \n로그인 하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    fragment = new secondpage();
                                    replaceFragment(fragment);
                                }
                            })
                            .create()
                            .show();
                }
                else {
                    Fragment fragment = new MemoFragment1();
                    replaceFragment(fragment);
                }
            }
        });

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_garden_board, container, false);
        fragment = new Garden_board();
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
        ((MainActivity) context).setOnBackPressedListener(this);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    private Fragment f1,f2,f3;
    private FragmentManager fragmentManager;

    @Override
    public void onBack() {
        MainActivity activity = (MainActivity) getActivity();
        fragmentManager = activity.getSupportFragmentManager();
        activity.setOnBackPressedListener(null);

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


    class BackgroundTask extends AsyncTask<String, Void, String> {
        String target;

        customprogress progressDialog = new customprogress(getContext());



        @Override
        protected void onPreExecute() {


            progressDialog.setCancelable(true);
            progressDialog .getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Log.e("전라남도",sidoSpinner.getSelectedItem().toString());
            try {
                target = "http://dbwo4011.cafe24.com/migration/garden_info_request.php?AREA_LNM="+URLEncoder.encode(sidoSpinner.getSelectedItem().toString(),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.e(this.getClass().getName(), "백그라운드로 list뽑기 시작한다.");
            progressDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {
            try {

                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST"); //post방식으로
                httpURLConnection.setDoInput(true); // server와 통신에서 입력가능상태로 설정
                httpURLConnection.setDoOutput(true);//server와의 통신에서 출력 가능한 상태로

                OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());//서버로
                wr.flush();//flush!
                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String res) {
            Log.e(this.getClass().getName(), "백그라운드 try문안으로");
            try {
                boardList.clear();
                Garden_array.clear();
                JSONObject jsonObject = new JSONObject(res);
                Log.e(this.getClass().getName(), "백그라운드 try문안으로");
                JSONArray jsonArray = jsonObject.getJSONArray("response");

                Log.e(this.getClass().getName(), String.valueOf(jsonArray));
                int count = 0;

                Log.e("jsonArray.length()", String.valueOf(jsonArray.length()));
                while(count< jsonArray.length()){
                    Log.e("String.valueOf(count)", String.valueOf(count));
                    JSONObject object = jsonArray.getJSONObject(count);

                    Log.e(this.getClass().getName(), String.valueOf(object));
                    Log.e("#####################", String.valueOf(count));
                    Garden_array.setFARM_TYPE(object.getString("FARM_TYPE"));

                    Garden_array.setFARM_NM(object.getString("FARM_NM"));

                    Garden_array.setADDRESS1(object.getString("ADDRESS1"));

                    Garden_array.setSELL_AREA_INFO(object.getString("SELL_AREA_INFO"));

                    Garden_array.setHOMEPAGE(object.getString("HOMEPAGE"));

                    Garden_array.setOFF_SITE(object.getString("OFF_SITE"));

                    Garden_array.setAPPLY_MTHD(object.getString("APPLY_MTHD"));

                    Garden_array.setPRICE(object.getString("PRICE"));

                    Garden_array.setREGIST(object.getString("REGIST_DT"));


                    Garden_array.setID(object.getString("ID"));
                    Garden_array.setName(object.getString("Name"));
                    Garden_array.setphone(object.getString("phone"));
                    Garden_board_Board board = new Garden_board_Board(Garden_array.getFARM_TYPE(count),
                            Garden_array.getFARM_NM(count),
                            Garden_array.getADDRESS1(count),
                            Garden_array.getSELL_AREA_INFO(count),
                            Garden_array.getHOMEPAGE(count),
                            Garden_array.getOFF_SITE(count),
                            Garden_array.getAPPLY_MTHD(count),
                            Garden_array.getPRICE(count),
                            Garden_array.getREGIST(count),
                            Garden_array.getID(count),
                            Garden_array.getName(count),
                            Garden_array.getphone(count));
                    boardList.add(board);
                    adapter.notifyDataSetChanged();
                    count++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            progressDialog.dismiss();
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



}



