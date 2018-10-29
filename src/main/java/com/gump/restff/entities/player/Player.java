package com.gump.restff.entities.player;

import com.gump.restff.entities.team.Team;

import javax.persistence.*;

@Entity
public class Player {
    @Id
    private Long id;
    private String name;
    private String position;
    @ManyToOne
    private Team team;

    public Player(long id, String name, String position, Team team) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.team = team;
    }

    //for JPA
    protected Player() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Team getTeam() {
        return team;
    }
}
