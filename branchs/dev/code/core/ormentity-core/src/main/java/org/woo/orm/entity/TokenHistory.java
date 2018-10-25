package org.woo.orm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Administrator on 2017/7/2.
 */
@Entity
@Table(name = "TB_TOKENHISTORY", schema = "WOOSTOREADMIN")
public class TokenHistory implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1995230191309875522L;
	private String pkId;
    private Timestamp addTime;
    private String accessToken;
    private String fkMasterId;
    private String loginIp;

    @Id
    @Column(name = "PK_ID")
    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    @Basic
    @Column(name = "ADDTIME")
    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addtime) {
        this.addTime = addtime;
    }

    @Basic
    @Column(name = "ACCESSTOKEN")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accesstoken) {
        this.accessToken = accesstoken;
    }

    @Basic
    @Column(name = "FKMASTERID")
    public String getFkMasterId() {
        return fkMasterId;
    }

    public void setFkMasterId(String fkmasterid) {
        this.fkMasterId = fkmasterid;
    }

    @Basic
    @Column(name = "LOGINIP")
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginip) {
        this.loginIp = loginip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenHistory that = (TokenHistory) o;
        return Objects.equals(pkId, that.pkId) &&
                Objects.equals(addTime, that.addTime) &&
                Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(fkMasterId, that.fkMasterId) &&
                Objects.equals(loginIp, that.loginIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkId, addTime, accessToken, fkMasterId, loginIp);
    }
}
