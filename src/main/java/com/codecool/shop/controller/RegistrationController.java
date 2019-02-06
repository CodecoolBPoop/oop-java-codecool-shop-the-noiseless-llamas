package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class RegistrationController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String emailAddress = request.getParameter("email");
        String password = request.getParameter("password1");
        String passwordRepeat = request.getParameter("password2");

        System.out.println(emailAddress + " anyád");
        System.out.println(password + " anyád");
        System.out.println(passwordRepeat + " anyád");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        engine.process("registration.html", context, response.getWriter());
    }
}
