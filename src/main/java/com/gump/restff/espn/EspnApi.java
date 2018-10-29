package com.gump.restff.espn;

import com.gump.restff.entities.player.Player;
import com.gump.restff.entities.team.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EspnApi {

    protected EspnApi() {}

    public static List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
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

    public static List<Player> getPlayers(Team team, int week) {
        List<Player> players = new ArrayList<>();
        long teamId = team.getId();
        JSONArray roster = EspnHttp.getRosterForWeek(teamId, week);

        for (int i = 0; i < roster.length(); i++) {
            JSONObject playerNode = roster.getJSONObject(i);
            JSONObject playerObj = playerNode.getJSONObject("player");

            long id = playerObj.getInt("playerId");
            String playerName = playerObj.getString("firstName") + " " + playerObj.getString("lastName");
            int positionId = playerObj.getInt("defaultPositionId");
            String position = getPosition(positionId);

            JSONObject currentPeriodStats = playerNode.getJSONObject("currentPeriodRealStats");
            double latestScore;
            if (currentPeriodStats.has("appliedStatTotal")) {
                latestScore = playerNode.getJSONObject("currentPeriodRealStats").getDouble("appliedStatTotal");
            } else {
                latestScore = 0;
            }

            Player player = new Player(id, playerName, position, team);
            player.setLatestScore(latestScore);

            players.add(player);
        }

        return players;
    }

    private static String getPosition(Integer id) {
        switch (id) {
            case 1:
                return "QB";
            case 2:
                return "RB";
            case 3:
                return  "WR";
            case 4:
                return "TE";
            case 5:
                return "K";
            case 16:
                return "DST";
            default:
                return id.toString();
        }
    }
}
