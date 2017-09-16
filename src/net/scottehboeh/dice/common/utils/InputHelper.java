package net.scottehboeh.dice.common.utils;

/**
 * Created by 1503257 on 13/09/2017.
 */
public class InputHelper {

    /**
     * Checks if the given Username is Valid (Short enough, long enough, etc)
     * @param givenUsername - The given String (Username)
     * @return - true/false
     */
    public static boolean isNameValid(String givenUsername){

        /** If username is null, too short or too long: */
        if(givenUsername == null || givenUsername.length() < 3 || givenUsername.length() > 16){
            return false;
        }

        return true;

    }

}
