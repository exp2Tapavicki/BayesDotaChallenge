package gg.bayes.challenge.rest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column (name = "name")
    private String name;

}
