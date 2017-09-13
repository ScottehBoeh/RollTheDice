package net.scottehboeh.dice;

import net.scottehboeh.dice.common.instances.GameInstance;
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

    /**
     * Main Class - Used to initialize everything for the Game
     * @param args - Launch Arguments
     */
    public static void main(String[] args){

        /** Create new Game Settings Instance for Game Session */
        gameVariables = new GameSettings();

        /** Initialize the Session Game Instance */
        gameInstance = new GameInstance(gameVariables);

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

}
