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


    /**
     * Shows page listing all accounts for specific client
     */
    @GetMapping("/accounts")
    public String showAccountsForm(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            accountService.findAllAccounts();
            return "accounts";
        } else {
            return "redirect:/login";
        }
    }

    // public String postAccountsForm;

    /** Shows page for single account page for specific client */
    @GetMapping("/accounts/selectedAccount")
    public String showSingleAccountForm(@ModelAttribute("account") Account account, @RequestParam Long accountNumber, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            accountService.findByAccountNumber(accountNumber);
            return "accounts/selectedAccount";
        } else {
            return "redirect:/login";
        }
    }

    // public String showUpdateAccountForm;

    // public String postUpdateAccountForm;

    /** Shows page for creating a new account */
    @GetMapping("/addAccount")
    public String showAddAccountForm(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            accountService.findAllAccounts();
            return "/addAccount";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for creating a new account, redirects to single account page */
    @PostMapping("/addAccount")
    public String postAddAccount(@ModelAttribute("account") Account account, @RequestParam Long fiscalNumber) {
        accountService.addAccount(account, fiscalNumber);
        return "redirect:/accounts/selectedAccount";
    }

    @GetMapping("/deleteAccount")
    public String showDeleteAccountForm(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            return "/deleteAccount";
        } else {
            return "redirect:/login";
        }
    }

    /** API call to delete account, redirects to all accounts page */
    @DeleteMapping("/deleteAccount")
    public String postDeleteAccountForm(@RequestParam Long accountNumber) {

        accountService.deleteAccount(accountNumber);
        return "redirect:/accounts";
    }
}