package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {
    private final int mySpecialNumber = 729;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime payDay = now.plusDays(30);

    String dateTimeString1 = now.format(formatter);
    String dateTimeString2 = payDay.format(formatter);

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        return "about";
    }
    @GetMapping("/pay")
    public String pay(Model model) {
        model.addAttribute("dateNow", dateTimeString1);
        model.addAttribute("payDay", dateTimeString2);
        return "pay";
    }
    @GetMapping({"/venuedetails", "/venuedetails/{venueName}"})
    public String venueDetails(Model model, @PathVariable(required = false) String venueName) {
        model.addAttribute("venueName", (venueName!=null) ? venueName : "--no venue chosen--");
        return "venuedetails";
    }
    @GetMapping("/venuelist")
    public String venuelist(Model model) {
        return "venuelist";
    }
}
