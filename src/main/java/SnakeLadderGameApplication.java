import domain.Player;
import service.BoardService;
import service.DiceService;
import service.SnakeService;

import java.util.*;

public class SnakeLadderGameApplication {
    private static final String CONFIRM = "Y";
    static BoardService boardService;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the board: ");
        var sizeOfBoard = scanner.nextInt();
        System.out.println("Enter number of players wants to join: 1");
        var noOfPlayers = 1;
        var players = new LinkedList<Player>();
        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println("Enter player " + i + 1 + " : ");
            var player = scanner.next();
            players.add(new Player(player));
        }
        System.out.println("Do you want to enter snake moves(Y/N): ");
        String reply = scanner.next();
        if (reply.equalsIgnoreCase(CONFIRM)) {
            System.out.println("Enter number of moves: ");
            int snakeMovesSize = scanner.nextInt();
            System.out.println("Enter " + snakeMovesSize + " moves (Eg. 14 4 : any player at 14 will come down to 4)");
            Map<Integer, Integer> snakeTwists = new HashMap<>();
            for (int i = 0; i < snakeMovesSize; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                snakeTwists.put(from, to);
            }
            // create snake object with the configured values
            SnakeService.setSnakeService(snakeTwists);

        }
        // adding crooked dice
        DiceService.setDiceService(new Random().nextInt(1));

        boardService = new BoardService(players, sizeOfBoard);
        boardService.start();
    }
}
