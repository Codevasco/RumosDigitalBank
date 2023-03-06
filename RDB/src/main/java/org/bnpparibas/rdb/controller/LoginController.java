package org.bnpparibas.rdb.controller;

import jakarta.servlet.http.HttpSession;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.service.implementation.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginServiceImpl webService;


    /** Shows login page */
    @GetMapping("/login")
    public String showLoginForm(Model model) {

        model.addAttribute("client", new Client());
        return "login";
    }

    /** API call for login, redirects to dashboard */
    @PostMapping("/login")
    public String postLoginForm(@RequestParam Long fiscalNumber, @RequestParam String password, Model model, HttpSession session) {

        Client client = webService.login(fiscalNumber, password);

        if (client != null) {
            session.setAttribute("client", client);
            return "redirect:/dashboard";

        } else {
            model.addAttribute("error", "Invalid fiscal number or password.");
            return "login";
        }
    }
}