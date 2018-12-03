package org.woo.orm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Administrator on 2017/7/2.
 */
@Entity
@Table(name = "TB_PRIVILEGE", schema = "WOOSTOREADMIN")
public class Privilege implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7742385905417275472L;
	private String pkId;
    private String privilegeMaster;
    private String privilegeMasterValue;
    private String privilegeAccess;
    private String privilegeAccessValue;
    private BigDecimal privilegeOperation;

    @Id
    @Column(name = "PK_ID")
    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    @Basic
    @Column(name = "PRIVILEGEMASTER")
    public String getPrivilegeMaster() {
        return privilegeMaster;
    }

    public void setPrivilegeMaster(String privilegemaster) {
        this.privilegeMaster = privilegemaster;
    }

    @Basic
    @Column(name = "PRIVILEGEMASTERVALUE")
    public String getPrivilegeMasterValue() {
        return privilegeMasterValue;
    }

    public void setPrivilegeMasterValue(String privilegemastervalue) {
        this.privilegeMasterValue = privilegemastervalue;
    }

    @Basic
    @Column(name = "PRIVILEGEACCESS")
    public String getPrivilegeAccess() {
        return privilegeAccess;
    }

    public void setPrivilegeAccess(String privilegeaccess) {
        this.privilegeAccess = privilegeaccess;
    }

    @Basic
    @Column(name = "PRIVILEGEACCESSVALUE")
    public String getPrivilegeAccessValue() {
        return privilegeAccessValue;
    }

    public void setPrivilegeAccessValue(String privilegeaccessvalue) {
        this.privilegeAccessValue = privilegeaccessvalue;
    }

    @Basic
    @Column(name = "PRIVILEGEOPERATION")
    public BigDecimal getPrivilegeOperation() {
        return privilegeOperation;
    }

    public void setPrivilegeOperation(BigDecimal privilegeoperation) {
        this.privilegeOperation = privilegeoperation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privilege privilege = (Privilege) o;
        return Objects.equals(pkId, privilege.pkId) &&
                Objects.equals(privilegeMaster, privilege.privilegeMaster) &&
                Objects.equals(privilegeMasterValue, privilege.privilegeMasterValue) &&
                Objects.equals(privilegeAccess, privilege.privilegeAccess) &&
                Objects.equals(privilegeAccessValue, privilege.privilegeAccessValue) &&
                Objects.equals(privilegeOperation, privilege.privilegeOperation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkId, privilegeMaster, privilegeMasterValue, privilegeAccess, privilegeAccessValue, privilegeOperation);
    }
}
