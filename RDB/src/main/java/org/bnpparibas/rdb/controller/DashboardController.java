package org.bnpparibas.rdb.controller;

import jakarta.servlet.http.HttpSession;
import org.bnpparibas.rdb.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    /** Shows dashboard page */
    @GetMapping("/dashboard")
    public String showDashboardPage(Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            return "dashboard";

        } else {
            return "redirect:/login";
        }
    }

    // public String postLoginForm();
}