package org.bnpparibas.rdb.controller;

import jakarta.servlet.http.HttpSession;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Card;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.service.implementation.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CardController {

    @Autowired
    private CardServiceImpl cardService;


    /** Shows page listing all cards for specific account */
    @GetMapping("/cards")
    public String showCards(@ModelAttribute("card") Card card, Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            List <Card> cards = cardService.findAllCards(client.getFiscalNumber());
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            model.addAttribute("card", card);
            model.addAttribute("cards", cards);
            return "cards";
        } else {
            return "redirect:/login";
        }
    }

    /** Shows page for card pin update */
    @GetMapping("/cards-update")
    public String showUpdateCard(@ModelAttribute("card") Card card, Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            model.addAttribute("card", card);
            return "cards-update";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for updating card pin, redirects to specific card page */
    @PostMapping("/cards-update")
    public String postUpdateCard(@ModelAttribute("card") Card card, @RequestParam Integer cardNumber) {
        cardService.updateCardPin(card, cardNumber);
        return "redirect:/cards";
    }

    /** Shows page for creating a new card */
    @GetMapping("/cards-create")
    public String showAddCard(@ModelAttribute("card") Card card, Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            model.addAttribute("card", new Card());
            return "cards-create";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for creating a new card, redirects to all cards page */
    @PostMapping("/cards-create")
    public String postAddCard(@ModelAttribute("card") Card card, @RequestParam Long fiscalNumber, @RequestParam Integer accountNumber) {
        cardService.addCard(card, fiscalNumber, accountNumber);
        return "redirect:/cards";
    }

    /** Shows page for deleting an existing card */
    @GetMapping("/cards-delete")
    public String showDeleteCard(@ModelAttribute("card") Card card, Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            return "cards-delete";
        } else {
            return "redirect:/login";
        }
    }

    /** API call to delete an existing card, redirects to all cards page */
    @PostMapping("/cards-delete")
    public String postDeleteCard(@RequestParam Integer cardNumber) {
        cardService.deleteCard(cardNumber);
        return "redirect:/cards";
    }
}