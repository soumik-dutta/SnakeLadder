package service;

import domain.Player;

import java.util.Queue;

public class BoardService {
    // player queue
    private Queue<Player> players;
    // board size
    private int boardSize;

    public BoardService(Queue<Player> players, int boardSize) {
        this.players = players;
        this.boardSize = boardSize;
    }

    /**
     * Starting the game
     */
    public void start() {

    }


}
