package com.master4.interceptor;

import com.master4.entities.Role;
import com.master4.entities.User;
import com.master4.repositories.UserRepository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    UserRepository userRepository;

    @Override
    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) throws Exception {

        boolean alreadyLoggedIn = request.getSession()
                .getAttribute("user") != null;

        User user = (User) request.getSession().getAttribute("user");
        Role role = (Role) request.getSession().getAttribute("currentRole");
        boolean isAdmin = false;

        if(role.getName().equals("admin")){
            isAdmin = true;
        }

        if (alreadyLoggedIn && !isAdmin) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        return true;
    }
}
