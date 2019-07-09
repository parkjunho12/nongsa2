package com.example.nongsa2;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Modify_Myinfo_Request extends StringRequest {

    final static private String URL ="http://dbwo4011.cafe24.com/migration/Modify_Myinfo_request.php";
    private Map<String, String> parameters;

    public Modify_Myinfo_Request(String M_ID,String ID, String PW,String Name,String Phone, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        parameters.put("M_ID",M_ID);
        parameters.put("ID",ID);
        parameters.put("PW",PW);
        parameters.put("Name",Name);
        parameters.put("Phone",Phone);

        Log.e(this.getClass().getName(),"M_ID!"+M_ID);
        Log.e(this.getClass().getName(),"ID!"+ID);
        Log.e(this.getClass().getName(),"PW!"+PW);
        Log.e(this.getClass().getName(),"Name!"+Name);
        Log.e(this.getClass().getName(),"Phone!"+Phone);


    }


    public Map<String, String> getParams(){
        return parameters;
    }
}