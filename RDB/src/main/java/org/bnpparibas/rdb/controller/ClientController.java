package org.bnpparibas.rdb.controller;

import jakarta.servlet.http.HttpSession;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.service.implementation.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;


    /**
     * Shows client information page
     */
    @GetMapping("/information")
    public String showClientForm(Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            return "information";
        } else {
            return "redirect:/login";
        }
    }

    // public String showClientUpdateForm;

    /** API call for updating client information, redirects to information */
    @PostMapping("/updateClient")
    public String postUpdateClientForm(@ModelAttribute("client") Client client, @RequestParam Long fiscalNumber) {

        clientService.updateClient(client, fiscalNumber);
        return "redirect:/information";
    }

    /** Shows client deletion page */
    @GetMapping("/deleteClient")
    public String showDeleteClientForm(Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            return "deleteClient";
        } else {
            return "redirect:/login";
        }
    }

    /** API call to delete client, redirects to login */
    @DeleteMapping("/deleteClient")
    public String postDeleteClientForm(@RequestParam Long fiscalNumber) {

        clientService.deleteClient(fiscalNumber);
        return "redirect:/login";
    }
}