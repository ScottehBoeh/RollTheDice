package net.scottehboeh.dice.common.instances;

/**
 * Created by 1503257 on 13/09/2017.
 */

import net.scottehboeh.dice.common.entities.EntityDice;
import net.scottehboeh.dice.common.entities.EntityPlayer;
import net.scottehboeh.dice.common.utils.GameSettings;
import net.scottehboeh.dice.common.utils.InputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Game Instance
 * a Single Instance of the Game
 */
public class GameInstance_RollASix extends GameInstance{

    private List<EntityDice> gameDice; /** Game Dice(s) */
    private int diceCount = 0; /** Dice Count - used for dice ID's */
    private EntityPlayer thePlayer; /** Player Entity/Instance */
    private Scanner inScanner; /** Scanner Instance */
    private boolean shouldQuitGame; /** Win Status */

    /**
     * Default Constructor for Game Instance
     */
    public GameInstance_RollASix(GameSettings givenSettings){
    	super(givenSettings);

    	/** Set game Name and Description */
    	setGameName("Roll a Six");
    	setGameName("Roll a Six on your Dice before your Score reaches 0");
    	
        /** Set Winning Status to False (Hasn't Won) */
        this.shouldQuitGame = false;

        /** Initialize the Game Dice List */
        this.gameDice = new ArrayList<EntityDice>();

            EntityDice dice1 = new EntityDice(1, nextDiceID());
            //EntityDice dice2 = new EntityDice(1, nextDiceID());

            /** Add Dice to game instance Dice-list */
            this.gameDice.add(dice1);
            //gameDice.add(dice2);

        /** Initialize Variables for Game Instance */
        this.inScanner = new Scanner(System.in); /** Scanner Instance */
        this.thePlayer = new EntityPlayer(); /** Player Instance */

    }

    /**
     * Initialize the Game Instance
     */
    @Override
    public void init(){

        /** String Placeholder for the username given from the player */
        String givenUsername = "";

        /** Boolean used to determine whether the current game instance should finish or not */
        boolean shouldQuit = false;

        /** Inform the user that the game instance has started */
        System.out.println("Initializing Game Instance");

        /** Prompt the user to input a Username */
        System.out.println("Please enter your Username:");

        /** Ask the user for a Username Input. */
        while(!InputHelper.isNameValid(givenUsername)){

            System.out.println("Please enter your Username:"
             + "\n - Between 3-16 Characters");
            givenUsername = this.inScanner.nextLine();

        }

        /** Ser the Player Username as the given valid String */
        this.thePlayer.setUsername(givenUsername);

        /** Inform the user of their chosen Username */
        System.out.println("The player username is " + this.thePlayer.getUsername() + "! Let's Begin!");

        /** Continue until the player choses to quit */
        while(!this.shouldQuitGame){

            /** Prompt user to give an input Value */
            System.out.println("Pick an option:");

            System.out.println("=---------------------------=");

            if(gameDice.size() > 0) {
                /** For each Dice, display its vale */
                for (int i = 0; i < gameDice.size(); i++) {
                    System.out.println("Dice " + gameDice.get(i).getDiceID() + " is currently at " + getSingleGameDice(i).getDiceFaceValue());
                }
            } else {
                /** Inform the user that there are no dice in the game */
                System.out.println("This game doesn't have any Dice! That's odd...");
            }

            System.out.println("The player score is currently " + this.thePlayer.getScore() + "!");
            System.out.println("=---------------------------=");
            System.out.println("=-= + Option '1' - Roll Dice");
            System.out.println("=-= + Option '2' - Quit Game");
            System.out.println("=---------------------------=");

            /** Declare given integer input to an integer variable */
            int givenInput = this.inScanner.nextInt();

            /** Select menu option depending on given int */
            switch(givenInput){

                /** 1 - Roll all Dice */
                case(1):
                    System.out.println("Rolling the Dice...");
                    rollAllGameDice();
                    break;

                /** 2 - Exit current Game */
                case(2):
                    this.shouldQuitGame = true;
                    break;

            }

        }

        /** Give the player a warm thankyou */
        System.out.println("Thanks for playing!");

        /** Close Scanner Stream */
        inScanner.close();

    }

    /**
     * Roll Dice - Roll each Dice in current Game Session
     */
    private void rollAllGameDice(){

        /** For each Dice in Dice List in current Game Instance */
        for(int i = 0; i < this.gameDice.size(); i++){

            /** Roll the Singular Dice */
            this.gameDice.get(i).rollDice();

            /** Inform the user of which value the dice has rolled */
            System.out.println("Dice " + this.gameDice.get(i).getDiceID() + " has rolled " + this.gameDice.get(i).getDiceFaceValue() + "!");

                if (gameDice.get(i).getDiceFaceValue() != 6) {
                    System.out.println("The player didn't roll a 6! -1 Player Score");

                } else {
                    System.out.println("The player rolled a 6! Well done! Total score was: " + this.thePlayer.getScore());
                    this.shouldQuitGame = true;
                    return;
                }

            /** Add 1 to dice roll count */
            diceCount++;

        }

        /** Remove 1 value from Player Score */
        this.thePlayer.setPlayerScore(this.thePlayer.getScore() - 1);

        /** Check if player score is less than or equal to 0 */
        if(this.thePlayer.getScore() <= 0) {
            System.out.println("The player score has reached 0, he has lost!");
            this.shouldQuitGame = true;
            return;
        }

    }

    /**
     * Returns next unique ID for Dice
     * @return - Dice ID
     */
    private int nextDiceID(){

        diceCount++;
        return diceCount;

    }

    /**
     * Get a Specific Game Dice based on Game Dice List index
     * @param givenIndexNumber - Given Dice List Index
     * @return - Dice Entity
     */
    private EntityDice getSingleGameDice(int givenIndexNumber){

        if(this.gameDice.get(givenIndexNumber) != null) {
            return this.gameDice.get(givenIndexNumber);
        } else {
            System.out.println("No Dice Found! What a Shame!");
            return null;
        }

    }

}
