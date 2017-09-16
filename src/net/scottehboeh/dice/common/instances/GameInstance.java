package net.scottehboeh.dice.common.instances;

import net.scottehboeh.dice.common.utils.GameSettings;

public class GameInstance {

	private String gameName;
	private String gameDescription;
	private GameSettings gameInstanceSettings; /** Game Settings Instance for Game Instance */
	
	/**
	 * Initial Game Instance constructor
	 * @param givenSettings
	 */
	public GameInstance(GameSettings givenSettings){
		/** Cast given settings to current game settings instance */
		this.gameInstanceSettings = givenSettings;
		
		System.out.println("Initializing Game: " + this.gameName);
	}
	
	/** 
	 * Init - Initialize the Game Method
	 */
	public void init(){
		
	}

	/**
	 * Get the Game Settings for current Game Instance
	 * @return - Returns Game Instance Settings Instance
	 */
	private GameSettings getGameSettings(){
		return this.gameInstanceSettings;
	}
	
	/**
	 * Set the Game Name
	 * @param givenGameName - Given Game Name (String)
	 */
	public void setGameName(String givenGameName){
		this.gameName = givenGameName;
	}
	
	/**
	 * Set the Game Description
	 * @param givenGameDescription - Given Game Description (String)
	 */
	public void setGameDescription(String givenGameDescription){
		this.gameDescription = givenGameDescription;
	}
	
}
