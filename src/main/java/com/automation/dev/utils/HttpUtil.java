package com.automation.dev.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.util.Map;

public class HttpUtil {

    public static String establishRequest(String urlAPI, Map<String,String> headers){

        try{
            HttpResponse<String> response = Unirest.get(urlAPI)
                    .headers(headers)
                    .asString();
            return response.getBody();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
