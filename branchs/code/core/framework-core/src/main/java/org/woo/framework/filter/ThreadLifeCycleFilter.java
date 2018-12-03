package org.woo.framework.filter;

import org.woo.framework.thread.ThreadLifeCycleManager;

import javax.servlet.*;
import java.io.IOException;

public class ThreadLifeCycleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            ThreadLifeCycleManager.start();
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            ThreadLifeCycleManager.end();
        }
    }

    @Override
    public void destroy() {

    }
}
