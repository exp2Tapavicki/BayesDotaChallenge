package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hero_damage")
public class HeroDamage {
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
    @Column(name = "target_hero_id")
    @JsonIgnore
    private Long targetHeroId;
    @Column(name = "target")
    private String target;
    @JsonProperty("damage_instances")
    @Column(name = "damage_instances")
    private Integer damageInstances;
    @JsonProperty("total_damage")
    @Column(name = "total_damage")
    private Integer totalDamage;
}
