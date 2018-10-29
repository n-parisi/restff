package com.gump.restff.entities.team;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    List<Team> findByName(String name);
    List<Team> findByOwner(String owner);
}
