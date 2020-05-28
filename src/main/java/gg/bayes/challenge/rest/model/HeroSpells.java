package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hero_spells")
public class HeroSpells {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private Long id;
    @Column(name = "match_id")
    @JsonIgnore
    private Long matchId;
    @JsonIgnore
    @Column(name = "hero_id")
    private Long heroId;
    @Column(name = "spell")
    private String spell;
    @Column(name = "casts")
    private Integer casts;
}
