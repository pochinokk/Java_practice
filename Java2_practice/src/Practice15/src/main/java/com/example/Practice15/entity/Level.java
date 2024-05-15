package com.example.Practice15.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "levels")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Level {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "levelsIdSeq", sequenceName = "levels_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "levelsIdSeq")
    private long id;
    @Column(name = "complexity")
    private String complexity;
    @Column(name = "level_name")
    private String levelName;
}
