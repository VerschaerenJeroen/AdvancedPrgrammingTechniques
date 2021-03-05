package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    @Query("select v from Venue v where ?1 is null and ?2 is null or" +
            " ?1 is null and v.capacity <= ?2 or" +
            " ?2 is null and v.capacity >= ?1 or" +
            " v.capacity between ?1 and ?2")
    List<Venue> findByCapacityBetweenQuery(Integer min, Integer max);
}
