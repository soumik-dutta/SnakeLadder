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
    private Twists twists;

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

    /**
     * Check if a snake is configured in the current positions
     *
     * @param currentPostion
     * @return
     */
    public Integer finalPosition(Integer currentPostion) {
        Integer position = currentPostion;
        if (twists.getMoves().containsKey(currentPostion)) {
            position = twists.getMoves().get(currentPostion);
            System.out.print(" OOPS! SNAKE BITE, RETURN TO " + position);
        }
        return position;
    }
}
