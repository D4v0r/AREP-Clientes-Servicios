package edu.escuelaing.arep.httpserver;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Request {
    private String method;
    private URI uri;
    private String requestURI;
    private String httpVersion;
    private Map<String, String> query;

    public Request(String requestLine){
        parseRequestLine(requestLine);
    }

    private void parseRequestLine(String requestLine)  {
        String[] components = requestLine.split(" ");
        method = components[0];
        try{
            uri = new URI(components[1]);
        }catch (URISyntaxException e){
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, e);
        }
        httpVersion = components[2];
        query = parseQuery(uri.getQuery());
    }

    private Map<String, String> parseQuery(String queryLine){
        if(queryLine == null ) return null;
        Map<String, String>  queryMap = new HashMap<>();
        String[] keys = queryLine.split("&");
        for (String key: keys
        ) {
            int index =  key.indexOf("=");
            if(index != 1){
                queryMap.put(key.substring(0, index), key.substring(index+1));
            }
        }
        return queryMap;
    }

    public String getMethod(){
        return method;
    }

    public  String getHttpVersion(){
        return  httpVersion;
    }

    public URI getUri(){
        return uri;
    }
}
