package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class PartyController {

    @Autowired
    private PartyRepository partyRepository;

    @GetMapping("/partylist")
    public String venuelist(Model model) {
        Iterable<Party> parties = partyRepository.findAll();
        long nrOfParties = partyRepository.count();
        model.addAttribute("parties", parties);
        model.addAttribute("nrOfVenues", nrOfParties);
        return "partylist";
    }

    @GetMapping({"/partydetails", "/partydetails/{id}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false)  Integer id) {
        if (id == null) return "partydetails";

        Optional<Party> p = partyRepository.findById(id);
        if (p.isPresent()) {
            long nrOfParties = partyRepository.count();
            Boolean priceTest = null;
            if (p.get().getPriceInEur() != null && p.get().getPricePresaleInEur() != null)   {
                priceTest = true;
            }
            else {
                priceTest = false;
            }

            model.addAttribute("party", p.get());
            model.addAttribute("prevIndex", id > 1 ? id - 1 : nrOfParties);
            model.addAttribute("nextIndex", id < nrOfParties ? id + 1 : 1);
            model.addAttribute("priceTest", priceTest);
        }

        return "partydetails";
    }
}
