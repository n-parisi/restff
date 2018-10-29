A Spring Boot web application which provides a convenient REST API for interacting with data parsed from ESPN FF API.

An embedded H2 database is populated with objects parsed from an undocumented ESPN API which returns JSON. This application attempts to parse those large JSON dumps into more sensible database objects, and then provides a web REST API to access information about a fantasy football league. The long term goal is to create a front end "homepage" replacement which displays all sorts of fantasy football information for a league in new and interesting ways.

The entities package contains DB entities and corresponding repositories and controllers. Repositories act as database access objects. Controllesr provide the routes for the REST API. 

The ESPN package contains classes for getting and parsing JSON from the ESPN API.

Build with `./mvnw clean install` on linux using Maven wrapper. Run with `java -jar` on jar file in target directory.

Available routes: <br>
/teams - List all teams <br>
/team/id -  (int id) List roster for team with given ID for week 8
