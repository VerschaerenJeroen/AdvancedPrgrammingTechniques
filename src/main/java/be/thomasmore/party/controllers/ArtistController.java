package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.repositories.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArtistController {
    private Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping({"/artistlist"})
    public String artistList(Model model,
                             @RequestParam(required = false) String keyword) {
        logger.info(String.format("artistList -- keyword=%s", keyword));

        List<Artist> artists;

        artists = artistRepository.findArtistsByArtistNameContainingQuery(keyword);


        model.addAttribute("artists", artists);
        model.addAttribute("keyword", keyword);
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
