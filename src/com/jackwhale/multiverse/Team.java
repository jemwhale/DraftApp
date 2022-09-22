package com.jackwhale.multiverse;

import java.util.ArrayList;
import java.util.Objects;

public class Team {

    static private int totalTeams;
    public String name;
    private String owner;
    private int id;
    private int leagueID;

    private static ArrayList<Team> allTeams = new ArrayList<Team>();
    public ArrayList<Player> playerArr = new ArrayList<Player>();


    // Constructor to initialize fields
    Team(int leagueID, String name, String owner){
        this.name = name;
        this.owner = owner;
        totalTeams ++;
        this.id = totalTeams- 1;
        this.leagueID = leagueID;
        allTeams.add(this);
    }


    // GET METHODS ------------------------------------------------------------------------

    //Get current team

    //Get team details by id
    public static Team getTeamByID(int id){
        if(id > allTeams.size() - 1 || id < 0){
            System.out.println(" ");
            System.out.println("There is no team with id " +  id);
            if ((allTeams.size() - 1) == -1){
                System.out.println("There are no teams registered with this app!");
                return null;
            }else{
                System.out.println("Valid ids are between 0 and " +  (allTeams.size() - 1));
                System.out.println(" ");
                return null;
            }
        }else{
            Team team = allTeams.get(id);
            return team;
        }

    }

    //Get total number of teams
    public static void getTotalTeams(){
        System.out.println("Total Teams: " + totalTeams);
    }

    public static void listAll() {
        for (int i = 0; i < allTeams.size(); i++){
            League league = League.getLeagueByID(allTeams.get(i).leagueID);
            System.out.println("-ID:   " + allTeams.get(i).id + " ----- " + allTeams.get(i).name + " ----- Owner: " + allTeams.get(i).owner + " ----- League: " + league.name + " ----- League ID: " + allTeams.get(i).leagueID);
        }
    }


    // GET METHODS from variables --------------------------------------------------------

    //Get team details
    public void getTeam(){
        System.out.println("------------------------");
        System.out.println(name);
        System.out.println("Owner: " + owner);
        System.out.println("ID: " + id);
        System.out.println(" ");
        try{
            this.displayCurrentPlayers();
            System.out.println(" ");
        }catch (Exception e){
            System.out.println(" ");
            System.out.println("No players currently drafted");
        }


    }

    //Get team ID
    public int getTeamID(){
        return id;
    }

    public String getTeamName(){
        return name;
    }

    public void displayCurrentPlayers(){
        League league = League.getLeagueByID(leagueID);
        Draft draft = league.setLatestDraft();
        if (Objects.isNull(draft)){
        }else{
            this.resetTeam(draft);
            System.out.println("Current Team (" + draft.year + "):");
            System.out.println(" ");
            for (int i = 0; i < playerArr.size(); i++){
                System.out.println(playerArr.get(i).position + " " + playerArr.get(i).team + " #" + playerArr.get(i).number + " " + playerArr.get(i).name);
            }

        }
    }

    // UPDATE METHODS --------------------------------------------------------------------

    //Update team name
    static void changeName(int id){
        Team team = allTeams.get(id);
        team.name = "new name";
    }

    //Update team name
    public void resetTeam(Draft draft){
        int year = draft.year;
        int index = 0;
        //Clear player array
        for (int i = playerArr.size() - 1; i >= 0; i--){
            playerArr.remove(i);
        }
        populateTeam(draft);
    }


    //Populate with new players
    public void populateTeam(Draft draft){
        Team team;
        for (int i=0; i < draft.picksTeamsArr.size(); i++){
            team = draft.picksTeamsArr.get(i);
            if(team.equals(this)){
                Player player = draft.picksPlayersArr.get(i);
                if(Objects.isNull(player)){
                }else{
                    this.playerArr.add(draft.picksPlayersArr.get(i));
                }
            }
        }
    }



    public void addPlayer(Player player){
        playerArr.add(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return leagueID == team.leagueID && name.equals(team.name) && owner.equals(team.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owner, leagueID);
    }
}
