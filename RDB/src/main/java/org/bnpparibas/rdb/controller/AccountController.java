package org.bnpparibas.rdb.controller;

import jakarta.servlet.http.HttpSession;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.service.AccountService;
import org.bnpparibas.rdb.service.implementation.AccountServiceImpl;
import org.bnpparibas.rdb.service.implementation.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;


    /** Shows page listing all accounts for specific client */
    @GetMapping("/accounts")
    public String showAccounts(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            List <Account> accounts = accountService.findAllAccounts(client.getFiscalNumber());
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            model.addAttribute("accounts", accounts);
            return "accounts";
        } else {
            return "redirect:/login";
        }
    }

    /** Shows page for secondary holder update */
    @GetMapping("/accounts-update")
    public String showUpdateAccount(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            return "accounts-update";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for updating account secondary holder, redirects to accounts page */
    @PostMapping("/accounts-update")
    public String postUpdateAccount(@ModelAttribute("account") Account account, Integer accountNumber) {
        accountService.updateAccount(account, accountNumber);
        return "redirect:/accounts";
    }

    /** Shows page for creating a new account */
    @GetMapping("/accounts-create")
    public String showAddAccount(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", new Account());
            return "accounts-create";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for creating a new account, redirects to accounts page */
    @PostMapping("/accounts-create")
    public String postAddAccount(@ModelAttribute("account") Account account, @RequestParam Long primaryHolder, @RequestParam Long secondaryHolder) {
        accountService.addAccount(account, primaryHolder, secondaryHolder);
        return "redirect:/accounts";
    }

    /** Shows page for deleting an existing account */
    @GetMapping("accounts-delete")
    public String showDeleteAccount(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            return "accounts-delete";
        } else {
            return "redirect:/login";
        }
    }

    /** API call to delete an existing account, redirects to accounts page */
    @PostMapping("accounts-delete")
    public String postDeleteAccount(@ModelAttribute("account") Account account, @RequestParam Integer accountNumber) {

        accountService.deleteAccount(accountNumber);
        return "redirect:/accounts";
    }
}