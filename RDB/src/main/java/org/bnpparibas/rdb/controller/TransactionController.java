package org.bnpparibas.rdb.controller;

import jakarta.servlet.http.HttpSession;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.model.operations.Deposit;
import org.bnpparibas.rdb.model.operations.Transfer;
import org.bnpparibas.rdb.model.operations.Withdrawal;
import org.bnpparibas.rdb.service.implementation.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;


    @GetMapping("/operations")
    public String showOperations(@ModelAttribute("account") Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            return "operations";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/deposit")
    public String postDeposit(@ModelAttribute("account") Account account, @RequestBody Deposit deposit, Long accountNumber) {
        transactionService.deposit(deposit, accountNumber);
        return "redirect:/operations";
    }

    @PostMapping("/withdrawal")
    public String postWithdrawal(@ModelAttribute("account") Account account, @RequestBody Withdrawal withdrawal, Long accountNumber) {
        transactionService.withdraw(withdrawal, accountNumber);
        return "redirect:/operations";
    }

    @PostMapping("/transfer")
    public String postTransfer(@ModelAttribute("account") Account account, @RequestBody Transfer transfer, Long accountNumber) {
        transactionService.transfer(transfer, accountNumber);
        return "redirect:/operations";
    }

    @GetMapping("/transactions")
    public String showTransactionsByAccountNumber(@ModelAttribute("transaction") Transaction transaction, Account account, Integer accountNumber, Model
            model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            List<Transaction> transactions = transactionService.findTransanctionsByAccountNumber(accountNumber);
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            model.addAttribute("transaction", transaction);
            model.addAttribute("transactions", transactions);
            return "transactions";

        } else {
            return "redirect:/login";
        }
    }
}