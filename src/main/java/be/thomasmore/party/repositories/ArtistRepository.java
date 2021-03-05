package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArtistRepository extends CrudRepository<Artist,Integer> {
    ArrayList<Artist> findArtistsByArtistNameContainingIgnoreCase(String name);
}

