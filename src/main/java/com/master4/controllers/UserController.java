package com.master4.controllers;

import com.master4.converter.RoleFormater;
import com.master4.converter.TagFormatter;
import com.master4.entities.Article;
import com.master4.entities.Role;
import com.master4.entities.Tag;
import com.master4.entities.User;
import com.master4.exceptions.ResourceNotFoundException;
import com.master4.repositories.RoleRepository;
import com.master4.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/"})
@Getter
@Setter
public class UserController {

    @Autowired
    @Qualifier("passwordValidator")
    private Validator validator;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private RoleRepository roleRepository;

    private String Error;

    public Role currentRole;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "listRole",
                new RoleFormater(List.class));
    }

    @GetMapping(value = {"/admin/user","/admin/user/page/{id}"})
    public String home(@PathVariable(name="id",required = false) Optional<Integer> id, ModelMap model){
        Page<User> pages = userService.getAllUsers(id, 3, "id");
        model.addAttribute("pageable", pages);
        return "user/home";
    }

    @GetMapping(value = {"/index"})
    public String index( ModelMap model ,User user){
        model.addAttribute("user", user);
        return "user/index";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("user") User user, BindingResult result, ModelMap model ) throws ResourceNotFoundException {
        if(result.hasErrors()){
            model.addAttribute("user",user);
            System.out.println(result);
            return "user/index";
        }
        if(userService.isExist(user.getEmail(),user.getPassword())){
            User currentUser = userService.findByEmail(user.getEmail());
            session.setAttribute("user", currentUser);
            System.out.println("session ------------------------------");

            if(currentUser.getListRole().size() <= 1){
                session.setAttribute("currentRole", currentUser.getListRole().get(0));
                currentRole = (Role) session.getAttribute("currentRole");
                return "redirect:/article/";
            }
            else if(currentUser.getListRole().size() > 1){
                return "redirect:/select_roles/";
            }

        }
        return  "user/index";
    }

    @GetMapping("/select_roles")
    public String selectRole(Role role, ModelMap model, BindingResult result) {
        User currentUser = (User) session.getAttribute("user");
        List<Role> roleList = currentUser.getListRole();

        model.addAttribute("role", role);
        model.addAttribute("roleList", roleList);

        return "user/role";
    }


    @PostMapping("/select_roles")
    public String selectRole(@ModelAttribute("role") Role role, BindingResult result, ModelMap model ) throws ResourceNotFoundException {
        if(result.hasErrors()){
            model.addAttribute("role", role);
            System.out.println(result);
            return "user/role";
        }

        session.setAttribute("currentRole", role);
        currentRole = (Role) session.getAttribute("currentRole");

        return "redirect:/article/";
    }


    @RequestMapping("/logout")
    public String logout() throws ResourceNotFoundException {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/admin/user/add")
    public String add(ModelMap model, @Validated  User user) {

        List<Role> roles = roleRepository.findAll();
        roles.forEach(e->{
            if(e.getName().equalsIgnoreCase("visitor")){
                e.setUsed(true);
            }
        });

        model.addAttribute("roles",roles);
        model.addAttribute("user", user);

        return "user/add";
    }

    @GetMapping("/admin/user/add/{id}")
    public String edit(@PathVariable("id") long id, ModelMap model, @Validated User user) throws ResourceNotFoundException {
        User currentUser = userService.findById(id);

        List<Role> roles = roleRepository.findAll();

        roles.forEach(e->{
            currentUser.getListRole().forEach(t->{
                if(e.getId() == t.getId()){
                    e.setUsed(true);
                }
            });
        });

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("user", currentUser);
        model.addAttribute("roles", roles);

        return "user/add";
    }

    @PostMapping("/user/save")
    public String saveArticle(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) throws ResourceNotFoundException {
        if(result.hasErrors()){
            model.addAttribute("user",user);
            model.addAttribute("roles", roleRepository.findAll());
            System.out.println("has error");
            return "user/add";
        }
        userService.save(user);
        return "redirect:/admin/user";
    }

    @PostMapping("/user/edit/save")
    public String saveEdit(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) throws ResourceNotFoundException {
        if(result.hasErrors()){
            model.addAttribute("user",user);
            model.addAttribute("roles", roleRepository.findAll());
            System.out.println("has error");
            return "user/modify";
        }
        userService.save(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/view/{id}")
    public String view(@PathVariable("id") long id,ModelMap model) throws ResourceNotFoundException {
        model.addAttribute("user",userService.findById(id));
        model.addAttribute("articles" , userService.getArticlesOfUser(id));
        return "user/view";
    }

    @GetMapping("/admin/user/delete/{page}/{id}")
    public String delete(@PathVariable("page") long page,@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        userService.deleteById(id);
        return "redirect:/user/page/"+page;
    }


    /*@GetMapping("/redirect")
    public String redirect(String st) {
        return "redirect:/"+st;
    }*/
}
