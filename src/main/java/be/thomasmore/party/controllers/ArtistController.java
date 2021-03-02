package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArtistController {
    @Autowired
    private be.thomasmore.party.repository.ArtistRepository artistRepository;

    @GetMapping({"/artistlist"})
    public String artistList(Model model) {
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "artistlist";
    }

    @GetMapping({"/artistdetails", "/artistdetails/{id}"})
    public String artistDetails(Model model,
                                @PathVariable(required = false)  Integer id) {
        if (id!=null && artistRepository.findById(id).isPresent() )
            model.addAttribute("artist", artistRepository.findById(id).get());
            model.addAttribute("prevIndex", id>1 ? id - 1 : artistRepository.count());
            model.addAttribute("nextIndex", id<artistRepository.count() ? id + 1 : 1);
        return "artistdetails";
    }
}
