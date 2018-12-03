package org.woo.metadata.model;

import java.security.Principal;

/**
 * Created by Administrator on 2017/3/26.
 */
public class AuthorPricinple implements Principal {
	private String name;

	public AuthorPricinple(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
