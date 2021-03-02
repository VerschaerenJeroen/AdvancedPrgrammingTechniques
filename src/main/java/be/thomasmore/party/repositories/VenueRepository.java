package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean outdoor);
    Iterable<Venue> findByIndoor(boolean indoor);
    ArrayList<Venue> findByCapacityGreaterThanEqual(int capacity);
    Iterable<Venue> findByCapacityBetween(int min, int max);
    Iterable<Venue> findByCapacityIsLessThan(int capacity);
}
