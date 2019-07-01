package com.example.nongsa2;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest  extends StringRequest {

    final static private String URL ="http://dbwo4011.cafe24.com/migration/Register.php";
    private Map<String, String> parameters;

    public  RegisterRequest(String ID, String PW, String Name, String Phone, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        parameters.put("ID",ID);
        parameters.put("PW",PW);
        parameters.put("Name",Name);
        parameters.put("Phone",Phone);

        Log.e(this.getClass().getName(),"회원등록!");
        Log.e(this.getClass().getName(),"ID!"+ID);
        Log.e(this.getClass().getName(),"PW!"+PW);
        Log.e(this.getClass().getName(),"Name!"+Name);
        Log.e(this.getClass().getName(),"Phone!"+Phone);

    }


    public Map<String, String> getParams(){
        return parameters;
    }
}
