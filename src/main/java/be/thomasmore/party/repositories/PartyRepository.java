package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartyRepository extends CrudRepository<Party, Integer> {
    @Query("select p from Party p where p.venue = ?1")
    List<Party> findPartyByVenue(Venue venue);
}
