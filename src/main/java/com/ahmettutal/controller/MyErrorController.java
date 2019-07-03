package com.ahmettutal.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {

            Object message = request.getAttribute("org.springframework.web.servlet.DispatcherServlet.EXCEPTION");
            model.addAttribute("message", message);
            System.out.println("Error occurred, status: " + status + ", message: " + message);

            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "admin/error-404";
            } else if (statusCode == HttpStatus.NOT_ACCEPTABLE.value()) {
                return "admin/error-406";
            }
        }

        return "admin/error";
    }

    @Override
    public String getErrorPath() {
        return "admin/error";
    }
}