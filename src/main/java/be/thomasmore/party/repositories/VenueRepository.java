package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    @Query("select v from Venue v where " +
            "(?1 is null or ?1 <= v.capacity) and" +
            "(?2 is null or ?2 >= v.capacity) and" +
            "(?3 is null or ?3 >= v.distanceFromPublicTransportInKm) and" +
            "(?4 is null or ?4 = v.foodProvided) and" +
            "(?5 is null or ?5 = v.indoor) and" +
            "(?6 is null or ?6 = v.outdoor)")
    List<Venue> findByCapacityBetweenQuery(Integer min, Integer max, Integer distanceToPublicTransport, Boolean foodProvided, Boolean indoor, Boolean outdoor);
}
