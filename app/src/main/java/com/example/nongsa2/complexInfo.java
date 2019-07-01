package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        setContentView(R.layout.activity_complex_info);
        TextView textView = (TextView) findViewById(R.id.infos);
        TextView textView2 = (TextView) findViewById(R.id.titles);
        TextView textView3 = (TextView) findViewById(R.id.dates);
        TextView textView4 = (TextView) findViewById(R.id.infos2);
        TextView textView5 = (TextView) findViewById(R.id.infos3);
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
            textView5.setText("사진 없음");
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);////웹뷰 쓸때 쓰는것
            webView.loadUrl(intent.getStringExtra("FILE_PATH1"));
            webView.setWebViewClient(new WebViewClientClass());
            textView5.setText("");

        }
        if (intent.getStringExtra("FILE_PATH2").equals("") || intent.getStringExtra("FILE_PATH1").equals(null)) {
            textView5.setText("사진 없음");
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);////웹뷰 쓸때 쓰는것
            webView.loadUrl(intent.getStringExtra("FILE_PATH2"));
            webView.setWebViewClient(new WebViewClientClass());
            textView5.setText("");

        }
        if (intent.getStringExtra("FILE_PATH3").equals("") || intent.getStringExtra("FILE_PATH1").equals(null)) {
            textView5.setText("사진 없음");
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);////웹뷰 쓸때 쓰는것
            webView.loadUrl(intent.getStringExtra("FILE_PATH2"));
            webView.setWebViewClient(new WebViewClientClass());
            textView5.setText("");

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
        textView4.setText(content2);
        textView.setText(content);
        textView2.setText(title);
        textView3.setText(date);
        new BackgroundTask().execute();


    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    class BackgroundTask extends AsyncTask<String, Void, String> {
        String target;
        @Override
        protected void onPreExecute() {

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
                Log.e(this.getClass().getName(), "백그라운드 try문안으로");
                JSONObject jsonObject = new JSONObject(res);
                Log.e(this.getClass().getName(), "백그라운드 try문안으로");
                JSONArray jsonArray = jsonObject.getJSONArray("response");


                int count = 0;


                while(count < jsonArray.length())
                {
                    Log.e(this.getClass().getName(), "들어오긴하냐?");
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
        }


    }

    public void end(){
        int i=city_info_array.getListSize();
        float min=distFrom(Float.parseFloat(Lat1), Float.parseFloat(Long1), Float.parseFloat(city_info_array.getLatitude(0)), Float.parseFloat(city_info_array.getIongitude(0)));
        int index=0;
        for(int j=0;j<i;j++)
        {
            if(city_info_array.getLatitude(j).equals("0")&&city_info_array.getIongitude(j).equals("0"))
            {


            }
            else {


                    if(min>distFrom(Float.parseFloat(Lat1), Float.parseFloat(Long1), Float.parseFloat(city_info_array.getLatitude(j)), Float.parseFloat(city_info_array.getIongitude(j))))
                    {

                        min=distFrom(Float.parseFloat(Lat1), Float.parseFloat(Long1), Float.parseFloat(city_info_array.getLatitude(j)), Float.parseFloat(city_info_array.getIongitude(j)));

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

                        }


            }
           Log.e(this.getClass().getName(), ""+city_info_array.getLatitude(j));
            Log.e(this.getClass().getName(), ""+city_info_array.getIongitude(j));

            TextView textView =(TextView) findViewById(R.id.titles);

            textView.setText(VILL_NM);
        }


    }
    public float bigyo(float a, float b)
    {

        if(a<b)
            return a;
        else
            return b;

    }

}