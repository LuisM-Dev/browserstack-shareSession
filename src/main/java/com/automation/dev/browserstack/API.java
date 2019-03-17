package com.automation.dev.browserstack;

import com.automation.dev.utils.HttpUtil;
import com.automation.dev.utils.PropertiesUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class API {
    private static final PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
    private static final String BROWSERSTACK_AUTOMATE_URL = "https://www.browserstack.com/automate";
    private static final String BROWSERSTACK_SESSION_URL = "https://automate.browserstack.com";

    private static Map<String,String> headers;

    static {

        try{
            String encoding = Base64.getEncoder().encodeToString((propertiesUtil.getValue("browserStackUsername") + ":" + propertiesUtil.getValue("browserStackPassword")).getBytes(StandardCharsets.UTF_8));
            headers = new HashMap<>();
            headers.put("Content-Type","application/json");
            headers.put("Authorization","Basic " + encoding);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static JSONObject getSessionInfoUrls(String buildId, String sessionId){
        return new JSONObject(Objects.requireNonNull(HttpUtil.establishRequest(BROWSERSTACK_SESSION_URL + "/builds/" + buildId + "/sessions/" + sessionId + ".json", headers)));
    }

    public static JSONArray getBrowserStackBuilds(){
        return new JSONArray(Objects.requireNonNull(HttpUtil.establishRequest(BROWSERSTACK_AUTOMATE_URL + "/builds.json", headers)));
    }
}
