import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.ECField;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {

//
        System.out.println(" ");
        System.out.println("WELCOME TO THE DRAFT APP!");
        System.out.println("------------------------");
        System.out.println("- Create a league!");
        System.out.println("- Add teams to that league!");
        System.out.println("- Create a draft for that league and draft players onto your teams!");
        System.out.println("- Add players to the app prior or during the draft!");
        System.out.println("------------------------");
        System.out.println(" ");
        System.out.println("'(c)' Indicates you can input 'C' for a list of valid command options");
        System.out.println("'(any)' Indicates you can input any String value");
        System.out.println("'(i)' Indicates you can only input an integer. Any other value will exit the process.");
        System.out.println(" ");
        System.out.println("------------------------");

//        new AppFrame();
        seed();
        readInputScanner(args);

    }
    private static void readInputScanner(String[] args) {

        //Exception handle int inputs and also clear the buffer with next line

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("------------------------");
        System.out.println("MAIN MENU");
        System.out.println("(c) Please enter your input: ");
        String input1 = scanner.nextLine();

        switch (input1.toUpperCase()){
            case "C": {
                mainCommandsList();
                readInputScanner(args);
            }
            break;
            case "NEW" : {
                readInputScannerNew(args);
            }
            break;
            case "LIST" : {
                readInputScannerList(args);
            }
            break;
            case "ONE" : {
                readInputScannerOne(args);
                }
            break;
            case "SEARCH" : {
                readInputScannerSearch(args);
            }
            break;
            case "DRAFT" : {
                readInputScannerDraft(args);
            }
            break;
            case "Q" : {
                System.exit(0);
            }
            break;
            default :
                invalidCommand(input1);
                readInputScanner(args);
        }

    }

    private static void readInputScannerDraft(String[] args) {

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println(" ");
        System.out.println("DRAFT MAIN MENU");
        System.out.println("(c) Please enter your input: ");
        String input1 = scanner.nextLine();

        switch (input1.toUpperCase()){
            case "C": {
                draftCommandsList();
                readInputScannerDraft(args);
            }
            break;
            case "E" : {
                System.out.println(" ");
                System.out.println("(i) Enter draft ID:");

                try{
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Draft draft = Draft.getDraftByID(id);
                    draft.getDraft();
                    readInputScannerLiveDraft(args, draft);
                }catch(Exception e){
                    System.out.println("Invalid input type. Please input an integer.");
                    readInputScannerDraft(args);
                }

            }
            break;
            case "O" : {
                readInputScannerCustomDraftOrder(args);
            }
            break;
            case "Q" : {
                readInputScanner(args);
            }
            break;
            default :
                invalidCommand(input1);
                readInputScannerDraft(args);
        }
    }

    private static void readInputScannerLiveDraft(String[] args, Draft draft) {

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println(" ");
        System.out.println("DRAFT ACTIONS MENU");
        System.out.println(" ");
        int draftID = draft.getDraftID(draft);
        System.out.println("You are now within draft ID: " + draftID);
        System.out.println(" ");
            draft.onTheClock();
            System.out.println(" ");
            System.out.println("(c) Please enter your input: ");
            String input1 = scanner.nextLine();


//        System.out.println("DRAFT - to see the draft details");
//        System.out.println("ALL - to see all previous picks up to the current");
//        System.out.println("THEN - to see the previous pick");
//        System.out.println("NOW - to see the current pick");
//        System.out.println("NEXT - to see the next pick");
//        System.out.println("ROUND - to see all picks from a specified round");
//        System.out.println("ONE - to see a pick by ID");
//        System.out.println("Q - To quit the process");

            switch (input1.toUpperCase()){
                case "C": {
                    liveDraftCommandsList();
                    readInputScannerLiveDraft(args, draft);
                }
                break;
                case "PICK" : {
                    if(!draft.complete){
                        try{
                            System.out.println("Enter player ID:");
                            int playerID = scanner.nextInt();
                            Player player = Player.getPlayerByID(playerID);
                            scanner.nextLine();
                            player.getPlayer();
                            System.out.println(" ");
                            System.out.println("Is this the player you wish to pick (Y/N)?");
                            switch (scanner.nextLine().toUpperCase()){
                                case "Y" : {
                                    draft.pickPlayer(playerID);
                                    readInputScannerLiveDraft(args, draft);
                                }
                                case "N" : {
                                    readInputScannerLiveDraft(args, draft);
                                }
                                default: {
                                    invalidCommand(scanner.nextLine());
                                    readInputScannerLiveDraft(args, draft);
                                }
                            }

                        }catch(Exception e){
                            System.out.println("Invalid input type. Please input an integer.");
                            readInputScannerLiveDraft(args, draft);
                        }
                    }else{
                        System.out.println("Unable ot make a pick. Draft is complete!");
                    }
                }
                break;
                case "NEW" : {
                    System.out.println("ADDING NEW PLAYER");
                    System.out.println("(any) Enter player name:");
                    String name = scanner.nextLine();
                    System.out.println("(any) Enter player position:");
                    String position = scanner.nextLine();
                    System.out.println("(any) Enter player team code:");
                    String team = scanner.nextLine();
                    System.out.println("(i) Enter player number:");
                    try{
                        int number = scanner.nextInt();
                        scanner.nextLine();
                        int playerID;
                        Player newPlayer = new Player(name, position, team, number);
                        if(Player.duplicatePlayerCheck(newPlayer) != -1){
                            playerID = Player.duplicatePlayerCheck(newPlayer);
                        }else{
                            System.out.println(" ");
                            System.out.println("New player successfully created!");
                            newPlayer.getPlayer();
                            playerID = newPlayer.getID();
                        }
                        System.out.println(" ");
                        System.out.println("Is this the player you wish to pick (Y/N)?");
                        switch (scanner.nextLine().toUpperCase()){
                            case "Y" : {
                                draft.pickPlayer(playerID);
                                readInputScannerLiveDraft(args, draft);
                            }
                            case "N" : {
                                readInputScannerLiveDraft(args, draft);
                            }
                            default: {
                                invalidCommand(scanner.nextLine());
                                readInputScannerLiveDraft(args, draft);
                            }
                        }
                        readInputScannerLiveDraft(args, draft);
                    }catch(Exception e){
                        System.out.println("Invalid input type. Please input an integer.");
                        readInputScannerLiveDraft(args, draft);
                    }
                }
                break;
                case "DRAFT" : {
                    draft.getDraft();
                    readInputScannerLiveDraft(args, draft);
                }
                break;
                case "ALL" : {
                    draft.printAllPicks();
                    readInputScannerLiveDraft(args, draft);
                }
                break;
                case "THEN" : {
                    draft.getPreviousPick();
                    readInputScannerLiveDraft(args, draft);
                }
                break;
                case "NOW" : {
                    readInputScannerLiveDraft(args, draft);
                }
                break;
                case "NEXT" : {
                    draft.getNextPick();
                    readInputScannerLiveDraft(args, draft);
                }
                break;
                case "ROUND" : {
                    System.out.println("(i) Input round:");
                    try{
                        int round = scanner.nextInt();
                        scanner.nextLine();
                        draft.getPicksByRound(round);
                        readInputScannerLiveDraft(args, draft);
                    }catch(Exception e){
                        System.out.println("Invalid input type. Please input an integer.");
                        readInputScannerLiveDraft(args, draft);
                    }
                }
                break;
                case "ONE" : {
                    readInputScannerLiveDraft(args, draft);
                }
                break;
                case "Q" : {
                    readInputScanner(args);
                }
                break;
                default :
                    invalidCommand(input1);
                    readInputScannerLiveDraft(args, draft);
            }
    }



    private static void readInputScannerCustomDraftOrder(String[] args) {
    }

    private static void readInputScannerSearch(String[] args) {
    }

    private static void readInputScannerList(String[] args) {
    }

    private static void readInputScannerNew(String[] args) {
        System.out.println(" ");
        System.out.println("CREATE NEW");
        System.out.println(" ");
        System.out.println("(c) Enter type of Class to create: ");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));

        String input1 = scanner.nextLine();

        switch (input1.toUpperCase()){
            case "C": {
                secondaryCommandsList();
                readInputScannerNew(args);
            }
            break;

            case "L": {
                System.out.println("ADDING NEW LEAGUE");
                System.out.println("(any) Enter league name:");
                String name = scanner.nextLine();
                System.out.println("(any) Enter commissioner's name:");
                String commish = scanner.nextLine();
                System.out.println("(i) How many players each team?");
                try{
                    int noOfPLayers = scanner.nextInt();
                    scanner.nextLine();
                    League newLeague = new League(name, commish, noOfPLayers);
                    System.out.println(" ");
                    System.out.println("New team successfully created!");
                    newLeague.getLeague();
                    readInputScanner(args);
                }catch(Exception e){
                    System.out.println("Invalid input type. Please input an integer.");
                    readInputScannerNew(args);
                };

            }
            break;
            case "T" : {
                System.out.println("ADDING NEW TEAM");
                System.out.println("(i) Enter league id team belongs to:");
                try{
                    int leagueID = scanner.nextInt();
                    scanner.nextLine();
                    League league = League.getLeagueByID(leagueID);
                    if (league == null){
                        readInputScannerNew(args);
                    }else{
                        System.out.println("(any) Team name:");
                        String name = scanner.nextLine();
                        System.out.println("(any) Team owner:");
                        String owner = scanner.nextLine();
                        Team newTeam = league.createNewTeam(name, owner);
                        System.out.println("New team successfully created!");
                        newTeam.getTeam();
                        readInputScanner(args);
                    }
                }catch(Exception e){
                    System.out.println("Invalid input type. Please input an integer.");
                    readInputScannerNew(args);
                }

            }
            break;
            case "P" : {
                System.out.println("ADDING NEW PLAYER");
                System.out.println("(any) Enter player name:");
                String name = scanner.nextLine();
                System.out.println("(any) Enter player position:");
                String position = scanner.nextLine();
                System.out.println("(any) Enter player team code:");
                String team = scanner.nextLine();
                System.out.println("(i) Enter player number:");
                try{
                    int number = scanner.nextInt();
                    scanner.nextLine();
                    Player newPlayer = new Player(name, position, team, number);
                    if(Player.duplicatePlayerCheck(newPlayer) != -1){
                        readInputScanner(args);
                    }else{
                        System.out.println(" ");
                        System.out.println("New player successfully created!");
                        newPlayer.getPlayer();
                        readInputScanner(args);
                    }
                }catch(Exception e){
                    System.out.println("Invalid input type. Please input an integer.");
                    readInputScannerNew(args);
                }
            }
            break;
            case "D" : {
                System.out.println("ADDING NEW DRAFT");
                System.out.println("(i) Enter league id drafting:");
                try{
                    int leagueID = scanner.nextInt();
                    scanner.nextLine();
                    League league = League.getLeagueByID(leagueID);
                    if (league == null){
                        readInputScannerNew(args);
                    }else{
                        System.out.println("(i) Draft year:");
                        try{
                            int year = scanner.nextInt();
                            scanner.nextLine();
                            readInputScannerNewDraft(args, year, league);
                        }catch(Exception e){
                            System.out.println("Invalid input type. Please input an integer.");
                            readInputScannerNew(args);
                        }
                    }
                }catch(Exception e){
                    System.out.println("Invalid input type. Please input an integer.");
                    readInputScannerNew(args);
                }

            }
            break;
            case "Q" : {
                readInputScanner(args);
            }
            break;
            default :
                invalidCommand(input1);
                readInputScannerNew(args);
        }
    }

    private static void readInputScannerNewDraft(String[] args, int year, League league) {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("(c) Draft order: ");
        String input1 = scanner.nextLine();
        switch(input1.toUpperCase()){
            case "C" : {
                System.out.println(" ");
                System.out.println("L - for Linear");
                System.out.println("S - for Linear");
                System.out.println("Q - to quit the process");
                System.out.println(" ");
                readInputScannerNewDraft(args, year, league);
            }
            break;
            case "L" : {
               Draft newDraft = league.createNewDraft(year, "Linear");
                System.out.println("New draft successfully created!");
                newDraft.getDraft();
                readInputScannerDraftOrder(args, newDraft);
            }
            case "S" : {
                Draft newDraft = league.createNewDraft(year, "Snake");
                System.out.println("New draft successfully created!");
                newDraft.getDraft();
                readInputScannerDraftOrder(args, newDraft);
            }
            break;
            case "Q" : {
                readInputScanner(args);
            }
            break;
            default :
                invalidCommand(input1);
                readInputScannerNewDraft(args, year, league);
        }
    }

    private static void readInputScannerDraftOrder(String[] args, Draft draft) {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("SETTING DRAFT ORDER: ");
        int noOfTeams = draft.getTeamsOfDraft();
        for (int i = 0; i < noOfTeams; i ++){
            System.out.println("(i) ID of team with pick " + (i+1) + ":");
            try{
                int settingTeamID = scanner.nextInt();
                // Check ID is valid against team list -----------------------------------------------------------------------
                Team settingTeam = Team.getTeamByID(settingTeamID);
                draft.setDraftOrder(settingTeam, i);
                scanner.nextLine();
            }catch(Exception e){
                System.out.println("Process terminated due to valid input type. Please input an integer.");
                readInputScannerDraftOrder(args, draft);
            }
        }
        draft.fillDraftOrder();
        System.out.println("Draft order set!");
        readInputScanner(args);
    }


    private static void readInputScannerOne(String[] args) {
        System.out.println(" ");
        System.out.println("FIND BY ID");
        System.out.println(" ");
        System.out.println("(i) Enter id to retrieve:");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        try{
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("(C) Enter type of Class to return: ");
            String input1 = scanner.nextLine();

            switch (input1.toUpperCase()){
                case "C": {
                    secondaryCommandsList();
                    readInputScannerOne(args);
                }
                break;

                case "L": {
                    League league = League.getLeagueByID(id);
                    league.getLeague();
                    readInputScanner(args);
                }
                break;
                case "T" : {
                    Team team = Team.getTeamByID(id);
                    team.getTeam();
                    readInputScanner(args);
                }
                break;
                case "P" : {
                    Player player = Player.getPlayerByID(id);
                    player.getPlayer();
                    readInputScanner(args);
                }
                break;
                case "D" : {
                    Draft draft = Draft.getDraftByID(id);
                    draft.getDraft();
                    System.out.println("List picks? (Y/N)");
                    switch(scanner.nextLine()){
                        case "Y" : {
                            draft.printAllPicks();
                        }
                        break;
                        default : {
                            readInputScanner(args);
                        }

                    }
                    readInputScanner(args);
                }
                break;
                case "Q" : {
                    readInputScanner(args);
                }
                break;
                default :
                    invalidCommand(input1);
                    readInputScannerOne(args);
            }
        }catch(Exception e){
            System.out.println("Invalid input type. Please input an integer.");
            readInputScannerOne(args);
        }
    }

    static void mainCommandsList(){
        System.out.println(" ");
        System.out.println("NEW - To create a new league, Team, Player or Draft");
//        System.out.println("LIST - To list all existing leagues, Teams, Players or Drafts");
        System.out.println("ONE - To return details of a known league, Team, Player or Draft by ID");
//        System.out.println("SEARCH - To find a specific league, Team, Player or Draft by name");
        System.out.println("DRAFT - To start or continue a draft");
        System.out.println("Q - To exit the program");
        System.out.println(" ");
    }
    static void secondaryCommandsList(){
        System.out.println(" ");
        System.out.println("L - for League");
        System.out.println("T - for Team");
        System.out.println("P - for Player");
        System.out.println("D - for Draft");
        System.out.println("Q - To quit the process");
        System.out.println(" ");
    }

    static void draftCommandsList(){
        System.out.println(" ");
        System.out.println("E - to start or resume a draft");
        System.out.println("O - to create a custom draft order");
        System.out.println("Q - To quit the process");
        System.out.println(" ");
    }

    private static void liveDraftCommandsList() {

        System.out.println(" ");
        System.out.println("PICK - to pick a player by ID");
        System.out.println("NEW - to pick a player that doesn't already exist on the app");
        System.out.println("DRAFT - to see the draft details");
        System.out.println("ALL - to see all previous picks up to the current");
        System.out.println("THEN - to see the previous pick");
        System.out.println("NOW - to see the current pick");
        System.out.println("NEXT - to see the next pick");
        System.out.println("ROUND - to see all picks from a specified round");
        System.out.println("ONE - to see a pick by ID");
        System.out.println("Q - To quit the process");
        System.out.println(" ");
    }

    static void invalidCommand(String input){
        System.out.println(" ");
        System.out.println("Sorry, " + input + " is not a valid command!");
        System.out.println(" ");
    }

    static void positionCommandList(){
        System.out.println(" ");
        System.out.println("QB - to pick a player by ID");
        System.out.println("RB - to pick a player that doesn't already exist on the app");
        System.out.println("WR - to see the draft details");
        System.out.println("TE - to see all previous picks up to the current");
        System.out.println("K - to see the previous pick");
        System.out.println("DEF - to see the current pick");
        System.out.println(" ");
    }

    static void seed(){

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("SEEDING DATA");

        //Add a league
        League league1 = new League("Bristol Bowl", "Jack Whale", 3);

        //Add some teams to that league
        Team team1 = league1.createNewTeam("Irritable Bowl Syndrome", "Jack Whale");
        Team team2 = league1.createNewTeam("Joe Buck Yourself", "Harris Todd");

        //Create a draft for that league
        Draft newDraft = league1.createNewDraft(2022, "Linear");

        //Add some players
        Player player1 = new Player("Jonathan Taylor", "RB", "IND", 28);
        Player player2 = new Player("Patrick Mahomes", "QB", "KC", 15);
        Player player3 = new Player("Michael Carter", "RB", "NYJ", 32);
        Player player4 = new Player("Davante Adams", "WR", "LV", 17);
        Player player5 = new Player("Deebo Samuel", "WR", "SF", 19);
        Player player6 = new Player("Mark Andrews", "TE", "BAL", 89);

//        Player.getTotalPlayers();
//
        newDraft.setDraftOrder(team1,0);
        newDraft.setDraftOrder(team2,1);
        newDraft.fillDraftOrder();

        newDraft.pickPlayer(0);
        newDraft.pickPlayer(1);
        newDraft.pickPlayer(2);
        newDraft.pickPlayer(3);
//        newDraft.pickPlayer(4);

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("SEEDED:");
        System.out.println("ONE league (ID: 0)");
        System.out.println("TWO teams (ID: 0-1)");
        System.out.println("SIX PLayers teams (ID: 0-5)");
        System.out.println("TWO teams (ID: 0-1)");
        System.out.println("ONE draft (in progress) (ID: 0)");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

    }





    //Unimplemented Featured --------------------------------------------------------------------

    // Validation on player positions
    // Validation when picking draft orders from list of teams

    //Validation on draft year
    // Keepers
    // Saving files
    //Revert to a pick

    //Get team form a year - default to current draft




}