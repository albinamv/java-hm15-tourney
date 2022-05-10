package ru.netology.gameplay;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Player first = new Player(1, "Maxim", 100);
    private Player second = new Player(2, "Ivan", 300);
    private Player third = new Player(3, "Sasha", 150);
    private Player thirdSameId = new Player(3, "Someone", 150);
    private Player thirdSameName = new Player(10, "Sasha", 150);
    private Player fourth = new Player(4, "Natalya", 100);
    private Player unregistered = new Player(150, "Undefined", 1000);
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();

        game.register(first);
        game.register(second);
        game.register(third);
        game.register(fourth);
    }

    @Test
    public void shouldNotAddExistingName() {
        assertThrows(AlreadyExistsException.class, () -> {
            game.register(thirdSameName);
        });
    }

    @Test
    public void shouldNotAddExistingId() {
       assertThrows(AlreadyExistsException.class, () -> {
            game.register(thirdSameId);
        });
    }

    @Test
    public void shouldNotAddAlreadyRegistered() {
        assertThrows(AlreadyExistsException.class, () -> {
            game.register(second);
        });
    }

    @Test
    public void shouldWinFirstPlayer() {
        int expected = 1;
        int actual = game.round(second.getName(), fourth.getName());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldWinSecondPlayer() {
        int expected = 2;
        int actual = game.round(first.getName(), third.getName());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeADraw() {
        int expected = 0;
        int actual = game.round(first.getName(), fourth.getName());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotPlayWithOneUnregistered() {
        assertThrows(NotRegisteredException.class, () -> {
            game.round(first.getName(), unregistered.getName());
        });
    }

    @Test
    public void shouldNotPlayWithBothUnregistered() {
        assertThrows(NotRegisteredException.class, () -> {
            game.round(thirdSameId.getName(), unregistered.getName());
        });
    }

    @Test
    public void shouldNotPlayWithTheSamePlayer() {
        assertThrows(SamePlayerException.class, () -> {
            game.round(second.getName(), second.getName());
        });
    }


}