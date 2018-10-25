package org.woo.metadata.entity;

public interface IDataEntityBase {
	IDataEntityType getDataEntityType();

	Object getPkValue();
}
