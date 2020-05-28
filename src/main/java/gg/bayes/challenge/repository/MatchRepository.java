package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query(value = "SELECT match.* " +
            "FROM " +
            "match " +
            " where match.name = :name", nativeQuery = true)
    Match get(@Param("name") String name);

}
