package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private VenueRepository venueRepository;

    @ModelAttribute("party")
    public Party findParty(@PathVariable(required = false) Integer id) {
        logger.info("findparty " + id);
        if (id == null) return new Party();

        Optional<Party> p = partyRepository.findById(id);
        return p.orElse(null);
    }

    @RequestMapping("/partyedit/{id}")
    public String partyEdit(Model model,
                            @PathVariable  int id){
        Iterable<Venue> venues = venueRepository.findAll();
        logger.info("partyEdit " + id);
        model.addAttribute("venues", venues);
        return "admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(Model model,
                                @PathVariable  int id,
                                @ModelAttribute("party") Party party,
                                @RequestParam Integer venueId){

        logger.info("partyEditPost" + id + " -- new name=" + party.getName());
        if (party.getVenue().getId() != venueId) {
            Venue venue = new Venue();
            venue.setId(venueId);
            party.setVenue(venue);
        }
        partyRepository.save(party);
        return "redirect:/partydetails/" + id;
    }

    @RequestMapping("/partynew")
    public String partyNew(Model model){
        Iterable<Venue> venues = venueRepository.findAll();
        Party party = new Party();

        model.addAttribute("party", party);
        model.addAttribute("venues", venues);

        return "admin/partynew";
    }

    @PostMapping({"/partynew"})
    public String partyNewPost(Model model,
                               @ModelAttribute("party") Party party,
                               @RequestParam Integer venueId) {

        logger.info(" -- new name=" + party.getName());
        party.setVenue(new Venue(venueId));
        Party newParty = partyRepository.save(party);
        return "redirect:/partydetails/" + newParty.getId();
    }
}
