package net.scottehboeh.dice.common.utils;

/**
 * Created by 1503257 on 13/09/2017.
 */
public class MathHelper {

    /**
     * Generate Random Integer - Generates a random value between the two given values (given min, given max)
     * @param givenMin - Given Minimum Value
     * @param givenMax - Given Maximum Value
     * @return - Random Value (Integer)
     */
    public static int generateRandomInt(int givenMin, int givenMax){

        /** Returns random number using the given minimum and maximum */
        return (givenMin + (int)(Math.random() * ((givenMax - givenMin) + 1)));

    }

}
