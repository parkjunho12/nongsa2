package com.example.nongsa2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
public class Gyesi extends Fragment  {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Migration_info_array Migration_info_array=new Migration_info_array();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Gyesi() {
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
    public static Gyesi newInstance(String param1, String param2) {
        Gyesi fragment = new Gyesi();
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


    private String id="";

    private ListView boardlistview;
    private BoardListAdapter adapter;
    private List<Board> boardList;


    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);







        boardlistview=(ListView) getView().findViewById(R.id.listview);
        boardList = new ArrayList<Board>();
        adapter = new BoardListAdapter(getContext().getApplicationContext(),boardList,this);
        boardlistview.setAdapter(adapter);


        Button searchbtn = (Button) getView().findViewById(R.id.search);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                End_info_request();
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_gyesi, container, false);

        new BackgroundTask().execute();
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

    class BackgroundTask extends AsyncTask<String, Void, String> {
        String target;




        @Override
        protected void onPreExecute() {
//            try {
//                +URLEncoder.encode(sidoSpinner.toString(),"UTF-8")+"&VACANT_YEAR="+URLEncoder.encode(yearSpinner.toString(),"UTF-8")
//                        +"&DEAL_TYPE="+URLEncoder.encode(maemaeSpinner.toString(),"UTF-8"///////////검색기능 추가할때 필요한거
                    target = "http://dbwo4011.cafe24.com/migration/migration_info_request.php";
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();/////////검색기능 추가할때 필요한거요겄도
//            }
            Log.e(this.getClass().getName(), "백그라운드로 list뽑기 시작한다.");
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
                Log.e(this.getClass().getName(), "백그라운드 try문안으로");
                        Log.e(this.getClass().getName(), "백그라운드 try문안으로");
                JSONObject jsonObject = new JSONObject(res);
                        Log.e(this.getClass().getName(), "백그라운드 try문안으로");
                JSONArray jsonArray = jsonObject.getJSONArray("response");


                        int count = 0;


                while(count < jsonArray.length()){
                    Log.e(this.getClass().getName(), "들어오긴하냐?");
                    JSONObject object = jsonArray.getJSONObject(count);
                    Migration_info_array.setID(object.getString("ID"));
                    Migration_info_array.setSIDO_NM(object.getString("SIDO_NM"));
                    Migration_info_array.setSIGUN_NM(object.getString("SIGUN_NM"));
                    Migration_info_array.setADDR(object.getString("ADDR"));
                    Migration_info_array.setDEAL_AMOUNT(object.getString("DEAL_AMOUNT"));
                    Migration_info_array.setDEAL_BIGO(object.getString("DEAL_BIGO"));
                    Migration_info_array.setBUILDING_AREA(object.getString("BUILDING_AREA"));
                    Migration_info_array.setAREA_ETC(object.getString("AREA_ETC"));
                    Migration_info_array.setBUILD_YEAR(object.getString("BUILD_YEAR"));
                    Migration_info_array.setVACANT_YEAR(object.getString("VACANT_YEAR"));
                    Migration_info_array.setSTRUCT_TYPE(object.getString("STRUCT_TYPE"));
                    Migration_info_array.setOWNER_NM(object.getString("OWNER_NM"));
                    Migration_info_array.setOWNER_CONTACT(object.getString("OWNER_CONTACT"));
                    Migration_info_array.setINSPECTOR(object.getString("INSPECTOR"));
                    Migration_info_array.setLOT_AREA(object.getString("LOT_AREA"));
                    Migration_info_array.setBIGO(object.getString("BIGO"));
                    Migration_info_array.setFILE_PATH1(object.getString("FILE_PATH1"));
                    Migration_info_array.setFILE_PATH2(object.getString("FILE_PATH2"));
                    Migration_info_array.setFILE_PATH3(object.getString("FILE_PATH3"));
                    Migration_info_array.setDETAIL_URL(object.getString("DETAIL_URL"));
                    Migration_info_array.setDEAL_NEGO_YN(object.getString("DEAL_NEGO_YN"));
                    Migration_info_array.setLEASE_AMOUNT(object.getString("LEASE_AMOUNT"));
                    Migration_info_array.setRENT_AMOUNT(object.getString("RENT_AMOUNT"));
                    Migration_info_array.setGUBUN(object.getString("GUBUN"));
                    Migration_info_array.setDEAL_TYPE(object.getString("DEAL_TYPE"));
                    Migration_info_array.setREG_DT(object.getString("REG_DT"));





                    count++;

                }

            } catch (Exception e){
                e.printStackTrace();
            }
            End_info_request();
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
    private void End_info_request() {
        int i=Migration_info_array.getListSize();
        boardList.clear();
        for(int l=0;l<i;l++)
        {
            String title;
            String content;
            String date;
            String SIDO_NM;
            String ID;
            String SIGUN_NM;
            String ADDR;
            String DEAL_AMOUNT;
            String DEAL_BIGO;
            String BUILDING_AREA;
            String AREA_ETC;
            String BUILD_YEAR;
            String VACANT_YEAR;
            String STRUCT_TYPE;
            String OWNER_NM;
            String OWNER_CONTACT;
            String INSPECTOR;
            String LOT_AREA;
            String BIGO;
            String FILE_PATH1;
            String FILE_PATH2;
            String FILE_PATH3;
            String DETAIL_URL;
            String DEAL_NEGO_YN;
            String LEASE_AMOUNT;
            String RENT_AMOUNT;
            String GUBUN;
            String DEAL_TYPE;
            String REG_DT;



            FILE_PATH1=Migration_info_array.getFILE_PATH1(l);
            if(Migration_info_array.getFILE_PATH1(l).equals("")||Migration_info_array.getFILE_PATH1(l).equals(null))
            {
                FILE_PATH1= "없음";

                }
            FILE_PATH2=Migration_info_array.getFILE_PATH2(l);
            if(Migration_info_array.getFILE_PATH2(l).equals("")||Migration_info_array.getFILE_PATH2(l).equals(null)) {
                FILE_PATH2 = "없음";
            }
            FILE_PATH3=Migration_info_array.getFILE_PATH3(l);
            if(Migration_info_array.getFILE_PATH3(l).equals("")||Migration_info_array.getFILE_PATH3(l).equals(null))
            {
                FILE_PATH3= "없음";

            }


            SIDO_NM = Migration_info_array.getSIDO_NM(l);
            ID = Migration_info_array.getID(l);
            SIGUN_NM = Migration_info_array.getSIGUN_NM(l);
            ADDR = Migration_info_array.getADDR(l);
            DEAL_AMOUNT = Migration_info_array.getDEAL_AMOUNT(l);
            DEAL_BIGO = Migration_info_array.getDEAL_BIGO(l);
            BUILDING_AREA = Migration_info_array.getBUILDING_AREA(l);
            AREA_ETC = Migration_info_array.getAREA_ETC(l);
            BUILD_YEAR = Migration_info_array.getBUILD_YEAR(l);
            VACANT_YEAR = Migration_info_array.getVACANT_YEAR(l);
            STRUCT_TYPE = Migration_info_array.getSTRUCT_TYPE(l);
            OWNER_NM = Migration_info_array.getOWNER_NM(l);
            OWNER_CONTACT = Migration_info_array.getOWNER_CONTACT(l);
            INSPECTOR = Migration_info_array.getINSPECTOR(l);
            LOT_AREA = Migration_info_array.getLOT_AREA(l);
            BIGO = Migration_info_array.getBIGO(l);
            FILE_PATH1 = Migration_info_array.getFILE_PATH1(l);
            FILE_PATH2 = Migration_info_array.getFILE_PATH2(l);
            FILE_PATH3 = Migration_info_array.getFILE_PATH3(l);
            DETAIL_URL = Migration_info_array.getDETAIL_URL(l);
            DEAL_NEGO_YN = Migration_info_array.getDEAL_NEGO_YN(l);
            LEASE_AMOUNT = Migration_info_array.getLEASE_AMOUNT(l);
            RENT_AMOUNT = Migration_info_array.getRENT_AMOUNT(l);
            GUBUN = Migration_info_array.getGUBUN(l);
            DEAL_TYPE = Migration_info_array.getDEAL_TYPE(l);
            REG_DT = Migration_info_array.getREG_DT(l);








            title = Migration_info_array.getGUBUN(l)+"("+Migration_info_array.getDEAL_TYPE(l)+")";
            date = "\n날짜 : "+Migration_info_array.getREG_DT(l);
            content ="시도 :"+Migration_info_array.getSIDO_NM(l)+"\n시군: "+Migration_info_array.getSIGUN_NM(l)+"\n이름 :"+Migration_info_array.getOWNER_NM(l)+"\n전화번호 : "+Migration_info_array.getOWNER_CONTACT(l)
                    +" \n가격"+Migration_info_array.getDEAL_AMOUNT(l)+"\n정보 : "+Migration_info_array.getDEAL_NEGO_YN(l) ;
            Board board = new Board( title,  content,  date,  SIDO_NM,  ID,  SIGUN_NM,  ADDR,  DEAL_AMOUNT,  DEAL_BIGO,  BUILDING_AREA,  AREA_ETC,  BUILD_YEAR,  VACANT_YEAR,  STRUCT_TYPE,  OWNER_NM,  OWNER_CONTACT,  INSPECTOR,  LOT_AREA,  BIGO,  FILE_PATH1,  FILE_PATH2,  FILE_PATH3,  DETAIL_URL,  DEAL_NEGO_YN,  LEASE_AMOUNT,  RENT_AMOUNT,  GUBUN,  DEAL_TYPE,  REG_DT);
            boardList.add(board);
            /*
            Log.e(this.getClass().getName(), ""+Migration_info_array.getID(l));
            Log.e(this.getClass().getName(), ""+Migration_info_array.getSIDO_NM(l));
            Log.e(this.getClass().getName(), ""+Migration_info_array.getSIGUN_NM(l));
            Log.e(this.getClass().getName(), ""+Migration_info_array.getADDR(l));
            Log.e(this.getClass().getName(), "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");*/
        }  adapter.notifyDataSetChanged();

    }
}
