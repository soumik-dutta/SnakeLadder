package service;

import java.util.Random;

/**
 * Creating a singleton object for dice as this cannot change during the game
 * it will be initialized during the game startup
 */
public class DiceService {
    private static DiceService diceService = null;
    private static final String NORMAL = "NORMAL";
    private static final String CROOCKED = "CROOCKED";
    private static String TYPE = NORMAL;
    private static Random rand = new Random();

    public int roll() {
        int randNumber = rand.nextInt(6) + 1;
        if (TYPE.equals(CROOCKED) && randNumber % 2 !=0) {
            randNumber = roll();
        }
        return randNumber;
    }

    private DiceService(int number) {
        if (number % 2 == 0) {
            TYPE = CROOCKED;
            System.out.println("SETTING DICE AS " + TYPE);
        }
    }

    public static DiceService getDiceService() {
        if (diceService == null) {
            // setting DICE strategy to NORMAL by default
            diceService = new DiceService(1);
        }
        return diceService;
    }

    public static void setDiceService(Integer number) {
        if (diceService == null && number != null) {
            diceService = new DiceService(number);
        }
    }

}