package com.example.nongsa2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment implements MainActivity.OnBackPressedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddFragment() {
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
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);
        final EditText edit_title = (EditText) getView().findViewById(R.id.title);

        final EditText edit_content = (EditText) getView().findViewById(R.id.content);
        //여기 오류?
        final String title = edit_title.getText().toString();

        final String content = edit_content.getText().toString();
        Button reg_btn = (Button) getView().findViewById(R.id.Register_btn);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_board(title, Static_setting.Name, content);
            }
        });
        Button back_btn = (Button) getView().findViewById(R.id.Back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("NTTID",roomId);
                Fragment fragment = new Consultation();
                replaceFragment(fragment);
            }
        });
    }



    Fragment fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_add_con, container, false);
        fragment = new AddFragment();
        new BackgroundTask().execute();

        return view;
    }

    private void Add_board(String title, String userNm, String content) {
        if (title.isEmpty()||userNm.isEmpty()||content.isEmpty()){
            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
            alert.setTitle("전문가 상담게시판 ");
            alert.setMessage("등록에 실패했습니다. \n 다시 시도하시겠습니까?.");
            alert.setNegativeButton("확인",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Fragment fragment = new AddFragment();
                    replaceFragment(fragment);
                }
            });
            alert.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Fragment fragment = new Consultation();
                    replaceFragment(fragment);
                }
            });
            alert.show();
        }
        else if (!title.isEmpty()&&!userNm.isEmpty()&&!content.isEmpty())
        {
            new BackgroundTask1().execute(title,userNm,content,roomId);
        }

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
        replaceFragment(fragment);
        activity.onBackPressed();
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
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


String roomId;
    class BackgroundTask extends AsyncTask<String, Void, String> {
        String target;
        @Override
        protected void onPreExecute() {
            target = "http://www.okdab.kr/episAutoAnswerApi/webchat/json";
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
                 roomId=jsonObject1.getString("roomId");

            } catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    class BackgroundTask1 extends AsyncTask<String, Void, String> {
        String target;
        @Override
        protected void onPreExecute() {

            target = "http://www.okdab.kr/episAutoAnswerApi/expert/xml?publicYN=Y&roomId=";

        }


        @Override
        protected String doInBackground(String... params) {
            try {
                String title=params[0];
                String userNm=params[1];
                String content=params[2];
                String roomId=params[3];

                target=target+URLEncoder.encode(roomId)+"&userNm="+URLEncoder.encode(userNm)+"&title="+URLEncoder.encode(title)+"&contents="+URLEncoder.encode(content);
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
            String status=res;

            if (status.contains("<success>Y</success>"))
            {
                Log.e(this.getClass().getName(), "성공성공"+status);
                        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                        alert.setTitle("전문가 상담게시판 ");
                        alert.setMessage("등록에 성공했습니다.");
                        alert.setNegativeButton("확인",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Fragment fragment = new Consultation();
                                replaceFragment(fragment);
                            }
                        });
                        alert.show();
            }
            else if (status.contains("<success>N</success>"))
            {
                Log.e(this.getClass().getName(), "실패실패"+status);
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("전문가 상담게시판 ");
                alert.setMessage("등록에 실패했습니다. \n 다시 시도하시겠습니까?.");
                alert.setNegativeButton("확인",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Fragment fragment = new AddFragment();
                        replaceFragment(fragment);
                    }
                });
                alert.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Fragment fragment = new Consultation();
                        replaceFragment(fragment);
                    }
                });
                alert.show();
            }
            else {
                Log.e(this.getClass().getName(), "실패실패"+status);
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("전문가 상담게시판 ");
                alert.setMessage("등록에 실패했습니다. \n 다시 시도하시겠습니까?.");
                alert.setNegativeButton("확인",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Fragment fragment = new AddFragment();
                        replaceFragment(fragment);
                    }
                });
                alert.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Fragment fragment = new Consultation();
                        replaceFragment(fragment);
                    }
                });
                alert.show();
            }
        }

    }

}
