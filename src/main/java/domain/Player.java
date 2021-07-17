package domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Player {
    private String name;
    private String id;

    public Player(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }
}
