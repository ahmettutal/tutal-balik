package com.ahmettutal.controller.admin;

import com.ahmettutal.core.AdminController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AdminController
public class AdminHomeController {

    @GetMapping
    public String get(Model model) {
        return "admin/index";
    }

}
