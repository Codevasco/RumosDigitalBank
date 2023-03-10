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
    public String showCards(@ModelAttribute("card") Card card, @RequestBody Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            model.addAttribute("card", card);
            cardService.findAllCards();
            return "cards";
        } else {
            return "redirect:/login";
        }
    }

    /** Shows page for single card page for specific account */
    @GetMapping("/cards/selectedCard")
    public String showSingleCard(@ModelAttribute("card") Card card, @RequestBody Account account, @RequestParam Long cardNumber, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            model.addAttribute("card", card);
            cardService.findByCardNumber(cardNumber);
            return "cards/selectedCard";
        } else {
            return "redirect:/login";
        }
    }

    /** Shows page for card pin update */
    @GetMapping("/cards/selectedCard/update")
    public String showUpdateCard(@ModelAttribute("card") Card card, @RequestBody Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            model.addAttribute("card", card);
            return "cards/selectedCard/update";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for updating card pin, redirects to specific card page */
    @PostMapping("/cards/selectedCard/update")
    public String postUpdateCard(@ModelAttribute("card") Card card, @RequestParam Long cardNumber) {
        cardService.updateCardPin(card, cardNumber);
        return "redirect:/cards/selectedCard";
    }

    /** Shows page for creating a new card */
    @GetMapping("/cards/addCard")
    public String showAddCard(@ModelAttribute("card") Card card, @RequestBody Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            model.addAttribute("card", new Card());
            return "cards/addCard";
        } else {
            return "redirect:/login";
        }
    }

    /** API call for creating a new card, redirects to all cards page */
    @PostMapping("/cards/addCard")
    public String postAddCard(@ModelAttribute("card") Card card, @RequestParam Integer accountNumber) {
        cardService.addCard(card, accountNumber);
        return "redirect:/cards/";
    }

    /** Shows page for deleting an existing card */
    @GetMapping("/cards/selectedCard/delete")
    public String showDeleteCard(@ModelAttribute("card") Card card, @RequestBody Account account, Model model, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("account", account);
            return "cards/selectedCard/delete";
        } else {
            return "redirect:/login";
        }
    }

    /** API call to delete an existing card, redirects to all cards page */
    @DeleteMapping("/cards/selectedCard/delete")
    public String postDeleteCard(@RequestParam Long cardNumber) {

        cardService.deleteCard(cardNumber);
        return "redirect:/cards";
    }
}