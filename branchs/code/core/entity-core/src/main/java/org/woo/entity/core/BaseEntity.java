package org.woo.entity.core;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class BaseEntity implements Serializable {
    private BigInteger id;
    private Integer version;
    private Date createTime;

    public BigInteger getId() {
        return id;
    }

    public BaseEntity setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public BaseEntity setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BaseEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
