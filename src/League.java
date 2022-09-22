import java.util.ArrayList;

public class League {

    static private int totalLeagues;
    private String name;
    private String commish;
    private int numberOfDrafts;
    private int id;
    private int noOfPlayers;

    public Draft latestDraft;

    private static ArrayList<League> allLeagues = new ArrayList<League>();
    public ArrayList<Team> teamsArr = new ArrayList<Team>();
    private ArrayList<Draft> draftsArr = new ArrayList<Draft>();

    // Constructor to initialize fields
    League(String name, String commish, int noOfPlayers){
        this.name = name;
        this.commish = commish;
        totalLeagues ++;
        numberOfDrafts = 0;
        this.id = totalLeagues- 1;
        this.noOfPlayers = noOfPlayers;
        allLeagues.add(this);
    }

    // GET METHODS ------------------------------------------------------------------------

    //Get league details by id
    public static League getLeagueByID(int id){
        if(id > allLeagues.size() - 1 || id < 0){
            System.out.println(" ");
            System.out.println("There is no league with id " +  id);
            if ((allLeagues.size() - 1) == -1){
                System.out.println("There are no leagues registered with this app!");
                return null;
            }else{
                System.out.println("Valid ids are between 0 and " +  (allLeagues.size() - 1));
                System.out.println(" ");
                return null;
            }

        }else{
            League league = allLeagues.get(id);
            return league;
        }

    }

    //Get latest Draft
    public Draft setLatestDraft(){
        if (draftsArr.size() >= 0){
            int latestYear = 0;
            Draft latestDraft = null;
            for (int i = draftsArr.size() - 1; i >= 0; i --){

                if (draftsArr.get(i).year > latestYear ){
                    latestYear = draftsArr.get(i).year;
                    latestDraft = draftsArr.get(i);
                }
            }
            return latestDraft;
        }else{
            System.out.println("This league has yet to hold any drafts!");
            return null;
        }
    }

    //Get total number of leagues
    public static void getTotalLeagues(){
        System.out.println("Total Leagues: " + totalLeagues);
    }

    // GET METHODS from variables --------------------------------------------------------

    //Get league details
    public void getLeague(){
        System.out.println("------------------------");
        System.out.println(name);
        System.out.println("Commish: " + commish);
        if ((teamsArr.size() - 1) == -1){
            System.out.println("Teams: There are no teams registered with this league!");
        }else{
            System.out.println("Teams: " + teamsArr.size());
        }
        if ((draftsArr.size() - 1) == -1){
            System.out.println("Drafts: This league has not held any drafts yet!");
        }else{
            System.out.println("Drafts: " + draftsArr.size());
        }
        System.out.println("ID: " + id);
        System.out.println(" ");
    }

    //Get league ID
    public int getLeagueID(League league){
        return league.id;
    }

    //Get league IName
    public String getLeagueName(League league){
        return league.name;
    }

    //Get list of teams
    public int getTeamsOfLeague(){
        System.out.println(" ");
        System.out.println("List of teams:");
        System.out.println(" ");
        for(int i = 0; i < teamsArr.size(); i ++){
            Team team = teamsArr.get(i);
            System.out.println("ID: "+ team.getTeamID() + " - " + team.getTeamName());
        }
        System.out.println(" ");
        return teamsArr.size();
    }

    public ArrayList getTeamsArr(){
        return getTeamsArr();
    }

    // ADD METHODS ------------------------------------------------------------------------

    // Create Team
    public Team createNewTeam(String name, String owner){
        Team newTeam = new Team(id, name, owner);
        teamsArr.add(newTeam);
        return newTeam;
    }

    // Create Draft
    public Draft createNewDraft(int year, String draftOrder){
        Draft newDraft = new Draft(id,teamsArr.size(),noOfPlayers,year, draftOrder);
        draftsArr.add(newDraft);
        return newDraft;
    }

}
