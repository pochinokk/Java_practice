package com.example.Practice21.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", insertable = false, updatable = false)
    @JsonIgnore
    private Game game;
}
