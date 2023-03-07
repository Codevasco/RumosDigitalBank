package org.bnpparibas.rdb.controller;

import jakarta.servlet.http.HttpSession;
import org.bnpparibas.rdb.model.Card;
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
    public String showLogin(Client client, Model model) {

        model.addAttribute("client", client);
        return "login";
    }

    /** API call for login, redirects to dashboard */
    @PostMapping("/login")
    public String postLogin(@RequestParam Long fiscalNumber, @RequestParam String password, HttpSession session) {

        Client client = webService.login(fiscalNumber, password);

        if (client != null) {
            session.setAttribute("client", client);
            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }

    /** Shows ATM login */
    @GetMapping("/atm-login")
    public String showAtmLogin(Card card, Model model) {

        model.addAttribute("card", card);
        return "atm-login";
    }

    /** API call for login, redirects to dashboard */
    @PostMapping("/atm-login")
    public String postAtmLogin(@RequestParam Long cardNumber, @RequestParam Integer cardPin, HttpSession session) {

       Card card = webService.atmLogin(cardNumber, cardPin);

        if (card != null) {
            return "redirect:/operations";
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