package com.example.nongsa2;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MemoRequest1 extends StringRequest {
    final static private String URL ="http://dbwo4011.cafe24.com/migration/addgarden.php";
    private Map<String, String> parameters;

    public MemoRequest1(String FARM_TYPE, String FARM_NM, String ADDRESS1,
                        String SELL_AREA_INFO, String HOMEPAGE, String OFF_SITE,
                        String APPLY_MTHD, String PRICE, String REGIST_DT, String ID,
                        String Name, String phone, String AREA_LNM, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters=new HashMap<>();
        parameters.put("FARM_TYPE",FARM_TYPE);
        parameters.put("FARM_NM",FARM_NM);
        parameters.put("ADDRESS1",ADDRESS1);
        parameters.put("SELL_AREA_INFO",SELL_AREA_INFO);
        parameters.put("HOMEPAGE",HOMEPAGE);
        parameters.put("OFF_SITE",OFF_SITE);
        parameters.put("APPLY_MTHD",APPLY_MTHD);
        parameters.put("PRICE",PRICE);
        parameters.put("REGIST_DT",REGIST_DT);
        parameters.put("ID",ID);
        parameters.put("Name",Name);
        parameters.put("phone",phone);
        parameters.put("AREA_LNM",AREA_LNM);

        Log.e(this.getClass().getName(),"정보 등록!");

    }
    public Map<String, String> getParams() { return parameters; }
}