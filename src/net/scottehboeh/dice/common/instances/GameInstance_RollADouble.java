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
public class GameInstance_RollADouble extends GameInstance {

    private List<EntityDice> gameDice; /** Game Dice(s) */
    private int diceCount = 0; /** Dice Count - used for dice ID's */
    private List<EntityPlayer> playerList; /** Player List - stores current players */
    private Scanner inScanner; /** Scanner Instance */
    private boolean shouldQuitGame; /** Win Status */
    private int currentPlayerTurn = 0; /** Who's Turn */
    private int playerCount = 2; /** The Player Count for the Game (Can be Changed!) */
    private int gameRounds = 5; /** How many rounds should play out each game */
    
    /**
     * Default Constructor for Game Instance
     */
    public GameInstance_RollADouble(GameSettings givenSettings){
    	super(givenSettings);

    	/** Set game Name and Description */
    	setGameName("Roll a Double");
    	setGameName("Try to roll a double before the other players to increate your Score");
    	
        /** Set Winning Status to False (Hasn't Won) */
        this.shouldQuitGame = false;

        /** Initialize the Game Dice List */
        this.gameDice = new ArrayList<EntityDice>();

            EntityDice dice1 = new EntityDice(1, nextDiceID());
            EntityDice dice2 = new EntityDice(1, nextDiceID());

            /** Add Dice to game instance Dice-list */
            this.gameDice.add(dice1);
            this.gameDice.add(dice2);

        /** Initialize Variables for Game Instance */
        this.inScanner = new Scanner(System.in); /** Scanner Instance */
        
        /** Initialize the Player List */
        this.playerList = new ArrayList<EntityPlayer>();

        /** Add players to player list (and set score accordingly) */
        for(int i = 0; i < this.playerCount; i++){
        	this.playerList.add(new EntityPlayer(0));
        }

    }

    /**
     * Initialize the Game Instance
     */
    @Override
    public void init(){
    	
        /** Boolean used to determine whether the current game instance should finish or not */
        boolean shouldQuit = false;

        /** Inform the user that the game instance has started */
        System.out.println("Initializing Game Instance");

        /** Prompt the user to input a Username */
        System.out.println("Please enter your Username:"
        		+ "\n (To add Score Handicap, view Readme File)");

        for(int i = 0; i < this.playerList.size(); i++){
        
            /** String Placeholder for the username given from the player */
            String givenUsername = "";
        	
        	/** Ask the user for a Username Input. */
        	while(!InputHelper.isNameValid(givenUsername)){

        		/** Inform the user on instructions on how to add Username */
        		System.out.println("Please enter player " + (i + 1) + " Username:"
        				+ "\n - Between 3-16 Characters"
        				 + "\n - To add a Handicap, please read the readme file!");
        		givenUsername = this.inScanner.nextLine();

        	}
        	
    		/** If the given username contains '-', add the follwing score to the players score */
    		if(givenUsername.contains("-")){
    			
    			/** Split the given input to reveal score handicap */
    			String[] splitUsername = givenUsername.split("-");
    			
    			/** Parse the given split to an integer (handicap value) */
    			int handicapValue = Integer.parseInt(splitUsername[1]);
    			
    			/** Inform the user that a handicap has been initialized */
    			System.out.println("Handicap Activated: Adding " + handicapValue + " Score Handicap to Player " + (i + 1) + "!");
    			
    			/** Add handicap to the player score */
    			this.playerList.get(i).addToScore(handicapValue);
    			
            	/** Set the Player Username as the given valid Split String */
            	this.playerList.get(i).setUsername(givenUsername.split("-")[0]);
    			
    		} else {
            	/** Set the Player Username as the given valid String */
            	this.playerList.get(i).setUsername(givenUsername);
    		}

        	/** Inform the user of their chosen Username */
        	System.out.println("Player " + (i + 1) + " has chosen the Username " + this.playerList.get(i).getUsername() + "!");
        
        }

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

            System.out.println("Player " + (this.currentPlayerTurn + 1) + "'s turn! Their Score: " + this.playerList.get(this.currentPlayerTurn).getScore() + "!");
            System.out.println("=---------------------------=");
            System.out.println("=-= + Option '1' - Roll Dice");
            System.out.println("=-= + Option '2' - Quit Game");
            System.out.println("=---------------------------=");

            while(!this.inScanner.hasNextInt());
            
            /** Declare given integer input to an integer variable */
            int givenInput = this.inScanner.nextInt();

            /** Select menu option depending on given int */
            switch(givenInput){

                /** 1 - Roll all Dice */
                case(1):
                    System.out.println("Rolling the Dice...");
                    rollAllGameDice();
                    
                    /** Check if the Dice are equal (rolled a double) */
                    checkIfWon();
                    
                    break;

                /** 2 - Exit current Game */
                case(2):
                    this.shouldQuitGame = true;
                    break;

            }
            
        }

        /** The Winning Player Object */
        EntityPlayer theWinningPlayer = null;
        
        /** Integer used to store the highest current winning score in loop */
        int winningPlayerScore = 0;
        
        /** For each player in the player list */
        for(int i = 0; i < this.playerList.size(); i++){
        	/** If the current player has a higher score than the known winning score */
        	if(this.playerList.get(i).getScore() > winningPlayerScore){
        		/** Set the new winning player */
        		theWinningPlayer = this.playerList.get(i);
        		winningPlayerScore = theWinningPlayer.getScore();
        	}
        }
        
        /** If there is a winning player, announce it to the user */
        if(theWinningPlayer != null){
        System.out.println("The winning player is " + theWinningPlayer.getUsername() + " with " + theWinningPlayer.getScore() + " score!");
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

    	/** If the game dice is not equal to 0 (or null) */
        if(this.gameDice.get(givenIndexNumber) != null) {
            return this.gameDice.get(givenIndexNumber);
        } else {
        	/** Inform the user that there have been no dice asigned to the game */
            System.out.println("No Dice Found! What a Shame!");
            return null;
        }

    }
    
    /**
     * Check If Won - A small method used to check if the player dice are equal to each other'
     * If they do equal each other, end the match and tell the player that they have won
     */
    private void checkIfWon(){
    	
    	/** If both dices are the same value, tell the user */
    	if(this.gameDice.get(0).getDiceFaceValue() == this.gameDice.get(1).getDiceFaceValue()){
    		
    		/** Inform the user that the two dice are the same value */
    		System.out.println("Both dice have the Same value! Player " + (this.currentPlayerTurn + 1) + " has Won!");
    		
    		/** If there are still rounds left */
    		if(this.gameRounds > 0){
    			
    			/** Notify the users that a new round is starting */
    			System.out.println("Rounds Left: " + this.gameRounds + "!");
    			
    			/** Remove 1 Game Round */
    			this.gameRounds--;
    			/** Assure that the game does not quit */
    			this.shouldQuitGame = false;
    			
    			/** Add 1 point to Player 2's Score */
    			this.playerList.get(this.currentPlayerTurn).addToScore(1);
    			
    			/** Reset turns to 0 (new round) */
    			this.currentPlayerTurn = 0;
    			
    			/** Else, assure that the game ends */
    		} else {
    			this.shouldQuitGame = true;
    		}
    		
    		/** Else, next players turn */
    	} else {
    		nextTurn();
    	}
    	
    }
    
    /**
     * Next Turn - Rotates the Game so that everyone gets a turn (Repeats)
     */
    private void nextTurn(){
    	
    	this.currentPlayerTurn++;
    	
    	//System.out.println(this.playerChoice);
    	//System.out.println(this.playerList.size());
    	
    	if(this.currentPlayerTurn > (this.playerList.size() - 1)){
    		this.currentPlayerTurn = 0;
    	}
    	
    }

}
