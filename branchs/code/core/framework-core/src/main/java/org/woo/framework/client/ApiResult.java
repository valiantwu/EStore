package org.woo.framework.client;

import java.util.Map;

public class ApiResult {
    private int statusCode = 200;
    private Map<String,Object> data;
    private Map<String,String> headers;
    private Throwable throwable;
    public ApiResult(Throwable throwable){
        this.throwable =throwable;
        this.statusCode =-1;
    }
    public boolean isOk(){
        return this.statusCode==200;
    }
    public int getStatusCode() {
        return this.statusCode;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
    public String getHeader(String name){
        return this.headers==null?null:this.headers.get(name);
    }
    @Override
    public String toString() {
        return super.toString();
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
