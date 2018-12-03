package org.woo.context;


import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public class BindInData implements AutoCloseable {
    private InputStream inputStream;
    private HttpServletRequest request;
    private String body;

    @Override
    public void close() throws Exception {
        inputStream.close();
    }

    public BindInData() {
    }

    public BindInData(HttpServletRequest request) {
        this.request = request;
        try {
            inputStream = this.request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T getBody(Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (this.body != null) {
            try {
                return objectMapper.readValue(this.body, tClass);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    this.inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
