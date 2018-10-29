package com.gump.restff.espn;

import com.gump.restff.entities.player.Player;
import com.gump.restff.entities.team.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EspnApi {

    protected EspnApi() {}

    public static List<Team> getTeams() {
        List<Team> teams = new ArrayList<Team>();
        JSONArray teamArr = EspnHttp.getTeams();

        for (int i = 0; i < teamArr.length(); i++) {
            JSONObject teamNode = teamArr.getJSONObject(i);

            long id = teamNode.getInt("teamId");
            String teamName = teamNode.getString("teamLocation") + " " + teamNode.getString("teamNickname");

            JSONObject ownerNode = teamNode.getJSONArray("owners").getJSONObject(0);
            String owner = ownerNode.getString("firstName") + " " + ownerNode.getString("lastName");

            Team team = new Team(id, teamName, owner);
            teams.add(team);
        }

        return teams;
    }
}
