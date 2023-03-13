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
    @GetMapping("/clients")
    public String showClient(Long fiscalNumber, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            clientService.findByFiscalNumber(fiscalNumber);
            return "clients";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Shows client update page
     */
    @GetMapping("/clients-update")
    public String showClientUpdateForm(Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            return "clients-update";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for updating client information, redirects to information */
    @PostMapping("/clients-update")
    public String postUpdateClientForm(@ModelAttribute("client") Client client, Long fiscalNumber) {
        clientService.updateClient(client, fiscalNumber);
        return "redirect:/clients";
    }

    /** Shows client deletion page */
    @GetMapping("/clients-delete")
    public String showDeleteClientForm(Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            return "clients-delete";
        } else {
            return "redirect:/login";
        }
    }

    /** API call to delete client, redirects to login */
    @PostMapping("/clients-delete")
    public String postDeleteClientForm(@RequestParam Long fiscalNumber) {

        clientService.deleteClient(fiscalNumber);
        return "redirect:/login";
    }
}