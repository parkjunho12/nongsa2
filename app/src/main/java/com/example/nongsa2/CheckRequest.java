package com.example.nongsa2;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CheckRequest extends StringRequest {

    final static private String URL ="http://dbwo4011.cafe24.com/migration/Check.php";

    private Map<String, String> parameters;

    public CheckRequest(String ID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        parameters.put("ID",ID);


    }


    public Map<String, String> getParams(){
        return parameters;
    }
}
