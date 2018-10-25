package org.woo.orm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Administrator on 2017/7/2.
 */
@Entity
@Table(name = "TB_GROUP", schema = "WOOSTOREADMIN")
public class Group implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7199457800308046542L;
	private String pkId;
    private String parentId;
    private String number;
    private String name;
    private boolean isLeaf;
    private boolean isRoot;

    @Id
    @Column(name = "PK_ID")
    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    @Basic
    @Column(name = "FKPARENTID")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "FNUMBER")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "FNAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ISLEAF")
    public boolean isIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isleaf) {
        this.isLeaf = isleaf;
    }

    @Basic
    @Column(name = "ISROOT")
    public boolean isIsRoot() {
        return isRoot;
    }

    public void setIsRoot(boolean isroot) {
        this.isRoot = isroot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return isLeaf == group.isLeaf &&
                isRoot == group.isRoot &&
                Objects.equals(pkId, group.pkId) &&
                Objects.equals(parentId, group.parentId) &&
                Objects.equals(number, group.number) &&
                Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkId, parentId, number, name, isLeaf, isRoot);
    }
}
