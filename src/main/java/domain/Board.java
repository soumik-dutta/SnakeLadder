package domain;

import lombok.Data;

import java.util.List;

@Data
public class Board {
    private List<Player> players;
}
