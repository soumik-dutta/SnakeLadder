package service;

import domain.Twists;

import java.util.Map;

/**
 * Creating a singleton object as the rules of the games like in this case
 * Snake should only be declared once during the start of the game and not
 * during the game;
 */
public class SnakeService {
    private static SnakeService snakeService = null;
    public Twists twists;

    private SnakeService(Twists twists) {
        this.twists = twists;
    }

    public static SnakeService getSnakeService() {
        return snakeService;
    }

    public static void setSnakeService(Map<Integer, Integer> moves) {
        if (snakeService == null && moves != null) {
            snakeService = new SnakeService(new Twists(moves));
        }
    }
}
