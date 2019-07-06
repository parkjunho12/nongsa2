package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class complexInfo extends AppCompatActivity {
static int comctn=0;

    String Long1;
    String Lat1;
    String VILL_ID="";
    String VILL_NM="";
    String VILL_NATURE_RESOURCE="";
    String VILL_ECONOMY_RESOURCE="";
    String VILL_NATURE_RESOURCE1="";
    String VILL_NATURE_RESOURCE2="";
    String VILL_ECONOMY_RESOURCE1="";
    String VILL_ECONOMY_RESOURCE2="";
    String VILL_ECONOMY_RESOURCE3="";
    String VILL_COMMUNITY_RESOURCE="";
    String VILL_COMMUNITY_RESOURCE1="";
    String VILL_COMMUNITY_RESOURCE2="";
    String VILL_COMMUNITY_RESOURCE3="";
    String VILL_COMMUNITY_RESOURCE4="";
    String DEAL_TYPE="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        setContentView(R.layout.activity_complex_info);
        TextView hoo =(TextView)findViewById(R.id.hoo1);
        TextView hoo2 = (TextView)findViewById(R.id.hoo2);

        TextView textView = (TextView) findViewById(R.id.one);
        TextView textView2 = (TextView) findViewById(R.id.two);
        TextView textView3 = (TextView) findViewById(R.id.three);
        TextView textView4 = (TextView) findViewById(R.id.four);
        TextView textView5 = (TextView) findViewById(R.id.five);
        TextView textView6 = (TextView) findViewById(R.id.six);
       // TextView textView7 = (TextView) findViewById(R.id.seven);
       // TextView textView8 = (TextView) findViewById(R.id.eight);
        TextView textView9 = (TextView) findViewById(R.id.nine);

        TextView textView11 = (TextView) findViewById(R.id.eleven);
        TextView textView12 = (TextView) findViewById(R.id.twelve);
        TextView infos3 = (TextView) findViewById(R.id.infos3);
        TextView dates = (TextView) findViewById(R.id.dates);
        WebView webView = (WebView) findViewById(R.id.webview);
        Button button = (Button) findViewById(R.id.back2);
        Button complex = (Button)findViewById(R.id.complex);

        complex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MoreComplex.class);

                intent.putExtra("VILL_ID",VILL_ID);
                intent.putExtra("VILL_NM",VILL_NM);
                intent.putExtra("VILL_NATURE_RESOURCE",VILL_NATURE_RESOURCE);
                intent.putExtra("VILL_ECONOMY_RESOURCE",VILL_ECONOMY_RESOURCE);
                intent.putExtra("VILL_NATURE_RESOURCE1",VILL_NATURE_RESOURCE1);
                intent.putExtra("VILL_NATURE_RESOURCE2",VILL_NATURE_RESOURCE2);
                intent.putExtra("VILL_ECONOMY_RESOURCE1",VILL_ECONOMY_RESOURCE1);
                intent.putExtra("VILL_ECONOMY_RESOURCE2",VILL_ECONOMY_RESOURCE2);
                intent.putExtra("VILL_ECONOMY_RESOURCE3",VILL_ECONOMY_RESOURCE3);
                intent.putExtra("VILL_COMMUNITY_RESOURCE",VILL_COMMUNITY_RESOURCE);
                intent.putExtra("VILL_COMMUNITY_RESOURCE1",VILL_COMMUNITY_RESOURCE1);
                intent.putExtra("VILL_COMMUNITY_RESOURCE2",VILL_COMMUNITY_RESOURCE2);
                intent.putExtra("VILL_COMMUNITY_RESOURCE3",VILL_COMMUNITY_RESOURCE3);
                intent.putExtra("VILL_COMMUNITY_RESOURCE4",VILL_COMMUNITY_RESOURCE4);


                startActivity(intent);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        if (intent.getStringExtra("FILE_PATH1").equals("") || intent.getStringExtra("FILE_PATH1").equals(null)) {
            infos3.setText("사진 없음");
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);////웹뷰 쓸때 쓰는것
            webView.loadUrl(intent.getStringExtra("FILE_PATH1"));
            webView.setWebViewClient(new WebViewClientClass());
            infos3.setText("");

        }
        if (intent.getStringExtra("FILE_PATH2").equals("") || intent.getStringExtra("FILE_PATH2").equals(null)) {
            infos3.setText("사진 없음");
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);////웹뷰 쓸때 쓰는것
            webView.loadUrl(intent.getStringExtra("FILE_PATH2"));
            webView.setWebViewClient(new WebViewClientClass());
            infos3.setText("");

        }
        if (intent.getStringExtra("FILE_PATH3").equals("") || intent.getStringExtra("FILE_PATH3").equals(null)) {
            infos3.setText("사진 없음");
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);////웹뷰 쓸때 쓰는것
            webView.loadUrl(intent.getStringExtra("FILE_PATH3"));
            webView.setWebViewClient(new WebViewClientClass());
            infos3.setText("");

        }
        DEAL_TYPE=intent.getStringExtra("GUBUN") + "(" + intent.getStringExtra("DEAL_TYPE") + ")";
        if(DEAL_TYPE.substring(0,2).equals("농지"))
        {
            hoo.setText("농지 상세정보");
            hoo2.setText("농지 검색");
        }
        else
        {
            hoo.setText("빈집 상세정보");
            hoo2.setText("빈집 검색");
        }
         Lat1 = intent.getStringExtra("Latitude");
         Long1 = intent.getStringExtra("Longtitude");

        String title = intent.getStringExtra("GUBUN") + "(" + intent.getStringExtra("DEAL_TYPE") + ")";

        String content =
                "ID: " + intent.getStringExtra("ID") + "\n ADDR: " + intent.getStringExtra("ADDR") + "\nDEAL_AMOUNT: " + intent.getStringExtra("DEAL_AMOUNT") + "\nDEAL_BIGO: " + intent.getStringExtra("DEAL_BIGO")
                        + "\nBUILDING_AREA: " + intent.getStringExtra("BUILDING_AREA") + "\nAREA_ETC: " + intent.getStringExtra("AREA_ETC") + "\nBUILD_YEAR: " + intent.getStringExtra("BUILD_YEAR") + "\nVACANT_YEAR: " + intent.getStringExtra("VACANT_YEAR");
        String content2 =
                "STRUCT_TYPE: " + intent.getStringExtra("STRUCT_TYPE") + "\nOWNER_NM:" + intent.getStringExtra("OWNER_NM") + "\nOWNER_CONTACT: " + intent.getStringExtra("OWNER_CONTACT") + "\nINSPECTOR: " + intent.getStringExtra("INSPECTOR")
                        + "\nLOT_AREA: " + intent.getStringExtra("LOT_AREA") + "\nBIGO:" + intent.getStringExtra("BIGO") + "\nDEAL_NEGO_YN: " + intent.getStringExtra("DEAL_NEGO_YN");

        String date = "등록일 : " + intent.getStringExtra("REG_DT");
      textView.setText(intent.getStringExtra("OWNER_NM"));
        textView2.setText(intent.getStringExtra("OWNER_CONTACT"));
        String c =intent.getStringExtra("DEAL_AMOUNT");
        if(c.equals("NULL")||c.equals("null")||c.equals("")||c.equals("0"))
        {
            c="정보가 없습니다.";

        }
        textView3.setText(c);
        textView4.setText(intent.getStringExtra("ADDR"));
        String b=intent.getStringExtra("DEAL_NEGO_YN");
        if(b.equals("NULL")||b.equals("null")||b.equals(""))
        {
            b="정보가 없습니다.";

        }
        textView5.setText(b);

     //   textView5.setText(intent.getStringExtra("DEAL_NEGO_YN"));
        textView6.setText(title);
        //textView7.setText(intent.getStringExtra("REG_DT"));
       // textView8.setText(intent.getStringExtra("VACANT_YEAR"));
        String a=intent.getStringExtra("AREA_ETC");
        if(a.equals("NULL")||a.equals("null")||a.equals("")||a.equals("0"))
        {
            a="정보가 없습니다.";
            textView9.setText(a);
        }
        else if(a.contains("㎡"))
        textView9.setText(a);
        else
        {
            a=a+"㎡";
            textView9.setText(a);
        }

        textView11.setText(intent.getStringExtra("ID"));
        textView12.setText(intent.getStringExtra("BIGO"));

        dates.setText(date);

        if(comctn==0)
        {
            new BackgroundTask().execute();
            comctn++;
        }
        else
        {
            end();
        }

    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }



    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }

        return (dist);
    }



    class BackgroundTask extends AsyncTask<String, Void, String> {

        String target;
        customprogress progressDialog = new customprogress(complexInfo.this);

        @Override
        protected void onPreExecute() {
            progressDialog.setCancelable(true);
            progressDialog .getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.show();

            target = "http://dbwo4011.cafe24.com/migration/city_info_request.php";

        }


        @Override
        protected String doInBackground(String... strings) {
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
                JSONObject jsonObject = new JSONObject(res);
                Log.e(this.getClass().getName(), "백그라운드 try문안으로");
                JSONArray jsonArray = jsonObject.getJSONArray("response");


                int count = 0;


                while(count < jsonArray.length())
                {

                    JSONObject object = jsonArray.getJSONObject(count);
                    city_info_array.setVILL_ID(object.getString("VILL_ID"));
                    city_info_array.setVILL_NM(object.getString("VILL_NM"));
                    city_info_array.setVILL_NATURE_RESOURCE(object.getString("VILL_NATURE_RESOURCE"));
                    city_info_array.setVILL_ECONOMY_RESOURCE(object.getString("VILL_ECONOMY_RESOURCE"));
                    city_info_array.setVILL_NATURE_RESOURCE1(object.getString("VILL_NATURE_RESOURCE1"));
                    city_info_array.setVILL_NATURE_RESOURCE2(object.getString("VILL_NATURE_RESOURCE2"));
                    city_info_array.setVILL_ECONOMY_RESOURCE1(object.getString("VILL_ECONOMY_RESOURCE1"));
                    city_info_array.setVILL_ECONOMY_RESOURCE2(object.getString("VILL_ECONOMY_RESOURCE2"));
                    city_info_array.setVILL_ECONOMY_RESOURCE3(object.getString("VILL_ECONOMY_RESOURCE3"));
                    city_info_array.setVILL_COMMUNITY_RESOURCE(object.getString("VILL_COMMUNITY_RESOURCE"));
                    city_info_array.setVILL_COMMUNITY_RESOURCE1(object.getString("VILL_COMMUNITY_RESOURCE1"));
                    city_info_array.setVILL_COMMUNITY_RESOURCE2(object.getString("VILL_COMMUNITY_RESOURCE2"));
                    city_info_array.setVILL_COMMUNITY_RESOURCE3(object.getString("VILL_COMMUNITY_RESOURCE3"));
                    city_info_array.setVILL_COMMUNITY_RESOURCE4(object.getString("VILL_COMMUNITY_RESOURCE4"));
                    city_info_array.setLatitude(object.getString("Latitude"));
                    city_info_array.setIongitude(object.getString("Iongitude"));


                    count++;

                }

            } catch (Exception e){
                e.printStackTrace();
            }
            end();
            progressDialog.dismiss();

        }


    }


    public void end(){
        TextView textView10 = (TextView) findViewById(R.id.ten);
        customprogress customprogress = new customprogress(complexInfo.this);

        int i=city_info_array.getListSize();
        Log.e(this.getClass().getName(), "0이에요: "+Double.parseDouble(Lat1)+"\n민민: "+distance(Double.parseDouble(Lat1),Double.parseDouble(Long1),Double.parseDouble(city_info_array.getLatitude(0)),Double.parseDouble(city_info_array.getIongitude(0)),"meter"));
        Double min = distance(Double.parseDouble(Lat1),Double.parseDouble(Long1),Double.parseDouble(city_info_array.getLatitude(0)),Double.parseDouble(city_info_array.getIongitude(0)),"kilometer");
        int index=0;

        customprogress .getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        customprogress.show();
        for(int j=0;j<i;j++)
        {

            if(Lat1.equals("0")&&Long1.equals("0"))
            {
                min=0.0;
                Log.e(this.getClass().getName(), "0이에요");
            }
            else {

              if(min > distance(Double.parseDouble(Lat1),Double.parseDouble(Long1),Double.parseDouble(city_info_array.getLatitude(j)),Double.parseDouble(city_info_array.getIongitude(j)),"kilometer"))
                    {


                        min = distance(Double.parseDouble(Lat1),Double.parseDouble(Long1),Double.parseDouble(city_info_array.getLatitude(j)),Double.parseDouble(city_info_array.getIongitude(j)),"kilometer");
                            VILL_ID =city_info_array.getVILL_ID(j);
                            VILL_NM =city_info_array.getVILL_NM(j);
                            VILL_NATURE_RESOURCE =city_info_array.getVILL_ECONOMY_RESOURCE(j);
                            VILL_ECONOMY_RESOURCE =city_info_array.getVILL_ECONOMY_RESOURCE(j);
                            VILL_NATURE_RESOURCE1 =city_info_array.getVILL_NATURE_RESOURCE1(j);
                            VILL_NATURE_RESOURCE2 =city_info_array.getVILL_NATURE_RESOURCE2(j);
                            VILL_ECONOMY_RESOURCE1 =city_info_array.getVILL_ECONOMY_RESOURCE1(j);
                            VILL_ECONOMY_RESOURCE2 =city_info_array.getVILL_ECONOMY_RESOURCE2(j);
                            VILL_ECONOMY_RESOURCE3 =city_info_array.getVILL_ECONOMY_RESOURCE3(j);
                            VILL_COMMUNITY_RESOURCE =city_info_array.getVILL_COMMUNITY_RESOURCE(j);
                            VILL_COMMUNITY_RESOURCE1 =city_info_array.getVILL_COMMUNITY_RESOURCE1(j);
                            VILL_COMMUNITY_RESOURCE2 =city_info_array.getVILL_COMMUNITY_RESOURCE2(j);
                            VILL_COMMUNITY_RESOURCE3 =city_info_array.getVILL_COMMUNITY_RESOURCE3(j);
                            VILL_COMMUNITY_RESOURCE4 =city_info_array.getVILL_COMMUNITY_RESOURCE4(j);
                        Log.e(this.getClass().getName(), "마을 이름: "+VILL_NM+"\n제일 가까운 좌표:"+min);

                        }
                    else
                    {
                        min=min;
                    }


            }




        }
        if(min==0.0)
        {

            VILL_ID ="위치 정보가 정확 하지 않습니다.";

            VILL_NM ="위치 정보가 정확 하지 않습니다.";
            textView10.setText(VILL_NM);
            VILL_NATURE_RESOURCE ="";
            VILL_ECONOMY_RESOURCE ="";
            VILL_NATURE_RESOURCE1 ="";
            VILL_NATURE_RESOURCE2 ="";
            VILL_ECONOMY_RESOURCE1 ="";
            VILL_ECONOMY_RESOURCE2 ="";
            VILL_ECONOMY_RESOURCE3 ="";
            VILL_COMMUNITY_RESOURCE ="";
            VILL_COMMUNITY_RESOURCE1 ="";
            VILL_COMMUNITY_RESOURCE2 ="";
            VILL_COMMUNITY_RESOURCE3 ="";
            VILL_COMMUNITY_RESOURCE4 ="";
        }
        else if(min<10){
            textView10.setText(VILL_NM);
        }
        else
        {

            VILL_ID ="선택지와 근처 농촌마을 간의 직선거리가 10km이내인 경우만 농촌 마을 상세정보를 제공합니다.";
            VILL_NM ="선택지와 근처 농촌마을 간의 직선거리가 10km\n 이내인 경우만 농촌 마을 상세정보를 제공합니다.";
            textView10.setText("10km 이내만 제공");
            VILL_NATURE_RESOURCE ="";
            VILL_ECONOMY_RESOURCE ="";
            VILL_NATURE_RESOURCE1 ="";
            VILL_NATURE_RESOURCE2 ="";
            VILL_ECONOMY_RESOURCE1 ="";
            VILL_ECONOMY_RESOURCE2 ="";
            VILL_ECONOMY_RESOURCE3 ="";
            VILL_COMMUNITY_RESOURCE ="";
            VILL_COMMUNITY_RESOURCE1 ="";
            VILL_COMMUNITY_RESOURCE2 ="";
            VILL_COMMUNITY_RESOURCE3 ="";
            VILL_COMMUNITY_RESOURCE4 ="";
        }
        customprogress.dismiss();
    }


}