package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.HeroItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroItemsRepository extends JpaRepository<HeroItems, Long> {
    @Query(value = "SELECT hero_items.* " +
            "FROM " +
            "hero_items " +
            " where hero_items.hero_id = :hero_id and hero_items.match_id = :match_id", nativeQuery = true)
    HeroItems get(@Param("hero_id") Long heroId, @Param("match_id") Long matchId);

    @Query(value = "SELECT hero_items.* " +
            "FROM " +
            "hero_items " +
            "INNER JOIN hero ON hero_items.hero_id = hero.id " +
            " where hero_items.match_id = :match_id and hero.name = :hero_name ", nativeQuery = true)
    List<HeroItems> getHeroItemsByMatchAndHero(@Param("match_id") Long matchId, @Param("hero_name") String heroName);

}