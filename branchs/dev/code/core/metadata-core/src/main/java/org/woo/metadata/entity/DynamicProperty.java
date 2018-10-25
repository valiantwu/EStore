package org.woo.metadata.entity;

import java.io.Serializable;

public class DynamicProperty extends DynamicMetadata implements IDataEntityProperty,Serializable,Cloneable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2795020303803882166L;
	private transient DynamicObjectType _reflectedType;
    protected Class<?> _propertyType;
    @Override
    public Object getValueFast(Object var1) {
        return null;
    }

    @Override
    public void setValueFast(Object var1, Object var2) {

    }

    @Override
    public Object getValue(Object var1) {
        return null;
    }

    @Override
    public Class<?> getPropertyType() {
        return null;
    }

    @Override
    public IDataEntityType getParent() {
        return null;
    }

    @Override
    public boolean hasDefaultValue() {
        return false;
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
