package com.example.Practice21.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "games")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "gamesIdSeq", sequenceName = "games_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gamesIdSeq")
    private long id;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Level> levels;
}
