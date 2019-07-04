package com.example.nongsa2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.Utmk;
import com.naver.maps.map.NaverMapSdk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MemoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoFragment extends Fragment implements MainActivity.OnBackPressedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Check_add_array check_add_array =new Check_add_array();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Double> latitude=new ArrayList<Double>();
    ArrayList<Double> longitude=new ArrayList<Double>();

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
        NaverMapSdk.getInstance(getActivity()).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("YOUR_CLIENT_ID_HERE"));
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3, adspin4; //어댑터를 선언했습니다. adspint1(서울,인천..) adspin2(강남구,강서구..)
    String choice_do="";
    String choice_se="";//검색시 선택된 매세지를 띄우기 위한 선언하였습니다. 그냥 선언안하고 인자로 넘기셔도 됩니다.
    String choice_deal_type="";
    String choice_gubun="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_memo, container, false);

        final EditText edit_ADDR = (EditText) view.findViewById(R.id.ADDR);
        final EditText edit_OWNER_NM = (EditText) view.findViewById(R.id.OWNER_NM);
        final EditText edit_OWNER_CONTACT = (EditText) view.findViewById(R.id.OWNER_CONTACT);
        final EditText edit_DEAL_AMOUNT = (EditText) view.findViewById(R.id.DEAL_AMOUNT);
        final EditText edit_DEAL_BIGO = (EditText) view.findViewById(R.id.DEAL_BIGO);


//        final Spinner spinner2 = (Spinner)view.findViewById(R.id.spinner2);
//        final String text2 = spinner2.getSelectedItem().toString(); // 시군. 스피너에서 선택된 항목의 값을 text2로 받음
        Button mRegister_btn = (Button) view.findViewById(R.id.Register_btn);
        Button check_btn = (Button) view.findViewById(R.id.check);
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ADDR=edit_ADDR.getText().toString();
                new BackgroundTask().execute(ADDR);
            }
        });
        mRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ADDR =edit_ADDR.getText().toString();
                String OWNER_NM =edit_OWNER_NM.getText().toString();
                String OWNER_CONTACT =edit_OWNER_CONTACT.getText().toString();
                String DEAL_AMOUNT =edit_DEAL_AMOUNT.getText().toString();
                String DEAL_BIGO =edit_DEAL_BIGO.getText().toString();

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
                String REG_DT = df.format(new Date());

                Log.e(this.getClass().getName(),"현재시각!"+REG_DT);

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
                MemoRequest memoRequest =new MemoRequest(choice_do,choice_se,ADDR,OWNER_NM,OWNER_CONTACT,DEAL_AMOUNT,choice_deal_type,DEAL_BIGO,choice_gubun,REG_DT,responseListener);
                RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(memoRequest);
            }
        });



        final Spinner spin1 = (Spinner)view.findViewById(R.id.spinner1);
        final Spinner spin2 = (Spinner)view.findViewById(R.id.spinner2);

        final Spinner spin3 = (Spinner)view.findViewById(R.id.DEAL_TYPE);

        adspin3 = ArrayAdapter.createFromResource(getContext(),R.array.maemae, android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(adspin3);
        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choice_deal_type = adspin3.getItem(i).toString();//선택된 값을 넣습니다.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner spin4 = (Spinner)view.findViewById(R.id.GUBUN);

        adspin4 = ArrayAdapter.createFromResource(getContext(),R.array.gubun, android.R.layout.simple_spinner_dropdown_item);
        spin4.setAdapter(adspin4);
        spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choice_gubun = adspin4.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        Button btn_refresh = (Button)findViewById(R.id.btn_refresh);//xml과 class에 변수들을 연결해줍니다. final를 사용한 이유는 spin2가 함수안에서 사용되기 때문에 코딩전체로 선언한 것입니다.
        adspin1 = ArrayAdapter.createFromResource(getContext(), R.array.sido, android.R.layout.simple_spinner_dropdown_item);//첫번째 어댑터에 값을 넣습니다. this=는 현재class를 의미합니다.
        // R.array.spinner_do는 이곳에 도시를 다 쓸 경우 코딩이 길어지기 때문에 value->string.xml에 따로 String값들을 선언해두었습니다.
        // R.layout.simple_~~~는 안드로이드에서 기본제공하는 spinner 모양입니다. 다른것도 있는데 비슷합니다.
        // adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//이부분이 정확히 말로 설명을 못하겠습니다. 아무튼 필요합니다. 헤헤 고수분들 도와주세요.
        spin1.setAdapter(adspin1);
//어댑터에 값들을 spinner에 넣습니다. 여기까지 하시면 첫번째 spinner에 값들이 들어갈 것입니다.
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//첫번째 spinner 클릭시 이벤트 발생입니다. setO 정도까지 치시면 자동완성됩니다. 뒤에도 마찬가지입니다.
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {//제대로 자동완성하셨다면 이부분이 자동으로 만들어 질 것입니다. int i는 포지션이라 하여 제가 spinner에 몇번째걸 선택했는지 값이 들어갑니다. 필요하겠죠? ㅎㅎ
                if (adspin1.getItem(i).equals("서울")) {//spinner에 값을 가져와서 i 보이시나요 제가 클릭 한것이 서울인지 확인합니다.
                    choice_do = "서울";//버튼 클릭시 출력을 위해 값을 넣었습니다.
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_seoul, android.R.layout.simple_spinner_dropdown_item);//서울일 경우에 두번째 spinner에 값을 넣습니다.
                    // 그냥 this가 아닌 Main~~~인 이유는 그냥 this는 메인엑티비티인 경우만 가능합니다.
                    // 지금과 같이 다른 함수안이나 다른 클래스에서는 꼭 자신의 것을 넣어주어야 합니다.
                    // 혹시나 다른 class -> Public View밑에서 작업하시는 분은 view명.getContext()로 해주셔야 합니다.
                    // 예로 View rootView =~~ 선언하신 경우에는 rootView.getContext()써주셔야합니다. this가 아니라요.
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);//두번째 어댑터값을 두번째 spinner에 넣었습니다.
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//저희는 두번째 선택된 값도 필요하니 이안에 두번째 spinner 선택 이벤트를 정의합니다.
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();//두번째 선택된 값을 choice_se에 넣습니다.
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("인천")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "인천";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_incheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("부산")) {
                    choice_do = "부산";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_busan, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }

                else if (adspin1.getItem(i).equals("대구")) {
                    choice_do = "대구";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_daegu, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }


                else if (adspin1.getItem(i).equals("대전")) {
                    choice_do = "대전";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_daejeon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("광주")) {
                    choice_do = "광주";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_gwangju, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("울산")) {
                    choice_do = "울산";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_ulsan, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("경기도")) {
                    choice_do = "경기도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("충청북도")) {
                    choice_do = "충청북도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_chungcheongbuk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("충청남도")) {
                    choice_do = "충청남도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_chungcheongnam, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("전라북도")) {
                    choice_do = "전라북도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_jeollabuk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("전라남도")) {
                    choice_do = "전라남도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_jeollanam, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("경상북도")) {
                    choice_do = "경상북도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_gyeongsangbuk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("경상남도")) {
                    choice_do = "경상남도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_gyeongsangnam, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("강원도")) {
                    choice_do = "강원도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_gangwon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (adspin1.getItem(i).equals("제주도")) {
                    choice_do = "제주도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_jeju, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        btn_refresh.setOnClickListener(new View.OnClickListener() {//버튼 클릭시 이벤트입니다.




        return view;
    }


        class BackgroundTask extends AsyncTask<String, Void, String> {
            String target;

            ProgressDialog progressDialog = new ProgressDialog(getContext());
            @Override
            protected void onPreExecute() {
                target = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=1&countPerPage=10&confmKey=U01TX0FVVEgyMDE5MDYzMDIxMTIyMDEwODg0NjA=&resultType=json&keyword=";
            }


            @Override
            protected String doInBackground(String... params) {
                try {
                    String ADDR=params[0];
                    target=target+ URLEncoder.encode(ADDR);
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
                    JSONObject jsonObject1 = jsonObject.getJSONObject("results");
                    JSONArray jsonArray = jsonObject1.getJSONArray("juso");
                    Log.e(this.getClass().getName(), String.valueOf(jsonArray));
                    if(jsonArray.length()==0)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                        alert.setTitle("상세주소를  ");
                        alert.setMessage("입력해주세요.");
                        alert.setNegativeButton("확인",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Fragment fragment = new MemoFragment();
                                replaceFragment(fragment);
                            }
                        });
                        alert.show();
                    }
                    int count = 0;
                    while(count < jsonArray.length()){
                        JSONObject object = jsonArray.getJSONObject(count);
                        // 상세 주소 입력을 위해
                        if(object.getString("roadAddr")==null)
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                            alert.setTitle("입력하신 주소는 ");
                            alert.setMessage("존재 하지 않습니다.");
                            alert.setNegativeButton("확인",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Fragment fragment = new MemoFragment();
                                    replaceFragment(fragment);
                                }
                            });
                            alert.show();
                        }
                        else if(object.getString("roadAddr")!=null){
                            check_add_array.setroadAddr(object.getString("roadAddr")); //도로명
                            check_add_array.setjibunAddr(object.getString("jibunAddr")); //지번주소
                            String roadAddr=object.getString("roadAddr");
                            String jibunAddr=object.getString("jibunAddr");
                            String admCd=object.getString("admCd");
                            String rnMgtSn=object.getString("rnMgtSn");
                            String udrtYn=object.getString("udrtYn");
                            String buldMnnm=object.getString("buldMnnm");
                            String buldSlno=object.getString("buldSlno");
                            Log.e(this.getClass().getName(), roadAddr);
                            Log.e(this.getClass().getName(),jibunAddr);
                            Log.e(this.getClass().getName(), admCd);
                            Log.e(this.getClass().getName(), rnMgtSn);
                            Log.e(this.getClass().getName(), udrtYn);
                            Log.e(this.getClass().getName(), buldMnnm);
                            Log.e(this.getClass().getName(), buldSlno);

                            check_add_array.setadmCd(object.getString("admCd"));
                            check_add_array.setrnMgtSn(object.getString("rnMgtSn"));
                            check_add_array.setudrtYn(object.getString("udrtYn"));
                            check_add_array.setbuldMnnm(object.getString("buldMnnm"));
                            check_add_array.setbuldSlno(object.getString("buldSlno"));
                            // 좌표변환을 위해 필요한것들

                            //new BackgroundTask2().execute(admCd,rnMgtSn,udrtYn,buldMnnm,buldSlno);
                        }

                        count++;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

            }

        }








    class BackgroundTask2 extends AsyncTask<String, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://www.juso.go.kr/addrlink/addrCoordApi.do?currentPage=1&countPerPage=10&confmKey=U01TX0FVVEgyMDE5MDYzMDIzMDczNjEwODg0NjI=&resultType=json";
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String admCd=params[0];
                String rnMgtSn=params[1];
                String udrtYn=params[2];
                Integer buldMnnm= Integer.valueOf((params[3]));
                Integer buldSlno= Integer.valueOf((params[4]));

                target=target+"&admCd="+URLEncoder.encode(admCd)+"&rnMgtSn="+URLEncoder.encode(rnMgtSn)+"&udrtYn="+URLEncoder.encode(udrtYn)+"&buldMnnm="+buldMnnm+"&buldSlno="+buldSlno;
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

            try {
                Log.e(this.getClass().getName(), "BackgroundTask2");
                JSONObject jsonObject = new JSONObject(res);
                //Log.e(this.getClass().getName(), String.valueOf(jsonObject));
                JSONObject jsonObject1 = jsonObject.getJSONObject("results");
                JSONArray jsonArray = jsonObject1.getJSONArray("juso");
               // Log.e(this.getClass().getName(), String.valueOf(jsonArray));
                int count = 0;
                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);

                    // 상세 주소 입력을 위해
                    //check_add_array.setroadAddr(object.getString("roadAddr")); //도로명
                   // check_add_array.setjibunAddr(object.getString("jibunAddr")); //지번주소
                    float entX= Float.parseFloat(object.getString("entX"));
                    float entY= Float.parseFloat(object.getString("entY"));
                    Utmk utmk = new Utmk(entX, entY);
                    LatLng latLng = utmk.toLatLng();
                    double latitude=(latLng.latitude);
                    double longitude=(latLng.longitude);


                    check_add_array.setlatitude(String.valueOf(latitude));
                    check_add_array.setlongitude(String.valueOf(longitude));
                    // 좌표변환을 위해 필요한것들
                   // Log.e(this.getClass().getName(), String.valueOf(latitude));
                   // Log.e(this.getClass().getName(), String.valueOf(longitude));
                    Log.e(this.getClass().getName(), String.valueOf(check_add_array.getlatitude(count)));
                    Log.e(this.getClass().getName(), String.valueOf(check_add_array.getlongitude(count)));
                    count++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    private void check_add() {
        for(int i=0;i<check_add_array.getsize();i++){
            Log.e(this.getClass().getName(), i+"@@@@@@@@@"+check_add_array.getroadAddr(i));
            Log.e(this.getClass().getName(), i+"@@@@@@@@@"+check_add_array.getlatitude(i));
            Log.e(this.getClass().getName(), i+"@@@@@@@@@"+check_add_array.getlongitude(i));
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
    private void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container2, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBack() {
        Fragment fragment = new Gyesi();
        replaceFragment(fragment);
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