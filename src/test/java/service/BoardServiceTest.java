package service;

import domain.Player;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
    private BoardService boardService = null;

    @Mock
    DiceService diceService;

    private static MockedStatic<DiceService> serviceMockedStatic ;
    @BeforeAll
    public static void init() {
        serviceMockedStatic =  Mockito.mockStatic(DiceService.class);
    }

    @AfterAll
    public static void close() {
        serviceMockedStatic.close();
    }

    @BeforeEach
    void setup() {
        boardService = new BoardService(getPlayers(), 100);

    }

    @Test
    @DisplayName("Testing the game without croocked Dice")
    void testWithoutSnakeCroockedDice() {
        // crooked
        serviceMockedStatic.when(DiceService::getDiceService).thenReturn(diceService);
        when(diceService.roll()).thenReturn(2);
        boardService.start();
        verify(diceService, times(10)).roll();
    }

    @Test
    @DisplayName("Testing the game snake with croocked Dice")
    void testWithSnakeCroockedDice() {
        // crooked
        serviceMockedStatic.when(DiceService::getDiceService).thenReturn(diceService);
        SnakeService.setSnakeService(new HashMap<>(){{
            put(20,2);
            put(10,4);
        }});
        when(diceService.roll()).thenReturn(2);
        boardService.start();
        verify(diceService, times(10)).roll();
    }



    private Queue<Player> getPlayers() {
        Queue<Player> players = new LinkedList<Player>();
        players.add(new Player("system"));
        return players;
    }

}