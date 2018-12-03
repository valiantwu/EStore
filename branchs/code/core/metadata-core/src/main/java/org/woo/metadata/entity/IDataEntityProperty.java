package org.woo.metadata.entity;

public interface IDataEntityProperty extends IMetaData {
	Object getValueFast(Object var1);

	void setValueFast(Object var1, Object var2);

	Object getValue(Object var1);

	Class<?> getPropertyType();

	IDataEntityType getParent();

	boolean hasDefaultValue();
}
