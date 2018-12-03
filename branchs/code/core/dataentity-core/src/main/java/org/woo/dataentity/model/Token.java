package org.woo.dataentity.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/21.
 */
public class Token implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4169359881837461473L;
	private String fMaterId;
    private String fAccessToken;

    public String getfMaterId() {
        return fMaterId;
    }

    public void setfMaterId(String fMaterId) {
        this.fMaterId = fMaterId;
    }

    public String getfAccessToken() {
        return fAccessToken;
    }

    public void setfAccessToken(String fAccessToken) {
        this.fAccessToken = fAccessToken;
    }
}
