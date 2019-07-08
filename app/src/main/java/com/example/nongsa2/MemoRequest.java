package com.example.nongsa2;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MemoRequest extends StringRequest {
    final static private String URL ="http://dbwo4011.cafe24.com/migration/memo.php";
    private Map<String, String> parameters;

    public MemoRequest(String ID, String SIDO_NM, String SIGUN_NM, String ADDR, String OWNER_NM, String OWNER_CONTACT, String DEAL_AMOUNT, String DEAL_TYPE, String DEAL_BIGO, String GUBUN, String REG_DT,String latitude,String longitude, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        
        parameters=new HashMap<>();
        parameters.put("ID",ID);
        parameters.put("SIDO_NM",SIDO_NM);
        parameters.put("SIGUN_NM",SIGUN_NM);
        parameters.put("ADDR",ADDR);
        parameters.put("OWNER_NM",OWNER_NM);
        parameters.put("OWNER_CONTACT",OWNER_CONTACT);
        parameters.put("DEAL_AMOUNT",DEAL_AMOUNT);
        parameters.put("DEAL_TYPE",DEAL_TYPE);
        parameters.put("DEAL_BIGO",DEAL_BIGO);
        parameters.put("GUBUN",GUBUN);
        parameters.put("REG_DT",REG_DT);
        parameters.put("latitude",latitude);
        parameters.put("longitude",longitude);

        Log.e(this.getClass().getName(),"정보 등록!");
        Log.e(this.getClass().getName(),"시도명!"+SIDO_NM);
        Log.e(this.getClass().getName(),"시군명!"+SIDO_NM);
        Log.e(this.getClass().getName(),"상세주소!"+SIDO_NM);
        Log.e(this.getClass().getName(),"소유주!"+OWNER_NM);
        Log.e(this.getClass().getName(),"소유주 전화번호!"+OWNER_CONTACT);
        Log.e(this.getClass().getName(),"희망거래가!"+DEAL_TYPE);
    }
    public Map<String, String> getParams() { return parameters; }
}