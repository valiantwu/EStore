package org.woo.metadata.entity;

import java.io.Serializable;

public abstract class DynamicMetadata implements IMetaData, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 895324807371917061L;
	private boolean isDbIgnore;

	@Override
	public boolean isDbIgnore() {
		return isDbIgnore;
	}

	public void setDbIgnore(boolean dbIgnore) {
		isDbIgnore = dbIgnore;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
