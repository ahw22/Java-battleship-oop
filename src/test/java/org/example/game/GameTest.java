package org.example.game;

import org.example.event.Event;
import org.example.model.board.Board;
import org.example.model.board.Position;
import org.example.output.ConsoleOutputController;
import org.example.output.OutputControllerInterface;
import org.example.player.AbstractPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameTest {
    @Mock
    AbstractPlayer player1;
    @Mock
    AbstractPlayer opponent;

    @Mock
    OutputControllerInterface out;

    Game game;

    @BeforeEach
    void setup() {
        game = spy(new Game(player1, opponent, out));
    }

    @Test
    void addListener_shouldAddListener() {
        OutputControllerInterface test = new ConsoleOutputController();
        game.addListener(test);

        assertTrue(game.getListeners().size() == 2);
    }

    @Test
    void fireAt_shouldEmitHitOnHit() {
        Position target = new Position();
        Board board = mock(Board.class);
        when(opponent.getOwnBoard()).thenReturn(board);
        when(board.fire(target)).thenReturn(true);

        game.fireAt(target);

        verify(board).fire(target);
        verify(game).emit(Event.Type.HIT, "Hit at " + target + "!");
        verify(game).nextTurn();
    }

    @Test
    void fireAt_shouldEmitMissOnMiss() {
        Position target = new Position();
        Board board = mock(Board.class);
        when(opponent.getOwnBoard()).thenReturn(board);
        when(board.fire(target)).thenReturn(false);

        game.fireAt(target);

        verify(board).fire(target);
        verify(game).emit(Event.Type.MISS, "Miss at " + target + ".");
        verify(game).nextTurn();
    }



}