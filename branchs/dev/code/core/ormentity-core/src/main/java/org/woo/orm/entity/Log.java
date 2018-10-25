package org.woo.orm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Administrator on 2017/7/2.
 */
@Entity
@Table(name = "TB_LOG", schema = "WOOSTOREADMIN")
public class Log implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 206516946609764989L;
	private String pkId;
    private String fkMasterName;
    private String tableName;
    private String opType;
    private String contentNote;
    private String params;
    private Timestamp opTime;
    private Boolean maintainTime;

    @Id
    @Column(name = "PK_ID")
    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    @Basic
    @Column(name = "FKMASTERNAME")
    public String getFkMasterName() {
        return fkMasterName;
    }

    public void setFkMasterName(String fkmastername) {
        this.fkMasterName = fkmastername;
    }

    @Basic
    @Column(name = "TABLENAME")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tablename) {
        this.tableName = tablename;
    }

    @Basic
    @Column(name = "OPTYPE")
    public String getOpType() {
        return opType;
    }

    public void setOpType(String optype) {
        this.opType = optype;
    }

    @Basic
    @Column(name = "CONTENTNOTE")
    public String getContentNote() {
        return contentNote;
    }

    public void setContentNote(String contentnote) {
        this.contentNote = contentnote;
    }

    @Basic
    @Column(name = "PARAMS")
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Basic
    @Column(name = "OPTIME")
    public Timestamp getOpTime() {
        return opTime;
    }

    public void setOpTime(Timestamp optime) {
        this.opTime = optime;
    }

    @Basic
    @Column(name = "MAINTAINTIME")
    public Boolean getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(Boolean maintaintime) {
        this.maintainTime = maintaintime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(pkId, log.pkId) &&
                Objects.equals(fkMasterName, log.fkMasterName) &&
                Objects.equals(tableName, log.tableName) &&
                Objects.equals(opType, log.opType) &&
                Objects.equals(contentNote, log.contentNote) &&
                Objects.equals(params, log.params) &&
                Objects.equals(opTime, log.opTime) &&
                Objects.equals(maintainTime, log.maintainTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkId, fkMasterName, tableName, opType, contentNote, params, opTime, maintainTime);
    }
}
