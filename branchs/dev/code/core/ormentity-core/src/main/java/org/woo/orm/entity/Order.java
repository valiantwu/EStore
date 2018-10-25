package org.woo.orm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Administrator on 2017/7/2.
 */
@Entity
@Table(name = "TB_ORDER", schema = "WOOSTOREADMIN")
public class Order implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4656391976061236970L;
	private String pkId;
    private Timestamp addTime;
    private Timestamp payTime;
    private BigDecimal baseStatus;
    private BigDecimal payStatus;
    private BigDecimal status;
    private BigDecimal f2Status;
    private String fkMasterId;
    private String fkAccountId;
    private String fkGoodsId;

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
    @Column(name = "PAYTIME")
    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp paytime) {
        this.payTime = paytime;
    }

    @Basic
    @Column(name = "BASESTATUS")
    public BigDecimal getBaseStatus() {
        return baseStatus;
    }

    public void setBaseStatus(BigDecimal baseststus) {
        this.baseStatus = baseststus;
    }

    @Basic
    @Column(name = "PAYSTATUS")
    public BigDecimal getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(BigDecimal paystatus) {
        this.payStatus = paystatus;
    }

    @Basic
    @Column(name = "STATUS")
    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    @Basic
    @Column(name = "F2STATUS")
    public BigDecimal getF2Status() {
        return f2Status;
    }

    public void setF2Status(BigDecimal f2Status) {
        this.f2Status = f2Status;
    }

    @Basic
    @Column(name = "FKMASTERID")
    public String getFkMasterId() {
        return fkMasterId;
    }

    public void setFkMasterId(String fkmaster) {
        this.fkMasterId = fkmaster;
    }

    @Basic
    @Column(name = "FKACCOUNTID")
    public String getFkAccountId() {
        return fkAccountId;
    }

    public void setFkAccountId(String fkaccountid) {
        this.fkAccountId = fkaccountid;
    }

    @Basic
    @Column(name = "FKGOODSID")
    public String getFkGoodsId() {
        return fkGoodsId;
    }

    public void setFkGoodsId(String fkgoodsid) {
        this.fkGoodsId = fkgoodsid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(pkId, order.pkId) &&
                Objects.equals(addTime, order.addTime) &&
                Objects.equals(payTime, order.payTime) &&
                Objects.equals(baseStatus, order.baseStatus) &&
                Objects.equals(payStatus, order.payStatus) &&
                Objects.equals(status, order.status) &&
                Objects.equals(f2Status, order.f2Status) &&
                Objects.equals(fkMasterId, order.fkMasterId) &&
                Objects.equals(fkAccountId, order.fkAccountId) &&
                Objects.equals(fkGoodsId, order.fkGoodsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkId, addTime, payTime, baseStatus, payStatus, status, f2Status, fkMasterId, fkAccountId, fkGoodsId);
    }
}
