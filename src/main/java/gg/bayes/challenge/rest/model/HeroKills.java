package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hero_kills")
public class HeroKills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private Long id;
    @Column(name = "match_id")
    @JsonIgnore
    private Long matchId;
    @Column(name = "hero_id")
    @JsonIgnore
    private Long heroId;
    @Column(name = "hero")
    private String hero;
    @Column(name = "kills")
    private Integer kills;
}
