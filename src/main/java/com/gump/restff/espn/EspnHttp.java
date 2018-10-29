package com.gump.restff.espn;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

class EspnHttp {

    private static final String API_ROOT = "http://games.espn.com/ffl/api/v2/";
    private static final int LEAGUE_ID = 517437;
    private static final int SEASON_ID = 2018;

    private EspnHttp() {}

    public static JSONArray getRosterForWeek(Long teamId, int week) {
        try {
           HttpResponse<JsonNode> response = Unirest.get(API_ROOT +"boxscore")
                    .queryString("leagueId", LEAGUE_ID)
                    .queryString("seasonId", SEASON_ID)
                    .queryString("teamId", teamId)
                    .queryString("matchupPeriodId", week)
                    .asJson();

            JSONObject root = response.getBody().getObject();
            JSONArray teams = root.getJSONObject("boxscore").getJSONArray("teams");
            for (int i = 0; i < teams.length(); i++) {
                JSONObject team = teams.getJSONObject(i);

                if (team.getInt("teamId") == teamId.intValue()) {
                    return team.getJSONArray("slots");
                }
            }

            return new JSONArray();
        } catch (UnirestException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static JSONArray getTeams() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(API_ROOT +"teams")
                    .queryString("leagueId", LEAGUE_ID)
                    .queryString("seasonId", SEASON_ID)
                    .asJson();

            JSONObject root = response.getBody().getObject();
            JSONArray teams = root.getJSONArray("teams");

            return teams;
        } catch (UnirestException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
}