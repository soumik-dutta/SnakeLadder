package domain;

import java.util.Map;

public class Twists {
    private Map<Integer,Integer> moves;

    public Map<Integer, Integer> getMoves() {
        return moves;
    }

    public void setMoves(Map<Integer, Integer> moves) {
        this.moves = moves;
    }

    public Twists(Map<Integer, Integer> moves) {
        this.moves = moves;
    }
}
