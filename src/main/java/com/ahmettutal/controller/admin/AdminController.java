package com.ahmettutal.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/admin", "/manage"})
public class AdminController {

    @GetMapping
    public String get(Model model) {
        return "admin/index";
    }

}
