package org.woo.context;

import java.io.Serializable;

public class RequestContext implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5777194556594599239L;
    private ThreadLocal<RequestContext> current = new ThreadLocal<>();
}
