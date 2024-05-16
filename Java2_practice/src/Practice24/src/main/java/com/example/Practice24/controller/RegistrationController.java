package com.example.Practice24.controller;

import com.example.Practice24.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.equals("anonymousUser")) {
            username = "Вы не авторизованы";
        }
        model.addAttribute("username", username);
        return "registration_page";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model, RedirectAttributes redirectAttributes) {
        if (customerService.exists(username) || username.equals("anonymousUser")) {
            redirectAttributes.addFlashAttribute("er", "Извините, имя занято");
            return "redirect:/registration";
        }
        customerService.create(username, password, "USER");
        System.out.println(username);
        System.out.println(password);
        System.out.println("USER");
        redirectAttributes.addFlashAttribute("mes", "Вы успешно зарегистрировались");
        return "redirect:/authentication";
    }
}



