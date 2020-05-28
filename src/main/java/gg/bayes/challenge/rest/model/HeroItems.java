package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hero_items")
public class HeroItems {
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
    @Column(name = "item")
    private String item;
    @Column(name = "timestamp")
    private Long timestamp;
}
