package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    @Query(value = "SELECT hero.* " +
            "FROM " +
            "hero " +
            " where hero.name = :name", nativeQuery = true)
    Hero get(@Param("name") String name);

}
