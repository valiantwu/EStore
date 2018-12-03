package org.woo.metadata.entity;

import java.io.Serializable;

public abstract class DataEntityBase implements IDataEntityBase, Serializable {
    @Override
    public abstract IDataEntityType getDataEntityType();

    @Override
    public Object getPkValue() {
        return null;
    }
}
