package service;

import domain.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class BoardService {
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
            // play his turn
            play(player);
        }
    }

    private void play(Player player) {
        // role dice
        int dice = DiceService.roll();
        System.out.println(player.getName() + " rolled : " + dice);
        var playerPosition = dice;
        if (getPlayerCurrentPosition().containsKey(player)) {
            // increment the position according to the dice output
            playerPosition = getPlayerCurrentPosition().get(player) + dice;
        }
        // check for valid final move
        if (!finalMoveValid(playerPosition)){
            System.out.println(player.getName() + " move not valid try again ... ");
            getPlayers().offer(player);
            return;
        }
        System.out.println(player.getName() + " moved to  : " + playerPosition);
        getPlayerCurrentPosition().put(player, playerPosition);
        // add the player back into queue if he/she has not reached to the board size
        if (!isFinished(playerPosition)) {
            getPlayers().offer(player);
        }
    }

    public boolean isFinished(int position) {
        return position == getBoardSize();
    }

    /**
     * Check whether the player has crossed the board size
     *
     * @param position
     * @return
     */
    public boolean finalMoveValid(int position) {
        return position <= getBoardSize();
    }


}
