package org.woo.context;

public interface ResponseDataWrapper {
    String warpSuccess(Object object);
    String warpError(String errorCode, String errorMessage);
}
