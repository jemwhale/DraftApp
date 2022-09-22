package com.jackwhale.multiverse;

import java.util.ArrayList;

public class Draft {
    static private int totalDrafts;
    private int id;
    private int teams;
    private int rounds;
    private String draftOrder;
    private int totalPicks;
    private int currentPick;
    private int currentRound;
    public int year;
    private static ArrayList<Draft> allDrafts = new ArrayList<Draft>();
    public ArrayList<Player> picksPlayersArr = new ArrayList<Player>();
    public ArrayList<Team> picksTeamsArr = new ArrayList<Team>();
    private ArrayList<Team> teamOrderArr = new ArrayList<Team>();
    int leagueID;
    Boolean complete = false;

    Draft(int leagueID, int teams, int rounds, int year,  String draftOrder){
        this.teams = teams;
        this.rounds = rounds;
        this.year = year;
        this.leagueID = leagueID;
        this.currentPick = 1;
        this.currentRound = 1;
        totalDrafts ++;
        this.id = totalDrafts- 1;
        this.draftOrder = draftOrder;
        this.totalPicks = this.teams*this.rounds;

        for (int i = 0; i < totalPicks; i ++){
            picksPlayersArr.add(i, null);
        }

        for (int i = 0; i < totalPicks; i ++){
            picksTeamsArr.add(i, null);
        }

        allDrafts.add(this);
    }

    // GET METHODS ------------------------------------------------------------------------

    //Get draft details by id
    public static Draft getDraftByID(int id){
        if(id > allDrafts.size() - 1 || id < 0){
            System.out.println(" ");
            System.out.println("There is no draft with id " +  id);
            if ((allDrafts.size() - 1) == -1){
                System.out.println("There are no drafts registered with this app!");
                return null;
            }else{
                System.out.println("Valid ids are between 0 and " +  (allDrafts.size() - 1));
                System.out.println(" ");
                return null;
            }
        }else{
            Draft draft = allDrafts.get(id);
            League league = League.getLeagueByID(draft.leagueID);
            return draft;
        }

    }

    //Get total number of drafts
    public static void getTotalDrafts(){
        System.out.println("Total Drafts: " + totalDrafts);
    }

    public static void listAll() {
        for (int i = 0; i < allDrafts.size(); i++){
            League league = League.getLeagueByID(allDrafts.get(i).leagueID);
            System.out.println("-ID:   " + allDrafts.get(i).id + " ----- League: " + league.name + " ----- League ID: " + allDrafts.get(i).leagueID + " ----- Year: " + allDrafts.get(i).year);
        }
    }

    // GET METHODS from variables --------------------------------------------------------

    //Get draft details
    public void getDraft(){
        System.out.println("------------------------");
        System.out.println("Draft Details");
        System.out.println(" ");
        System.out.println("ID: " + id);
        League league = League.getLeagueByID(leagueID);
        String leagueName = league.getLeagueName(league);
        System.out.println("League: " + leagueName);
        System.out.println("Year: " + year);
        System.out.println("--");
        System.out.println("Teams: " + teams);
        System.out.println("Rounds: " + rounds);
        System.out.println("Total Picks: " + totalPicks);
        System.out.println("--");
        if(complete){
            System.out.println("Status: Completed");
            System.out.println("Current Pick: N/A");
            System.out.println("Current Round: N/A");
        }else{
            if(currentPick == 1){
                System.out.println("Status: Not Started");
            }else{
                System.out.println("Status: In Progress");
            }
            System.out.println("Current Pick: " + currentPick);
            System.out.println("Current Round: " + currentRound);
        }
        System.out.println("--");
        System.out.println("Order: " + draftOrder);
        if(teamOrderArr.size() > 0){
            for(int i = 0; i < teamOrderArr.size(); i++){
                Team team  = teamOrderArr.get(i);
                System.out.println((i + 1) + ": " + team.name);
            }
        }
        System.out.println(" ");
    }

    //Get draft ID
    public int getDraftID(Draft draft){
        return draft.id;
    }

    //Get list of teams
    public int getTeamsOfDraft(){
        League league = League.getLeagueByID(leagueID);
        return league.getTeamsOfLeague();
    }

    //Get league
    public ArrayList getLeagueTeamsArray(){
        League league = League.getLeagueByID(leagueID);
        ArrayList teamsArr = league.getTeamsArr();
        return teamsArr;
    }

    public int getYear() { return year; }

    public void onTheClock(){
        System.out.println(" ");
        if (!complete) {
            Team team = picksTeamsArr.get(currentPick - 1);
            System.out.println("With pick " + currentPick + " " + team.name + " are on the clock!");
        }else{
            System.out.println("This draft has concluded!");
        }
        System.out.println(" ");
    }

    public void getNextPick(){
        System.out.println(" ");
        if (!complete){
            if (currentPick != totalPicks){
                    System.out.println("The next pick is " + (currentPick + 1)+ " and " + picksTeamsArr.get(currentPick).name + " are picking.");
            }else{
                    System.out.println("The current pick " + (currentPick) + " is the final pick!");
            }
    }else{
            System.out.println("This draft has concluded!");
        }
        System.out.println(" ");
    }

    public void getPreviousPick(){
        System.out.println(" ");
        if (!complete){
            if (currentPick != 1){
                System.out.println("The previous pick was " + (currentPick - 1)+ " and " + picksTeamsArr.get(currentPick - 2).name + " chose: ");
                System.out.println(picksPlayersArr.get(currentPick - 2).position + " " + picksPlayersArr.get(currentPick - 2).team + " #" + picksPlayersArr.get(currentPick - 2).number + " " + picksPlayersArr.get(currentPick - 2).name);
            }else{
                System.out.println("The current pick is the first overall pick!");
            }
        }else{
            System.out.println("This draft has concluded!");
        }
        System.out.println(" ");
    }

    public void getPicksByRound(int round){

        if (currentPick != 1){
            if(round < 1 || round > currentRound){
                System.out.println("Invalid round - please choose between 1 and " + currentRound);
            }else{
                int startingPick  = (round*teams)-teams;
                System.out.println(" ");
                System.out.println("ROUND " + round + "----");
                System.out.println(" ");

                if (startingPick == currentPick - 1){
                    System.out.println("No picks made in this round so far!");
                }else{
                    for (int i = startingPick; i < currentPick - 1; i ++){
                        System.out.println((i + 1) + " " + picksTeamsArr.get(i).name);
                        System.out.println(picksPlayersArr.get(i).position + " " + picksPlayersArr.get(i).team + " #" + picksPlayersArr.get(i).number + " " + picksPlayersArr.get(i).name);
                        System.out.println(" ");
                    }
                }
            }
        }else{
            System.out.println("No result to show as draft not yet started!");
        }
    }

    public void printAllPicks(){

        double dPick;
        double dTeams = teams;
        double dRounds;
        double storedRound = 0;
        double dCurrentRound;

        System.out.println(" ");
        if (!complete){
            System.out.println("This draft is still in progress!");
            System.out.println("All picks so far: ");
        }else{
            System.out.println("This draft has concluded!");
            System.out.println("Draft results: ");
        }
        if (currentPick == 1){
            System.out.println(" ");
            System.out.println("No picks made yet!");
            System.out.println(" ");
        }else{
            System.out.println(" ");
            for (int i = 0; i < currentPick - 1; i ++){
                dPick = i+1;
                dRounds = dPick/dTeams;
                dCurrentRound = (int) Math.ceil(dRounds);
                if(dCurrentRound != storedRound){
                    storedRound = dCurrentRound;
                    System.out.println(" ");
                    System.out.println("ROUND " + dCurrentRound + "----");
                    System.out.println(" ");
                }
                System.out.println((i+1) + " " + picksTeamsArr.get(i).name);
                System.out.println(picksPlayersArr.get(i).position + " " + picksPlayersArr.get(i).team + " #" + picksPlayersArr.get(i).number + " " + picksPlayersArr.get(i).name);
                System.out.println(" ");
            }
            System.out.println(" ");
        }
    }


    // UPDATE METHODS --------------------------------------------------------------------
    public void setDraftOrder(Team team, int index){
        if(index >= teamOrderArr.size() || index < 0){
            teamOrderArr.add(team);
        }else{
            teamOrderArr.set(index, team);
        }
    }

    //Fill draft order
    public void fillDraftOrder(){
        System.out.println(" ");
        if (draftOrder.equals("Linear")){
            for (int i = 0; i < rounds; i ++){
                for (int j = 0; j < teams; j ++){
                    picksTeamsArr.set((i*teams)+j, teamOrderArr.get(j));
                }
            }
        }else{
            for (int i = 0; i < rounds; i ++){
                    if (i % 2 == 0) {
                        for (int j = 0; j < teams; j ++){
                            picksTeamsArr.set((i * teams) + j, teamOrderArr.get(j));
                        }
                    }else{
                        int k = 0;
                        for (int j = teams -1; j > -1; j --){
                            picksTeamsArr.set((i*teams) + k, teamOrderArr.get(j));
                            k++;
                        }
                    }
            }
        }
        for (int i = 0; i < picksTeamsArr.size(); i ++){
            System.out.println("pick " + (i+1) + " " + picksTeamsArr.get(i).name);
        }
        System.out.println(" ");
    }

    // Make pick
    public void pickPlayer(int playerID){
        if (!complete){
                Player player = Player.getPlayerByID(playerID);
                picksPlayersArr.set(currentPick - 1, player);
                Team team = picksTeamsArr.get(currentPick - 1);
                team.addPlayer(player);
                currentPick ++;
                double dPick = (double) currentPick;
                double dTeams = (double) teams;
                double dRounds = dPick/dTeams;
                currentRound = (int) Math.ceil(dRounds);
                System.out.println("Player successfully added!");
            }
        if (currentPick > totalPicks){
            complete = true;
            System.out.println("Draft complete!");
        }
    }

    // Reset teams
    public void resetTeams(){
        for (int i=0; i < teamOrderArr.size(); i++){
            Team team = teamOrderArr.get(i);
            team.resetTeam(this);
        }
    }

    public void printUpcomingPicks() {
        double dPick;
        double dTeams = teams;
        double dRounds;
        double storedRound = 0;
        double dCurrentRound;

        System.out.println(" ");
        if (!complete){
            System.out.println("Upcoming picks:");
            if(currentPick == totalPicks){
                System.out.println(" ");
                System.out.println("Last pick! No more upcoming picks.");
                System.out.println(" ");
            }else{
                System.out.println(" ");
                for (int i = currentPick - 1; i <= totalPicks - 1; i ++){
                    dPick = i+1;
                    dRounds = dPick/dTeams;
                    dCurrentRound = (int) Math.ceil(dRounds);
                    if(dCurrentRound != storedRound){
                        storedRound = dCurrentRound;
                        System.out.println(" ");
                        System.out.println("ROUND " + dCurrentRound + "----");
                        System.out.println(" ");
                    }
                    System.out.println((i+1) + " " + picksTeamsArr.get(i).name);
                }
                System.out.println(" ");
            }

        }else{
            System.out.println("This draft has concluded! No upcoming picks!");

        }
    }

    //Get current pick details

    //Get current draft results

    //Get pick details by pick number

    //Get pick number of player by id

    //Get pick details by player name search

    //Reset draft to previous pick



}
