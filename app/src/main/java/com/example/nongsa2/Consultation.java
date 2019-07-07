package com.example.nongsa2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Consultation.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Consultation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Consultation extends Fragment implements MainActivity.OnBackPressedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Fragment fragment;
    Con_array Con_array=new Con_array();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView boardlistview;
    private Con_BoardListAdapter adapter;
    private List<Con_Board> boardList;
    private OnFragmentInteractionListener mListener;

    public Consultation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Consultation.
     */
    // TODO: Rename and change types and number of parameters
    public static Consultation newInstance(String param1, String param2) {
        Consultation fragment = new Consultation();
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
        View view =inflater.inflate(R.layout.fragment_consultation, container, false);
        fragment = new Consultation();
        new BackgroundTask3().execute();
        return view;
    }
    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);
        boardlistview=(ListView) getView().findViewById(R.id.listview);
        boardList = new ArrayList<Con_Board>();
        adapter = new Con_BoardListAdapter(getContext().getApplicationContext(),boardList,this);
        boardlistview.setAdapter(adapter);
        boardlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fragment = new Detail_Consultation();
                int count=i;
                Bundle bundle=new Bundle();
                bundle.putString("count", String.valueOf(count));
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
        });
        Button search_btn=(Button) getView().findViewById(R.id.search1);
        Button add_btn=(Button) getView().findViewById(R.id.register);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("조회하고자 하는 ");
                alert.setMessage("키워드를 입력해주세요.");
                final EditText keyword = new EditText(getActivity());
                alert.setView(keyword);
                alert.setNegativeButton("확인",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        boardList.clear();
                        String kwd = keyword.getText().toString();
                        Log.e("NTTID","start");
                        new BackgroundTask().execute(kwd);
                    }
                });
                alert.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                alert.show();
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        FloatingActionButton floatingactionbutton = (FloatingActionButton) getView().findViewById(R.id.floatingActionButton);
        floatingactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddFragment();
                replaceFragment(fragment);
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
    private void addFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.container2, fragment);
        fragmentTransaction.commit();
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
    class BackgroundTask extends AsyncTask<String, Void, String> {
        String target;

        customprogress progressDialog = new customprogress(getContext());
        @Override
        protected void onPreExecute() {
            progressDialog.setCancelable(true);
            progressDialog .getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            target = "http://www.okdab.kr/episAutoAnswerApi/expert/list/json?pageSize=1000&pageNum=1&kwd=";
            Log.e(this.getClass().getName(), "백그라운드로 list뽑기 시작한다.");
            progressDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {
            try {
                String kwd=params[0];
                target=target+URLEncoder.encode(kwd);
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

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
                JSONObject jsonObject = new JSONObject(res);
                Log.e(this.getClass().getName(), String.valueOf(jsonObject));
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                JSONArray jsonArray = jsonObject1.getJSONArray("rows");
                Log.e(this.getClass().getName(), String.valueOf(jsonArray));
                int count = 0;
                while(count < jsonArray.length()){
                    Log.e(this.getClass().getName(), "들어오긴하냐?");
                    JSONObject object = jsonArray.getJSONObject(count);
                    String REGDT=object.getString("REGDT");
                    Log.e(this.getClass().getName(), Static_setting.REGDT);
                    String NTTID=object.getString("NTTID");
                    Log.e(this.getClass().getName(), NTTID);
                    String USERNM=object.getString("USERNM");
                    Log.e(this.getClass().getName(), USERNM);
                    String TITLE=object.getString("TITLE");
                    Log.e(this.getClass().getName(), Static_setting.TITLE);
                    new BackgroundTask2().execute(NTTID,USERNM,REGDT,TITLE);
                    count++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }

    }
    class BackgroundTask2 extends AsyncTask<String, Void, String> {
        String target;

        customprogress progressDialog = new customprogress(getContext());
        @Override
        protected void onPreExecute() {
            progressDialog.setCancelable(true);
            progressDialog .getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            target = "http://www.okdab.kr/episAutoAnswerApi/webchat/expert/detail/json?publicYN=Y&nttId=";
            Log.e(this.getClass().getName(), "백그라운드로 list뽑기 시작한다.");
            progressDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {

            try {
                String NTTID=params[0];
                String USERNM=params[1];

                target=target+URLEncoder.encode(NTTID)+"&userNm="+URLEncoder.encode(USERNM);
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

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
                JSONObject jsonObject = new JSONObject(res);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                int count = 0;
                while(count < jsonArray.length()){
                    Log.e(this.getClass().getName(), "들어오긴하냐?");
                    JSONObject object = jsonArray.getJSONObject(count);
                    Con_array.setcount(String.valueOf(count));
                    Con_array.settitle(object.getString("title"));
                   // Log.e(this.getClass().getName(), title);
                    Con_array.setregDt(object.getString("regDt"));
                   // Log.e(this.getClass().getName(), regDt);setNTTID
                    Con_array.setuserNm(object.getString("userNm"));
                   // Log.e(this.getClass().getName(), userNm);
                    Con_array.setnttId(object.getString("nttId"));
                   // Log.e(this.getClass().getName(), nttId);
                    Con_array.setcontents(object.getString("contents"));
                   // Log.e(this.getClass().getName(), contents);
                    Con_array.setanswerTitle(object.getString("answerTitle"));
                   // Log.e(this.getClass().getName(), answerTitle);
                    Con_array.setanswerContents(object.getString("answerContents"));
                   // Log.e(this.getClass().getName(), answerContents);
                    Con_array.setanswerUserNm(object.getString("answerUserNm"));
                  //  Log.e(this.getClass().getName(), answerUserNm);
                    Con_array.setanswerOrgNm(object.getString("answerOrgNm"));
                   // Log.e(this.getClass().getName(), answerOrgNm);
                    Con_array.setanswerDeptNm(object.getString("answerDeptNm"));
                   // Log.e(this.getClass().getName(), answerDeptNm);
                    Con_array.setanswerEmail(object.getString("answerEmail"));
                   // Log.e(this.getClass().getName(), answerEmail);
                    Con_Board board = new Con_Board(Con_array.gettitle(count),Con_array.getuserNm(count),Con_array.getregDt(count),Con_array.getnttId(count),Con_array.getcontents(count),Con_array.getanswerTitle(count),Con_array.getanswerContents(count),Con_array.getanswerUserNm(count),Con_array.getanswerOrgNm(count),Con_array.getanswerDeptNm(count),Con_array.getanswerEmail(count));
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



    class BackgroundTask3 extends AsyncTask<String, Void, String> {
        String target;

        customprogress progressDialog = new customprogress(getContext());
        @Override
        protected void onPreExecute() {

            progressDialog.setCancelable(true);
            progressDialog .getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            target = "http://www.okdab.kr/episAutoAnswerApi/expert/list/json?pageSize=1000&pageNum=1&kwd=";
            Log.e(this.getClass().getName(), "백그라운드로 list뽑기 시작한다.");
            progressDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {
            try {

                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

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
                JSONObject jsonObject = new JSONObject(res);
                Log.e(this.getClass().getName(), String.valueOf(jsonObject));
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                JSONArray jsonArray = jsonObject1.getJSONArray("rows");
                Log.e(this.getClass().getName(), String.valueOf(jsonArray));
                int count = 0;
                while(count < jsonArray.length()){
                    Log.e(this.getClass().getName(), "들어오긴하냐?");
                    JSONObject object = jsonArray.getJSONObject(count);
                    String REGDT=object.getString("REGDT");
                    Log.e(this.getClass().getName(), Static_setting.REGDT);
                    String NTTID=object.getString("NTTID");
                    Log.e(this.getClass().getName(), NTTID);
                    String USERNM=object.getString("USERNM");
                    Log.e(this.getClass().getName(), USERNM);
                    String TITLE=object.getString("TITLE");
                    Log.e(this.getClass().getName(), Static_setting.TITLE);
                    new BackgroundTask4().execute(NTTID,USERNM,REGDT,TITLE);

                    count++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }

    }
    class BackgroundTask4 extends AsyncTask<String, Void, String> {
        String target;

        customprogress progressDialog = new customprogress(getContext());
        @Override
        protected void onPreExecute() {
            progressDialog.setCancelable(true);
            progressDialog .getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            target = "http://www.okdab.kr/episAutoAnswerApi/webchat/expert/detail/json?publicYN=Y&nttId=";
            Log.e(this.getClass().getName(), "백그라운드로 list뽑기 시작한다.");
            progressDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {
            try {
                String NTTID=params[0];
                String USERNM=params[1];
                target=target+URLEncoder.encode(NTTID)+"&userNm="+URLEncoder.encode(USERNM);
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

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
                JSONObject jsonObject = new JSONObject(res);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                int count = 0;
                while(count < jsonArray.length()){
                    Log.e(this.getClass().getName(), "들어오긴하냐?");
                    JSONObject object = jsonArray.getJSONObject(count);
                    Con_array.setcount(String.valueOf(count));
                    Con_array.settitle(object.getString("title"));
                    // Log.e(this.getClass().getName(), title);
                    Con_array.setregDt(object.getString("regDt"));
                    // Log.e(this.getClass().getName(), regDt);setNTTID
                    Con_array.setuserNm(object.getString("userNm"));
                    // Log.e(this.getClass().getName(), userNm);
                    Con_array.setnttId(object.getString("nttId"));
                    // Log.e(this.getClass().getName(), nttId);
                    Con_array.setcontents(object.getString("contents"));
                    // Log.e(this.getClass().getName(), contents);
                    Con_array.setanswerTitle(object.getString("answerTitle"));
                    // Log.e(this.getClass().getName(), answerTitle);
                    Con_array.setanswerContents(object.getString("answerContents"));
                    // Log.e(this.getClass().getName(), answerContents);
                    Con_array.setanswerUserNm(object.getString("answerUserNm"));
                    //  Log.e(this.getClass().getName(), answerUserNm);
                    Con_array.setanswerOrgNm(object.getString("answerOrgNm"));
                    // Log.e(this.getClass().getName(), answerOrgNm);
                    Con_array.setanswerDeptNm(object.getString("answerDeptNm"));
                    // Log.e(this.getClass().getName(), answerDeptNm);
                    Con_array.setanswerEmail(object.getString("answerEmail"));
                    // Log.e(this.getClass().getName(), answerEmail);
                    Con_Board board = new Con_Board(Con_array.gettitle(count),Con_array.getuserNm(count),Con_array.getregDt(count),Con_array.getnttId(count),Con_array.getcontents(count),Con_array.getanswerTitle(count),Con_array.getanswerContents(count),Con_array.getanswerUserNm(count),Con_array.getanswerOrgNm(count),Con_array.getanswerDeptNm(count),Con_array.getanswerEmail(count));
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

}
