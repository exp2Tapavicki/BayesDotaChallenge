package gg.bayes.challenge.rest.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "hero")
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column (name = "name")
    private String name;
}
