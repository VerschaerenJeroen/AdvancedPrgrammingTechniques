package be.thomasmore.party.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    private final String dayToday = LocalDate.now().format(DateTimeFormatter.ofPattern("E"));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime payDay = now.plusDays(30);

    String dateTimeString1 = now.format(formatter);
    String dateTimeString2 = payDay.format(formatter);

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        model.addAttribute("dayToday", dayToday);
        model.addAttribute("dateNow", dateTimeString1);
        model.addAttribute("payDay", dateTimeString2);
        return "pay";
    }
}
