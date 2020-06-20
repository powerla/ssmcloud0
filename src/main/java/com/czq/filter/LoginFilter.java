package com.czq.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null && request.getRequestURI().indexOf("/User/login")==-1)
        {
            response.sendRedirect(request.getContextPath()+"/User/tiaozhuan");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    public void destroy() {

    }
}
