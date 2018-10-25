package org.woo.metadata.entity;


import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.List;

public class DynamicObjectType extends DynamicMetadata implements IDataEntityType, Serializable, Cloneable {
    /**
     *
     */
    private static final long serialVersionUID = -239040857899183508L;
    private Object[] propertyValues;
    private String[] propertyname;
    private String name;
    private String tableName;
    PropertyDescriptor propertyDescriptor;
    private List<IDataEntityProperty> jsonSerializerProperties;

    protected DynamicObjectType() {
    }

    @Override
    public Object createInstance() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getAlias() {
        return null;
    }
}
