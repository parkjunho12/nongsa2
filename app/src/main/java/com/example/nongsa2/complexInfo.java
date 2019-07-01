package com.example.nongsa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class complexInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =getIntent();
        setContentView(R.layout.activity_complex_info);
        TextView textView = (TextView) findViewById(R.id.infos);
        TextView textView2 = (TextView) findViewById(R.id.titles);
        TextView textView3 = (TextView) findViewById(R.id.dates);
        TextView textView4 = (TextView) findViewById(R.id.infos2);
        TextView textView5 = (TextView) findViewById(R.id.infos3);
        WebView webView =(WebView) findViewById(R.id.webview);




        if(intent.getStringExtra("FILE_PATH1").equals("")||intent.getStringExtra("FILE_PATH1").equals(null))
        {
            textView5.setText("사진 없음");
        }
        else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);////웹뷰 쓸때 쓰는것
            webView.loadUrl(intent.getStringExtra("FILE_PATH1"));
            webView.setWebViewClient(new WebViewClientClass());
            textView5.setText("");

        }
        if(intent.getStringExtra("FILE_PATH2").equals("")||intent.getStringExtra("FILE_PATH1").equals(null))
        {
            textView5.setText("사진 없음");
        }
        else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);////웹뷰 쓸때 쓰는것
            webView.loadUrl(intent.getStringExtra("FILE_PATH2"));
            webView.setWebViewClient(new WebViewClientClass());
            textView5.setText("");

        }
        if(intent.getStringExtra("FILE_PATH3").equals("")||intent.getStringExtra("FILE_PATH1").equals(null))
        {
            textView5.setText("사진 없음");
        }
        else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);////웹뷰 쓸때 쓰는것
            webView.loadUrl(intent.getStringExtra("FILE_PATH2"));
            webView.setWebViewClient(new WebViewClientClass());
            textView5.setText("");

        }

        String title =intent.getStringExtra("GUBUN")+"("+intent.getStringExtra("DEAL_TYPE")+")";

        String content =
                "ID: "+intent.getStringExtra("ID")+"\n ADDR: "+intent.getStringExtra("ADDR")+"\nDEAL_AMOUNT: "+intent.getStringExtra("DEAL_AMOUNT")+"\nDEAL_BIGO: "+intent.getStringExtra("DEAL_BIGO")
                +"\nBUILDING_AREA: "+intent.getStringExtra("BUILDING_AREA")+"\nAREA_ETC: "+intent.getStringExtra("AREA_ETC")+"\nBUILD_YEAR: "+intent.getStringExtra("BUILD_YEAR")+"\nVACANT_YEAR: "+intent.getStringExtra("VACANT_YEAR");
        String content2=
                "STRUCT_TYPE: "+intent.getStringExtra("STRUCT_TYPE")+"\nOWNER_NM:"+intent.getStringExtra("OWNER_NM")+"\nOWNER_CONTACT: "+intent.getStringExtra("OWNER_CONTACT")+"\nINSPECTOR: "+intent.getStringExtra("INSPECTOR")
                +"\nLOT_AREA: "+intent.getStringExtra("LOT_AREA")+"\nBIGO:"+intent.getStringExtra("BIGO")+"\nDEAL_NEGO_YN: "+intent.getStringExtra("DEAL_NEGO_YN");


        String date ="등록일 : "+intent.getStringExtra("REG_DT");
        textView4.setText(content2);
        textView.setText(content);
        textView2.setText(title);
        textView3.setText(date);
    }
    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void bakku(){


    }
}

