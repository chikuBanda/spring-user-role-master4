package com.master4.filter;

import com.master4.entities.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/admin/*", dispatcherTypes = {DispatcherType.REQUEST})
public class AdminFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Role role = (Role) ((HttpServletRequest) servletRequest).getSession().getAttribute("currentRole");
        boolean isAlreadyLoggedIn = ((HttpServletRequest) servletRequest).getSession().getAttribute("user") != null;
        boolean isAdmin = false;

        if(!isAlreadyLoggedIn){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(role != null){
            if(role.getName().equals("admin")){
                isAdmin = true;
            }
        }

        if (!isAdmin) {
            ((HttpServletResponse) servletResponse).sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
