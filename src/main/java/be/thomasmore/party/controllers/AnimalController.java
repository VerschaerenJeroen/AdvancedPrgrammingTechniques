package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Animal;
import be.thomasmore.party.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping({"/animaldetails", "/animaldetails/{id}"})
    public String artistDetails(Model model,
                                @PathVariable(required = false)  Integer id) {
        if (id == null) return "animaldetails";

        Optional<Animal> a = animalRepository.findById(id);
        if (a.isPresent()) {
            model.addAttribute("animal", a.get());
            model.addAttribute("prevIndex", id > 1 ? id - 1 : animalRepository.count());
            model.addAttribute("nextIndex", id < animalRepository.count() ? id + 1 : 1);
        }
        return "animaldetails";
    }
}
