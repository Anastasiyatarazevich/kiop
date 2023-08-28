package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpStatus;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WebHelper {

    public static void getRequest(){
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl("http://185.65.19.35:8080/shapes");

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                HttpStatus status = httpResponse.getStatus();
                if(status.getStatusCode() == 200) {
                    String responseJson = httpResponse.getResultAsString();
                    Gdx.app.log("web response", responseJson);
                } else {
                    System.out.println("Error\nStatus:" + httpResponse.getStatus() +" Response: " + httpResponse);
                }
            }
            public void failed(Throwable t) {
                String error = t.getMessage();
            }
            public void cancelled() {
                // request aborted
            }
        });
    }
    public static void postRequest(int mark, int time) {
        String url = "http://185.65.19.35:31/add?";
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);

        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("mark", mark);
        parameters.put("time", time);
        //todo: create user profile class and get info from that
        parameters.put("uId", 111);
        try {
            url += ParameterStringBuilder.getParamsString(parameters);
            request.setUrl(url);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                HttpStatus status = httpResponse.getStatus();
                if(status.getStatusCode() == 200) {
                    String responseJson = httpResponse.getResultAsString();
                    Gdx.app.log("web response", responseJson);
                } else {
                    System.out.println("Error\nStatus:" + httpResponse.getStatus() +" Response: " + httpResponse);
                }
            }
            public void failed(Throwable t) {
                String error = t.getMessage();
            }
            public void cancelled() {
                // request aborted
            }
        });
    }
}

class ParameterStringBuilder {
    public static String getParamsString(Map<String, Integer> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Integer> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(entry.getValue());
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}