package org.bnpparibas.rdb.controller;

import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.service.implementation.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private ClientServiceImpl clientService;


    /** Shows register page */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {

        model.addAttribute("client", new Client());
        return "register";
    }

    /** API call for register, redirects to login */
    @PostMapping("/register")
    public String postRegisterForm(@ModelAttribute("client") Client client, @RequestParam Long fiscalNumber) {

        clientService.addClient(client, fiscalNumber);
        return "redirect:/login";
    }
}