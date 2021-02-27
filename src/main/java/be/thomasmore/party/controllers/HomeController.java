package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import be.thomasmore.party.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private ArtistRepository artistRepository;

    private final String dayToday = LocalDate.now().format(DateTimeFormatter.ofPattern("E"));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime payDay = now.plusDays(30);

    String dateTimeString1 = now.format(formatter);
    String dateTimeString2 = payDay.format(formatter);

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        model.addAttribute("dayToday", dayToday);
        model.addAttribute("dateNow", dateTimeString1);
        model.addAttribute("payDay", dateTimeString2);
        return "pay";
    }

    @GetMapping("/venuelist")
    public String venuelist(Model model) {
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{id}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false)  Integer id) {
        if (id!=null && venueRepository.findById(id).isPresent() ) {
            model.addAttribute("venue", venueRepository.findById(id).get());
            model.addAttribute("prevIndex", id>1 ? id-1 : venueRepository.count());
            model.addAttribute("nextIndex", id<venueRepository.count() ? id+1 : 1);
        }
        return "venuedetails";
    }

    @GetMapping("/venuelist/outdoor/{value}")
    public String venuelistOutdoorYes(Model model,
                                      @PathVariable(required = false) String value) {
        Iterable<Venue> venues;

        if (value.equals("yes")) {
            venues = venueRepository.findByOutdoor(true);
            model.addAttribute("yo",true);
        }
        else if (value.equals("no")) {
            venues = venueRepository.findByOutdoor(false);
            model.addAttribute("no",true);
        }
        else {
            venues = venueRepository.findAll();
            model.addAttribute("all",true);
        }
        model.addAttribute("venues",venues);
        return "venuelist";
    }

    @GetMapping("/venuelist/indoor/{value}")
    public String venuelistIndoorYes(Model model,
                                      @PathVariable(required = false) String value) {
        Iterable<Venue> venues;

        if (value.equals("yes")) {
            venues = venueRepository.findByIndoor(false);
            model.addAttribute("yi",true);
        }
        else if (value.equals("no")) {
            venues = venueRepository.findByIndoor(false);
            model.addAttribute("ni",true);
        }
        else {
            venues = venueRepository.findAll();
            model.addAttribute("all",true);
        }
        model.addAttribute("venues",venues);
        return "venuelist";
    }

    @GetMapping("/venuelist/capacity/{value}")
    public String venuelistCapacity(Model model,
                                    @PathVariable String value) {
        Iterable<Venue> venues;
            if (value.equals("s")) {
                venues = venueRepository.findByCapacityIsLessThan(200);
                model.addAttribute("small", true);
            }
            else if (value.equals("m")) {
                venues = venueRepository.findByCapacityBetween(200, 500);
                model.addAttribute("medium", true);
            }
            else if (value.equals("l")) {
                venues = venueRepository.findByCapacityGreaterThan(600);
                model.addAttribute("large", true);
            }
            else {
                venues = venueRepository.findAll();
                model.addAttribute("all", true);
            }
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    @GetMapping({"/artistlist"})
    public String artistList(Model model) {
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "artistlist";
    }

    @GetMapping({"/artistdetails", "/artistdetails/{id}"})
    public String artistDetails(Model model,
                               @PathVariable(required = false)  Integer id) {
        if (id!=null && artistRepository.findById(id).isPresent() ) {
            model.addAttribute("artist", artistRepository.findById(id).get());
            model.addAttribute("prevIndex", id>1 ? id-1 : artistRepository.count());
            model.addAttribute("nextIndex", id<artistRepository.count() ? id+1 : 1);
        }
        return "artistdetails";
    }
}
