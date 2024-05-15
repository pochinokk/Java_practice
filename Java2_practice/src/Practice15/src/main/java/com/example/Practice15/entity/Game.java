package com.example.Practice15.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Game) obj).getId() &&
                this.creationDate.equals(((Game) obj).getCreationDate());
    }

    @ManyToOne()
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;
}
