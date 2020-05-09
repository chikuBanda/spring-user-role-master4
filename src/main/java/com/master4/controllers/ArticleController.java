package com.master4.controllers;


import com.master4.converter.TagFormatter;
import com.master4.entities.Article;
import com.master4.entities.Role;
import com.master4.entities.Tag;
import com.master4.entities.User;
import com.master4.exceptions.ResourceNotFoundException;
import com.master4.services.ArticleService;
import com.master4.services.TagService;
import com.master4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping(value = {"","/article"})
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "tagList",
                new TagFormatter(List.class));
    }

    @GetMapping(value = {"/", "/article/", "/page/{id}", "/article/page/{id}"})
    public String home(@PathVariable(name="id",required = false) Optional<Integer> id, ModelMap model)
    {
        Page<Article> pages = articleService.getAllArticles(id, 3, "id");
        model.addAttribute("pageable", pages);

        User user = (User) session.getAttribute("user");
        String role = user.getListRole().get(0).getName();
        model.addAttribute("role", role);

        return "article/home";
    }

    @RequestMapping({"/view/{id}", "/article/view/{id}"})
    public String view(@PathVariable("id") long id,ModelMap model) throws ResourceNotFoundException {
        model.addAttribute("article",articleService.findById(id));
        return "article/view";
    }


    @GetMapping({"admin/article/add", "writer/article/add"})
    public String add(ModelMap model,Article article) {
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("article", article);
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("currentUser", session.getAttribute("user"));
        model.addAttribute("currentRole", session.getAttribute("currentRole"));
        return "article/add";
    }

    @GetMapping({"admin/article/add/{id}", "writer/article/add/{id}"})
    public String edit(@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        Article article=articleService.findByIdWithTags(id);
        List<Tag> tags=tagService.getAllTags();
        tags.forEach(e->{
             article.getTagList().forEach(t->{
                 if(e.getId() ==t.getId()){
                     e.setUsed(true);
                 }
             });
        });

        model.addAttribute("currentUser", session.getAttribute("user"));
        model.addAttribute("currentRole", session.getAttribute("currentRole"));
        model.addAttribute("tags", tags);
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("article", articleService.findByIdWithTags(id));
        return "article/add";
    }

    @PostMapping("/article/save")
    public String saveArticle(@Valid @ModelAttribute("article") Article article, BindingResult result, ModelMap model) throws ResourceNotFoundException {
        if(result.hasErrors()){

            model.addAttribute("tags", tagService.getAllTags());
            model.addAttribute("article",article);
            return "article/add";
        }

        articleService.save(article);
        return "redirect:/article/";
    }

    @GetMapping("/article/delete/{page}/{id}")
    public String delete(@PathVariable("page") long page,@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        articleService.deleteById(id);
        return "redirect:/article/page/"+page;
    }


    /*@GetMapping("/redirect")
    public String redirect(String st) {
        return "redirect:/"+st;
    }*/

}
