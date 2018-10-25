package org.woo.orm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Administrator on 2017/7/2.
 */
@Entity
@Table(name = "TB_ROLE", schema = "WOOSTOREADMIN")
public class Role implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8170165769391381082L;
	private String pkId;
    private String name;
    private String number;
    private BigDecimal baseStatus;

    @Id
    @Column(name = "PK_ID")
    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
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
    @Column(name = "FNUMBER")
    public String getNumber() {
        return number;
    }

    public void setNumber(String fnumber) {
        this.number = fnumber;
    }

    @Basic
    @Column(name = "BASESTATUS")
    public BigDecimal getBaseStatus() {
        return baseStatus;
    }

    public void setBaseStatus(BigDecimal basestatus) {
        this.baseStatus = basestatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(pkId, role.pkId) &&
                Objects.equals(name, role.name) &&
                Objects.equals(number, role.number) &&
                Objects.equals(baseStatus, role.baseStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkId, name, number, baseStatus);
    }
}
