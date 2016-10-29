package com.springapp.mvc.entity;

import java.util.Map;


public class RequestWrapper {

   /* {“sendTime”:” 12232312313”,”version”:”1.0”,
    ”appType”:”03”,”authToken”:” QYVD8W2JJW4KYDP2YMKWFX36”,
        devId:”fefsdfsdffd”,”data”:{“user”:”root”,”pwd”:”111111”}
        ,”signType”:”01”,”sign”:”qwgggjjmutydfg01gfbdfgfgfdertyujk”}*/

    private String data;
    private String signType;
    private String appType;
    private String version;
    private String authToken;
    private String sign;
    private String devId;
    private String sendTime;

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }






}
