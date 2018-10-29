/**
 * JPA Entity for Team
 * Team represents a fantasy football team, not an NFL team.
 */

package com.gump.restff.entities.team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Team {
    @Id
    private Long id;
    private String name;
    private String owner;

    public Team(long id, String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.id = id;
    }

    //for JPA
    protected Team() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }
}
