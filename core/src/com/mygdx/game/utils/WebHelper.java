package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.util.HashMap;
import java.util.Map;

public class WebHelper {

    public static void makeRequest() {

        Map<String, String> parameters = new HashMap<String, String>();

        /*parameters.put("token", "<TOKEN>");
        parameters.put("some_value", "1234");

        final Json json = new Json();
        json.setTypeName(null);
        json.setOutputType(JsonWriter.OutputType.json);
        json.setUsePrototypes(false);
        String requestJson = json.toJson(parameters);*/

        // Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl("https://libgdx.com/wiki/html5-backend-and-gwt-specifics");
        // request.setContent(requestJson);

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                HttpStatus status = httpResponse.getStatus();
                if(status.getStatusCode() == 200) {
                    String responseJson = httpResponse.getResultAsString();
                    System.out.println("success http response");
                    System.out.println(responseJson);
                    Gdx.app.log("web response", responseJson);
                } else {
                    System.out.println("error ((( http response");
                }
            }
            public void failed(Throwable t) {
                String error = t.getMessage();
            }
            public void cancelled() {
                // request aborded
            }
        });
    }

}
