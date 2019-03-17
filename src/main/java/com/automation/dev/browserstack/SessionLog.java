package com.automation.dev.browserstack;

import com.automation.dev.utils.PropertiesUtil;
import org.json.JSONArray;
import org.json.JSONObject;

public class SessionLog {

    private static final PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();

    public static String getSessionUrl(String sessionId){
        String buildId = getBrowserStackBuildId();
        return API.getSessionInfoUrls(buildId, sessionId).getJSONObject("automation_session").get("public_url").toString();
    }

    private static String getBrowserStackBuildId(){
        JSONArray builds = API.getBrowserStackBuilds();
        for(int i = 0; i < builds.length(); i++){
            JSONObject build = builds.getJSONObject(i).getJSONObject("automation_build");
            if(build.get("name").toString().equals(propertiesUtil.getValue("browserStackBuild"))){
                return build.get("hashed_id").toString();
            }
        }
        return null;
    }
}
