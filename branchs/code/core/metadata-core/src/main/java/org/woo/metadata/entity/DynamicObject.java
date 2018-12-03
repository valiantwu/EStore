package org.woo.metadata.entity;

import java.io.Serializable;
import java.util.BitSet;

public final class DynamicObject extends DataEntityBase implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7594087994791637157L;
    private DynamicObjectType dynamicObjectType;
    private transient BitSet bitSet;

    public DynamicObject(DynamicObjectType dynamicObjectType, Object id) {
    }

    public DynamicObject(DynamicObjectType dynamicObjectType) {
        this(dynamicObjectType, (Object) null);
    }

    public final DynamicObjectType getDynamicObjectType() {
        return dynamicObjectType;
    }

    public final void set(String proName, Object value) {
    }

    public final void set(int index, String proName) {
    }

    public final void set(IDataEntityProperty iDataEntityProperty, Object value) {
    }

    public final Object get(String proName) {
        return null;
    }

    public final Object get(int index) {
        return null;
    }

    public final Object get(IDataEntityProperty iDataEntityProperty) {
        return null;
    }

    public final <T> T get(String proName, Class<T> tClass) {
        return null;
    }

    public final <T> T get(int index, Class<T> tClass) {
        return null;
    }

    public final <T> T get(IDataEntityProperty iDataEntityProperty, Class<T> tClass) {
        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public IDataEntityType getDataEntityType() {
        return null;
    }
}
