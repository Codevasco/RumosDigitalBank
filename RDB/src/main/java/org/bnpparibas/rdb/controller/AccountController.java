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
            List <Account> accounts = accountService.findAllAccounts();
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            model.addAttribute("accounts", accounts);
            return "accounts";
        } else {
            return "redirect:/login";
        }
    }

    /** Shows page for single account page for specific client */
    @GetMapping("/accounts/selectedAccount")
    public String showSingleAccount(@ModelAttribute("account") Account account, @RequestParam Long accountNumber, Model model, HttpSession session) {

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

    /** Shows page for secondary holder update */
    @GetMapping("/accounts/selectedAccount/update")
    public String showUpdateAccount(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            return "accounts/selectedAccount/update";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for updating account secondary holder, redirects to specific account page */
    @PostMapping("/accounts/selectedAccount/update")
    public String postUpdateAccount(@ModelAttribute("account") Account account, @RequestParam Long fiscalNumber) {
        accountService.updateAccount(account, fiscalNumber);
        return "redirect:/accounts/selectedAccount";
    }

    /** Shows page for creating a new account */
    @GetMapping("/accounts/addAccount")
    public String showAddAccount(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", new Account());
            return "accounts/addAccount";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for creating a new account, redirects to specific account page */
    @PostMapping("/accounts/addAccount")
    public String postAddAccount(@ModelAttribute("account") Account account, @RequestParam Long fiscalNumber) {
        accountService.addAccount(account, fiscalNumber);
        return "redirect:/accounts/selectedAccount";
    }

    /** Shows page for deleting an existing account */
    @GetMapping("accounts/selectedAccount/delete")
    public String showDeleteAccount(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            return "accounts/selectedAccount/delete";
        } else {
            return "redirect:/login";
        }
    }

    /** API call to delete an existing account, redirects to all accounts page */
    @DeleteMapping("accounts/selectedAccount/delete")
    public String postDeleteAccount(@RequestParam Long accountNumber) {

        accountService.deleteAccount(accountNumber);
        return "redirect:/accounts";
    }
}