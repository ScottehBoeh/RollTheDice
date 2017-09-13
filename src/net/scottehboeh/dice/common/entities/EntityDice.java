package net.scottehboeh.dice.common.entities;

import net.scottehboeh.dice.common.utils.MathHelper;

/**
 * Created by 1503257 on 13/09/2017.
 */
public class EntityDice {

    private int diceID; /** Identifier for Dice (In-case of multiple dice) */
    private int diceValue; /** Roll-Value of the Dice */

    /**
     * Default Dice Constructor
     */
    public EntityDice(){

        setDiceValue(0);

    }

    /**
     * Pre-defined Value Dice Constructor
     * @param givenStartValue - Given Start Value for Dice
     */
    public EntityDice(int givenStartValue, int givenDiceID){

        setDiceValue(givenStartValue);
        setDiceID(givenDiceID);

    }

    /**
     * Set the value of the current dice
     * @param givenValue - Given value to set
     */
    public void setDiceValue(int givenValue){
        this.diceValue = givenValue;
    }

    /**
     * Set the ID of the current dice
     * @param givenID - Given ID to set
     */
    public void setDiceID(int givenID){
        this.diceID = givenID;
    }

    /**
     * Roll Dice - Rolls the Dice to a random number between 1-6
     */
    public void rollDice(){

        /** Set the Dice Value to the given random integer */
        setDiceValue(MathHelper.generateRandomInt(1,6));

    }

    /**
     * Get Dice Face Value - Get the face value of the Dice
     * @return - Face Value of Dice (Integer)
     */
    public int getDiceFaceValue(){
        return this.diceValue;
    }

    /**
     * Get Dice ID - Get the Unique Identifier for the Dice
     * @return - Value of Dice ID (Integer)
     */
    public int getDiceID(){
        return this.diceID;
    }

}
