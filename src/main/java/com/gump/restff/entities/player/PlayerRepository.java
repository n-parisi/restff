package com.gump.restff.entities.player;

import com.gump.restff.entities.team.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findByName(String name);
    List<Player> findByTeam (Team team);
}
