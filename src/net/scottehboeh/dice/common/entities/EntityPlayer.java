package net.scottehboeh.dice.common.entities;

/**
 * Created by 1503257 on 13/09/2017.
 */
public class EntityPlayer {

    private String playerUsername; /** Player Username (String) */
    private int playerID; /** Player Unique Identifier (int) */
    private int playerScore; /** Player Score (int) */

    /**
     * Default Constructor for Player Entity
     */
    public EntityPlayer(){
        setPlayerScore(5); /** Set Score as 5 (default starting score) */
    }

    /**
     * Constructor for Player Entity with extra Attributes
     */
    public EntityPlayer(int givenScore){
        setPlayerScore(givenScore); /** Set Score as given score */
    }
    
    /**
     * Get Username - Returns the players Username
     * @return - Username (String)
     */
    public String getUsername(){
        return this.playerUsername;
    }

    /**
     * Get Player Score - Gets the players score
     * @return - The Players Score (int)
     */
    public int getScore(){
        return this.playerScore;
    }

    /**
     * Set Username - Method used to set the players Username
     * @param givenName - Given Username
     */
    public void setUsername(String givenName){
        this.playerUsername = givenName;
    }

    /**
     * Set Player Score - Set a new score value for player
     * @param givenScore - Given Score Value (int)
     */
    public void setPlayerScore(int givenScore){
        this.playerScore = givenScore;
    }
    
    /**
     * Add to Score - Add a value to the players current Score
     * @param givenScore - The Given Value to Add (int)
     */
    public void addToScore(int givenScore){
    	this.playerScore += givenScore;
    }

}
