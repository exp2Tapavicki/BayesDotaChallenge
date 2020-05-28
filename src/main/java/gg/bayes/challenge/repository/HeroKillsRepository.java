package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroKillsRepository extends JpaRepository<HeroKills, Long> {
    @Query(value = "SELECT hero_kills.* " +
            "FROM " +
            "hero_kills " +
            " where hero_kills.hero_id = :hero_id and hero_kills.match_id = :match_id", nativeQuery = true)
    HeroKills get(@Param("hero_id") Long heroId, @Param("match_id") Long matchId);

    @Query(value = "SELECT hero_kills.* " +
            "FROM " +
            "hero_kills " +
            " where hero_kills.match_id = :match_id", nativeQuery = true)
    List<HeroKills> getHeroKillsByMatch(@Param("match_id") Long matchId);
}