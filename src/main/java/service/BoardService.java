package service;

import domain.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class BoardService {
    private static final int TURN_LIMIT = 10;
    // player queue
    private Queue<Player> players;
    // board size
    private int boardSize;
    private Map<Player, Integer> playerCurrentPosition;

    public Queue<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Queue<Player> players) {
        this.players = players;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public Map<Player, Integer> getPlayerCurrentPosition() {
        return playerCurrentPosition;
    }

    public void setPlayerCurrentPosition(Map<Player, Integer> playerCurrentPosition) {
        this.playerCurrentPosition = playerCurrentPosition;
    }

    public BoardService(Queue<Player> players, int boardSize) {
        this.players = players;
        this.boardSize = boardSize;
        this.playerCurrentPosition = new HashMap<>();
    }

    /**
     * Starting the game
     */
    public void start() {
        System.out.println("Starting Snakes and Ladder");
        while (!getPlayers().isEmpty()) {
            // get and remove the player from the queue
            Player player = getPlayers().poll();
            if (Objects.requireNonNull(player).getTurn() == TURN_LIMIT)
                continue;
            // play his turn
            play(player);
        }
    }

    /**
     * 1. Dice roll
     * 2. determining the player position in conjunction with the dice output
     * 3. check when player is in the final move matches the exact steps
     * needed to reach the destination
     * 4. Increment the players turn
     * 5. Add the player back in the queue if needed
     *
     * @param player
     */
    private void play(Player player) {
        // role dice
        int dice = DiceService.getDiceService().roll();
        System.out.print(player.getName() + " rolled : " + dice);
        var playerPosition = dice;
        if (getPlayerCurrentPosition().containsKey(player)) {
            // increment the position according to the dice output
            playerPosition = getPlayerCurrentPosition().get(player) + dice;
        }
        // check for twists
        playerPosition = checkAndReturnTwists(playerPosition);
        // check for valid final move
        if (!finalMoveValid(playerPosition)) {
            System.out.println(" move not valid try again ... ");
            getPlayers().offer(player);
            return;
        }
        System.out.println(" moved to  : " + playerPosition);
        getPlayerCurrentPosition().put(player, playerPosition);
        // increment the turn
        player.setTurn(player.getTurn() + 1);
        // add the player back into queue if he/she has not reached to the board size
        if (!isFinished(playerPosition)) {
            getPlayers().offer(player);
        }
    }

    private boolean isFinished(int position) {
        return position == getBoardSize();
    }

    /**
     * Check whether the player has crossed the board size
     *
     * @param position
     * @return
     */
    private boolean finalMoveValid(int position) {
        return position <= getBoardSize();
    }

    private Integer checkAndReturnTwists(Integer currentPosition){
        if (SnakeService.getSnakeService()!=null) {
            return SnakeService.getSnakeService().finalPosition(currentPosition);
        }
        return currentPosition;
    }


}
