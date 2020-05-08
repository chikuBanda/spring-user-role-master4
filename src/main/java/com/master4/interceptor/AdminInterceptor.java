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
        boolean isAdmin = false;

        for (Role role: user.getListRole()) {
            if(role.getName().equalsIgnoreCase("admin")){
                System.out.println("ROLE: " + role.getName());
                isAdmin = true;
            }
        }

        if (alreadyLoggedIn && !isAdmin) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        return true;
    }
}
