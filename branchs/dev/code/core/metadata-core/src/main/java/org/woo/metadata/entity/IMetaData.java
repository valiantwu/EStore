package org.woo.metadata.entity;

public interface IMetaData extends Cloneable {
	String getName();

	String getAlias();

	boolean isDbIgnore();

	Object clone() throws CloneNotSupportedException;
}
