package org.woo.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ApiRequestContext implements AutoCloseable {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private BindInData bindInData;
    private BindOutData bindOutData;

    @Override
    public void close() throws Exception {

    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiRequestContext.class);

    private static final ApiRequestContext INSTANCE = new ApiRequestContext();

    public final static ApiRequestContext get() {
        return INSTANCE;
    }

    private ApiRequestContext() {
        super();
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Map<String, String> getParameterMap() {
        Map<String, String> result = new HashMap<>();
        Map<String, String[]> map = this.request.getParameterMap();
        for (String key : map.keySet()) {
            String[] values = map.get(key);
            String value = values == null ? null : values[0];
            result.put(key, value);
        }
        LOGGER.debug(result.toString());
        return result;
    }

    public String getParameterValue(String param) {
        bindOutData.close();
        return this.request.getParameter(param);
    }

    public String getPostData() {
        return null;
    }

    public <T> T getPostData(Class<T> tClass) {
        return this.bindInData.getBody(tClass);
    }

    public String getHeaderValue(String header) {
        return this.request.getHeader(header);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpSession getSession() {
        return getSession(true);
    }

    public HttpSession getSession(boolean create) {
        return getRequest().getSession(create);
    }

    public String getSessionId() {
        return getSession().getId();
    }

    public ServletContext getServletContext() {
        return getSession().getServletContext(); // servlet2.3
    }


}