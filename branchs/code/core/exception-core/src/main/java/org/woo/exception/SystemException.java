package org.woo.exception;

public class SystemException extends RuntimeException {
    public static final SystemException DB_INSERT_RESULT_0 = new SystemException(90040001, "4二位热无");

    public static final SystemException DB_UPDATE_RESULT_0 = new SystemException(90040002, "");

    public static final SystemException DB_SELECTONE_IS_NULL = new SystemException(90040003, "");

    public static final SystemException DB_LIST_IS_NULL = new SystemException(90040004, "");

    public static final SystemException TOKEN_IS_ILLICIT = new SystemException(90040005, "");

    public static final SystemException SESSION_IS_OUT_TIME = new SystemException(90040006, "");

    protected String msg;

    protected int code;

    public SystemException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public SystemException() {
        super();
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    /**
     * @param msgFormat
     * @param args
     * @return
     */
    public SystemException newInstance(String msgFormat, Object... args) {
        return new SystemException(this.code, msgFormat, args);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message) {
        super(message);
    }
}
