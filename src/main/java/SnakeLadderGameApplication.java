import domain.Player;
import service.BoardService;

import java.util.LinkedList;
import java.util.Scanner;

public class SnakeLadderGameApplication {
    static BoardService boardService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the board : ");
        var sizeOfBoard = scanner.nextInt();
        System.out.println("Enter number of players wants to join : 1");
        var noOfPlayers = 1;
        var players = new LinkedList<Player>();
        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println("Enter player "+ i + " : ");
            var player = scanner.next();
            players.add(new Player(player));
        }
        boardService = new BoardService(players, sizeOfBoard);
        boardService.start();
    }
}
