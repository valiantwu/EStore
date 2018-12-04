package org.woo.entity.factory;

import java.io.Serializable;

public abstract class EntityFactory implements EntityMeta<Object> {
	public EntityFactory() {

	}

	@Override
	public Serializable Builder() {
		return null;
	}
}
