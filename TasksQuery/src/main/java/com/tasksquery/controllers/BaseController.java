package com.tasksquery.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController
{

    private final static String USER_NAME = "username";

    @ModelAttribute
    public void addUserName(Model model)
    {
        Authentication ath = SecurityContextHolder.getContext().getAuthentication();
        String username = ath != null ? ath.getName() : null;

        if (username.equalsIgnoreCase("admin"))
            model.addAttribute(USER_NAME, username);
        else
            model.addAttribute(USER_NAME, null);
    }
}
