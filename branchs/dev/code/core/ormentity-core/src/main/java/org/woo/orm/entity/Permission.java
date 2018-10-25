package org.woo.orm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Administrator on 2017/7/2.
 */
@Entity
@Table(name = "TB_PERMISSION", schema = "WOOSTOREADMIN")
public class Permission implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2338014032246735164L;
	private String pkId;
    private String number;
    private String name;
    private boolean baseStatus;

    @Id
    @Column(name = "PK_ID")
    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    @Basic
    @Column(name = "FNUMBER")
    public String getNumber() {
        return number;
    }

    public void setNumber(String fnumber) {
        this.number = fnumber;
    }

    @Basic
    @Column(name = "FNAME")
    public String getName() {
        return name;
    }

    public void setName(String fname) {
        this.name = fname;
    }

    @Basic
    @Column(name = "BASESTATUS")
    public boolean isBaseStatus() {
        return baseStatus;
    }

    public void setBaseStatus(boolean basestatus) {
        this.baseStatus = basestatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return baseStatus == that.baseStatus &&
                Objects.equals(pkId, that.pkId) &&
                Objects.equals(number, that.number) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkId, number, name, baseStatus);
    }
}
