package net.scottehboeh.dice.common.utils;

/**
 * Created by 1503257 on 13/09/2017.
 */
public class GameSettings {

    private boolean debugMode; /** Debug Game */
    private int diceCount = 1; /** Dice Count */

    /**
     * Default Constructor for Game Settings Instance
     */
    public GameSettings(){

        setDebug(false); /** Set Debug Mode */
        setDiceCount(1); /** Set Dice Count */

    }

    /**
     * Set Debug - Set the Game Instance to debug-mode
     * @param givenBool - Given True/False
     */
    public void setDebug(Boolean givenBool){
        this.debugMode = givenBool;
    }

    /**
     * Set Dice Count - Set the current Dice Count
     * @param givenCount - Given Count (int)
     */
    public void setDiceCount(int givenCount){
        this.diceCount = givenCount;
    }

    /**
     * Get Dice Count - Returns the amount of Dice in Game
     * @return
     */
    public int getDiceCount(){

        /** returns dice count */
        return this.diceCount;

    }

}
