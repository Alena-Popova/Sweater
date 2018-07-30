package org.popova.lesson.sweater.controller;

import org.popova.lesson.sweater.domain.User;
import org.popova.lesson.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList( Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userlist";
    }

    @GetMapping("{user}")
    public String userEditForm(@RequestParam User user, Model model) {
        model.addAttribute("user", user);
        return "userEdit";
    }

}
