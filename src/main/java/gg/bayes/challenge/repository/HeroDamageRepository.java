package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.HeroDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroDamageRepository extends JpaRepository<HeroDamage, Long> {
    @Query(value = "SELECT hero_damage.* " +
            "FROM " +
            "hero_damage " +
            " where hero_damage.hero_id = :hero_id and hero_damage.match_id = :match_id and hero_damage.target_hero_id = :target_hero_id ", nativeQuery = true)
    HeroDamage get(@Param("hero_id") Long heroId, @Param("match_id") Long matchId, @Param("target_hero_id") Long targetHeroId);

    @Query(value = "SELECT hero_damage.* " +
            "FROM " +
            "hero_damage " +
            "INNER JOIN hero ON hero_damage.hero_id = hero.id " +
            " where hero_damage.match_id = :match_id and hero.name = :hero_name ", nativeQuery = true)
    List<HeroDamage> getHeroDamageByMatchAndHero(@Param("match_id") Long matchId, @Param("hero_name") String heroName);
}