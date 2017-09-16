package net.scottehboeh.dice;

import net.scottehboeh.dice.common.instances.GameInstance;
import net.scottehboeh.dice.common.instances.GameInstance_RollADouble;
import net.scottehboeh.dice.common.instances.GameInstance_RollASix;
import net.scottehboeh.dice.common.utils.GameSettings;

/**
 * Created by 1503257 on 13/09/2017.
 */

/**
 * Dice Game - a Simple Roll-the-dice Game
 */
public class DiceGame {

    private static GameSettings gameVariables; /** Game Settings */
    private static GameInstance gameInstance; /** Game Instance */
    private static int gameIndex = 0;

    /**
     * Main Class - Used to initialize everything for the Game
     * @param args - Launch Arguments
     */
    public static void main(String[] args){

    	/** Initialize the Game Arguments */
    	initializeArgs(args);
    	
        /** Create new Game Settings Instance for Game Session */
        gameVariables = new GameSettings();
        
        switch(gameIndex){
        
        /** Default Case */
        case(0):
            /** Initialize the Session Game Instance 1 */
            gameInstance = new GameInstance_RollASix(gameVariables);
        	break;
        
        /** Case 1 - Game 1 */
        case(1):
            /** Initialize the Session Game Instance 1 */
            gameInstance = new GameInstance_RollASix(gameVariables);
        	break;
        
        /** Case 2 0 Game 2 */
        case(2):
            /** Initialize the Session Game Instance 2 */
            gameInstance = new GameInstance_RollADouble(gameVariables);
        	break;
        
        }

        /** Initialize the Game Instance */
        gameInstance.init();

    }

    /**
     * Game Settings - Used to get Game Settings
     * @return - Returns the current Game Session Settings instance/object
     */
    public GameSettings getSettings(){
        return this.gameVariables;
    }
    
    /**
     * Initialize Arguments - Initializes the given Arguments
     * @param givenArguments
     */
    private static void initializeArgs(String[] givenArguments){
    	
    	for(int i = 0; i < givenArguments.length; i++){
    		
    		/** If the Arguments begin with '--game', fetch game index */
    		if(givenArguments[i].contains("--game")){
    			
    			/** Get game index as number (integer) */
    			int gameNumber = Integer.parseInt(givenArguments[i + 1]);
    			System.out.println("Game number is " + gameNumber);
    			gameIndex = gameNumber;
    			
    		}
    		
    	}
    	
    }

}
