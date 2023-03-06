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
    public String showLogin(Model model) {

        model.addAttribute("client", new Client());
        return "login";
    }

    /** API call for login, redirects to dashboard */
    @PostMapping("/login")
    public String postLogin(@RequestParam Long fiscalNumber, @RequestParam String password, Model model, HttpSession session) {

        Client client = webService.login(fiscalNumber, password);

        if (client != null) {
            session.setAttribute("client", client);
            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for logout, redirects to login */
    @GetMapping("/logout")
    public String showLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}