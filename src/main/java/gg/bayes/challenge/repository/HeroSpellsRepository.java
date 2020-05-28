package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.HeroSpells;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroSpellsRepository  extends JpaRepository<HeroSpells, Long> {
    @Query(value = "SELECT hero_spells.* " +
            "FROM " +
            "hero_spells " +
            " where hero_spells.hero_id = :hero_id and hero_spells.match_id = :match_id and hero_spells.spell = :spell", nativeQuery = true)
    HeroSpells get(@Param("hero_id") Long heroId, @Param("match_id") Long matchId, @Param("spell") String spell);

    @Query(value = "SELECT hero_spells.* " +
            "FROM " +
            "hero_spells " +
            "INNER JOIN hero ON hero_spells.hero_id = hero.id " +
            " where hero_spells.match_id = :match_id and hero.name = :hero_name ", nativeQuery = true)
    List<HeroSpells> getHeroSpellsByMatchAndHero(@Param("match_id") Long matchId, @Param("hero_name") String heroName);
}