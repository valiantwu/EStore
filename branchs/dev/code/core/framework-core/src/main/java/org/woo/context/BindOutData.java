package org.woo.context;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BindOutData implements AutoCloseable{
    private OutputStreamWriter outputStreamWriter;
    private ResponseDataWrapper responseDataWrapper;

    public BindOutData(HttpServletResponse response, ResponseDataWrapper responseDataWrapper) {
        this.responseDataWrapper=responseDataWrapper;
        try {
            this.outputStreamWriter= new OutputStreamWriter(response.getOutputStream(),"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public <T> void writeSuccess(Object object,Class<T> clazz){
        if (this.outputStreamWriter==null){
            new Throwable();
        }else {
            String data =this.responseDataWrapper.warpSuccess(object);
            this.writeOut(data);
        }
    }
    public void writeOut(String data){
        try {
            this.outputStreamWriter.write(data);
            this.outputStreamWriter.flush();
            this.outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.outputStreamWriter=null;
        }
    }
    @Override
    public void close(){
        if (this.outputStreamWriter!=null){
            try {
                this.outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
