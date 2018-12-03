package org.woo.exception;

public class WebAppException extends RuntimeException {
    /**
     * 异常ID，用于表示某一异常实例，每一个异常实例都有一个唯一的异常ID
     */
    protected String id;

    /**
     * 异常信息，包含必要的上下文业务信息，用于打印日志
     */
    protected String message;

    /**
     * 具体异常码，由各具体异常实例化时自己定义
     */
    protected String defineCode;

    /**
     * 异常类名
     */
    protected String realClassName;

}
