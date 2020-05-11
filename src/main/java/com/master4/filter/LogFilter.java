package com.master4.filter;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST})
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse  res = (HttpServletResponse) response;
        boolean isAlreadyLoggedIn = req.getSession().getAttribute("user") != null;
        String path = req.getRequestURI();

        System.err.println(path);

        if(path.equals("/"+req.getContextPath()+"/login")){
            chain.doFilter(req, res);
            return;
        }

        if(!isAlreadyLoggedIn){
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, res);
    }
}
