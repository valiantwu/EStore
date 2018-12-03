/**
 * 
 */
package org.woo.dataentity.model;

import java.io.Serializable;

/**
 * @ClassName: ErrorInfo 
 * @Description: TODO
 * @author Administrator 
 * @date 2017年3月10日 上午9:40:05 
 *  
 */
public class ErrorInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
    private String errorMsg;
    private String errorType;

    public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
