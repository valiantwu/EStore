package org.woo.login.filter;

import io.jsonwebtoken.Claims;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.woo.framework.util.JwtUtil;
import org.woo.login.LoginType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    private LoginType initLoginType;

    public LoginFilter() {
    }

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String loginType = System.getProperty("login.type");
        if (loginType != null && "debug".equalsIgnoreCase(loginType)) {
            initLoginType = LoginType.DEBUG;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletRequest.setCharacterEncoding("utf-8");
        httpServletRequest.setAttribute("LoginType", initLoginType);
        try {
            chain.doFilter(request, response);
            doAccessCheck(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

    private void doAccessCheck(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        logger.error(HttpHeaders.CONTENT_ENCODING + httpServletRequest.getCharacterEncoding());
        logger.error(HttpHeaders.ACCEPT + httpServletRequest.getProtocol());
        logger.error(HttpHeaders.HOST + httpServletRequest.getRemoteHost());
        logger.error(HttpHeaders.SERVER + httpServletRequest.getServerName());
        logger.error(HttpHeaders.USER_AGENT + httpServletRequest.getScheme());
        logger.error(httpServletRequest.getAuthType());
        logger.error(httpServletRequest.getContextPath());
        logger.error(httpServletRequest.getMethod());
        logger.error(httpServletRequest.getRequestedSessionId());
        logger.error(httpServletRequest.getRemoteUser());
        logger.error(httpServletRequest.getPathTranslated());
        logger.error(httpServletRequest.getServletPath());
        String authorization = httpServletRequest.getParameter(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.length() > 0) {
            Claims token = JwtUtil.parseJWT(authorization);
        }
    }
}
