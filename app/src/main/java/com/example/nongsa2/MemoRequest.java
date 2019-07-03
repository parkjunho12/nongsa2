package com.example.nongsa2;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MemoRequest extends StringRequest {
    final static private String URL ="http://dbwo4011.cafe24.com/migration/memo.php";
    private Map<String, String> parameters;

    public MemoRequest(String SIDO_NM, String SIGUN_NM, String ADDR, String OWNER_NM, String OWNER_CONTACT, String DEAL_AMAUNT,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters=new HashMap<>();
        parameters.put("SIDO_NM",SIDO_NM);
        parameters.put("SIGUN_NM",SIGUN_NM);
        parameters.put("ADDR",ADDR);
        parameters.put("OWNER_NM",OWNER_NM);
        parameters.put("OWNER_CONTACT",OWNER_CONTACT);
        parameters.put("DEAL_AMAUNT",DEAL_AMAUNT);

        Log.e(this.getClass().getName(),"정보 등록!");
        Log.e(this.getClass().getName(),"시도명!"+SIDO_NM);
        Log.e(this.getClass().getName(),"시군명!"+SIDO_NM);
        Log.e(this.getClass().getName(),"상세주소!"+SIDO_NM);
        Log.e(this.getClass().getName(),"소유주!"+OWNER_NM);
        Log.e(this.getClass().getName(),"소유주 전화번호!"+OWNER_CONTACT);
        Log.e(this.getClass().getName(),"희망거래가!"+DEAL_AMAUNT);
    }
    public Map<String, String> getParams() { return parameters; }
}