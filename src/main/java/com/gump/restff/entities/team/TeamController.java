package com.gump.restff.entities.team;

import com.gump.restff.entities.player.Player;
import com.gump.restff.entities.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping("/teams")
    public String getAllTeams() {
        StringBuilder sb = new StringBuilder();

        Iterable<Team> teams = teamRepository.findAll();
        for (Team team : teams) {
            sb.append(team.getName() + " (" + team.getOwner() + ") (id:" + team.getId() + ")<br>");
        }

        return sb.toString();
    }

    @RequestMapping("team/{id}")
    public String getTeamPlayers(@PathVariable long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("Printing team info...<br>");
        sb.append("<br>");

        teamRepository.findById(id).ifPresent(team -> {
            sb.append("Team Name: " + team.getName());
            sb.append("<br>");
            sb.append("Team Owner: " + team.getOwner());
            sb.append("<br>");

            //find players
            sb.append("Roster:");
            sb.append("<br>");
            List<Player> players = playerRepository.findByTeam(team);
            for (Player player : players) {
                sb.append(player.getName() + " : " + player.getPosition());
                sb.append("<br>");
            }
        });



        return sb.toString();
    }
}
