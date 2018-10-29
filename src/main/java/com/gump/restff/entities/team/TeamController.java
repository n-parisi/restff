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
        sb.append("Printing team info for week 8...<br>");
        sb.append("<br>");

        teamRepository.findById(id).ifPresent(team -> {
            sb.append("Team Name: " + team.getName());
            sb.append("<br>");
            sb.append("Team Owner: " + team.getOwner());
            sb.append("<br><br>");

            //find players
            sb.append("Roster:");
            sb.append("<br><br>");
            List<Player> players = playerRepository.findByTeam(team);

            StringBuilder qbStr = new StringBuilder();
            StringBuilder rbStr = new StringBuilder();
            StringBuilder wrStr = new StringBuilder();
            StringBuilder teStr = new StringBuilder();
            //StringBuilder benchStr = new StringBuilder();
            String dstStr = "";
            String kStr =  " ";

            for (Player player : players) {
                String position = player.getPosition();
                String playerStr = player.getName() + " " + player.getLatestScore() + "<br>";
                switch(position) {
                    case "QB":
                        qbStr.append(playerStr);
                        break;
                    case "RB":
                        rbStr.append(playerStr);
                        break;
                    case "WR":
                        wrStr.append(playerStr);
                        break;
                    case "TE":
                        teStr.append(playerStr);
                        break;
                    case "DST":
                        dstStr = playerStr;
                        break;
                    case "K":
                        kStr = playerStr;
                        break;
                    default:
                        teStr.append(playerStr);
                        break;
                }

            }
            sb.append("QB: <br>");
            sb.append(qbStr.toString() + "<br>");
            sb.append("RB: <br>");
            sb.append(rbStr.toString() + "<br>");
            sb.append("WR: <br>");
            sb.append(wrStr.toString() + "<br>");
            sb.append("TE: <br>");
            sb.append(teStr.toString() + "<br>");
            sb.append("DST: <br>");
            sb.append(dstStr + "<br>");
            sb.append("K: <br>");
            sb.append(kStr + "<br>");
            //sb.append("Bench: <br>");
            //sb.append(benchStr);

        });

        return sb.toString();
    }
}
