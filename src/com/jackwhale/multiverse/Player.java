package com.jackwhale.multiverse;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    static private int totalPlayers;
    private static ArrayList<Player> allPlayers = new ArrayList<Player>();
    private int id;
    public String name;
    public String position;
    public String team;
    public int number;


    // Constructor to initialize fields
    Player(String name, String position, String realTeam, int number){
        this.name = name;
        this.position = position;
        this.team = realTeam;
        this.number = number;
        totalPlayers ++;
        this.id = totalPlayers - 1;
        allPlayers.add(this);
    }

    // GET METHODS ------------------------------------------------------------------------

    //Get player details by id
    public static Player getPlayerByID(int id){
        if(id > allPlayers.size() - 1 || id < 0){
            System.out.println("There is no player with id " +  id);
            if ((allPlayers.size() - 1) == -1){
                System.out.println("There are no players registered with this app!");
                return null;
            }else{
                System.out.println("Valid ids are between 0 and " +  (allPlayers.size() - 1));
                System.out.println(" ");
                return null;
            }
        }else {
            Player player = allPlayers.get(id);
            return player;
        }
    }

    //Get total number of players
    public static void getTotalPlayers(){
        System.out.println("Total PLayers = " + totalPlayers);
    }

    //Search for duplicate player
    public static int duplicatePlayerCheck(Player newPlayer){
        for (int i = 0; i < allPlayers.size() - 1; i++){
            if (allPlayers.get(i).equals(newPlayer)){
                System.out.println("This player already exists:");
                System.out.println(" ");
                allPlayers.get(i).getPlayer();
                allPlayers.remove(allPlayers.size() - 1);
                totalPlayers --;
                return allPlayers.get(i).id;
            }
        }
        return -1;
    }

    public static void listAll() {
        for (int i = 0; i < allPlayers.size(); i++){
            System.out.println("-ID:   " + allPlayers.get(i).id + " ----- " + allPlayers.get(i).position + " " + allPlayers.get(i).team + " #" + allPlayers.get(i).number + " " + allPlayers.get(i).name);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return getNumber() == player.getNumber() && getName().equals(player.getName()) && getPosition().equals(player.getPosition()) && getTeam().equals(player.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPosition(), getTeam(), getNumber());
    }

    public String getName() { return name; }
    public String getPosition() { return position; }
    public String getTeam() { return team; }
    public int getNumber() { return number; }
    public int getID() { return id; }

    //Search for player by name

    // GET METHODS from variables --------------------------------------------------------

    //Get player details
    public void getPlayer(){
        System.out.println("------------------------");
        System.out.println(name);
        System.out.println(position + " #" + number + " " + team);
        System.out.println("id: " + id);
        System.out.println(" ");
    }

    //Get player details


}
