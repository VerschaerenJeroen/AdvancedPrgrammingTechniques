package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    @Query("select a from Artist a where upper(a.artistName) like upper(concat('%',?1,'%'))  or" +
            " upper(a.bio) like upper(concat('%',?1,'%')) or" +
            " upper(a.genre) like upper(concat('%',?1,'%')) or" +
            " upper(a.portfolio) like upper(concat('%',?1,'%'))")
    List<Artist> findArtistsByArtistNameContainingQuery(String name);
}

