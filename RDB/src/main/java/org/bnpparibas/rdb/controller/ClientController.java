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
    @GetMapping("/client")
    public String showClient(@RequestParam Long fiscalNumber, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            clientService.findByFiscalNumber(fiscalNumber);
            return "client";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Shows client update page
     */
    @GetMapping("/client/updateClient")
    public String showClientUpdateForm(Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            return "client/updateClient";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for updating client information, redirects to information */
    @PostMapping("/client/updateClient")
    public String postUpdateClientForm(@ModelAttribute("client") Client client, @RequestParam Long fiscalNumber) {

        clientService.updateClient(client, fiscalNumber);
        return "redirect:/information";
    }

    /** Shows client deletion page */
    @GetMapping("/client/deleteClient")
    public String showDeleteClientForm(Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            return "client/deleteClient";
        } else {
            return "redirect:/login";
        }
    }

    /** API call to delete client, redirects to login */
    @DeleteMapping("/client/deleteClient")
    public String postDeleteClientForm(@RequestParam Long fiscalNumber) {

        clientService.deleteClient(fiscalNumber);
        return "redirect:/login";
    }
}