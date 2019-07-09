package com.example.nongsa2;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL ="http://dbwo4011.cafe24.com/migration/Login.php";
    private Map<String, String> parameters;

    public LoginRequest(String ID, String PW, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        parameters.put("ID",ID);
        parameters.put("PW",PW);

        Log.e(this.getClass().getName(),"@@@@@@@@@@@@@@@@@@!"+Static_setting.ID);
        Log.e(this.getClass().getName(),"@@@@@@@@@@@@@@@@@@!!"+Static_setting.PW);
        Log.e(this.getClass().getName(),"@@@@@@@@@@@@@@@@@@!!"+Static_setting.Name);
        Log.e(this.getClass().getName(),"@@@@@@@@@@@@@@@@@@!!"+Static_setting.Phone);
    }


    public Map<String, String> getParams(){
        return parameters;
    }
}
