package com.gump.restff;

import com.gump.restff.entities.player.Player;
import com.gump.restff.entities.player.PlayerRepository;
import com.gump.restff.entities.team.Team;
import com.gump.restff.entities.team.TeamRepository;
import com.gump.restff.espn.EspnApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        //fill our embedded server with some data

        List<Team> teams = EspnApi.getTeams();
        teamRepository.saveAll(teams);

        for (Team team : teams) {
            List<Player> players = EspnApi.getPlayers(team, 8);
            playerRepository.saveAll(players);
        }
    }

}
