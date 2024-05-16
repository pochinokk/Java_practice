package com.example.Practice24;

import com.example.Practice24.entity.Game;
import com.example.Practice24.entity.Game;
import com.example.Practice24.repository.GameRepository;
import com.example.Practice24.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;






@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    public void testCreate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.MAY, 10, 16, 15, 0);
        Date date = calendar.getTime();
        Game expectedGame = new Game(null, date, "Test game", null);
        when(gameRepository.save(any())).thenReturn(expectedGame);
        Game createdGame = gameService.create(expectedGame);
        verify(gameRepository, times(1)).save(any());
        expectedGame.setId(createdGame.getId());
        assertEquals(expectedGame, createdGame);
    }

    @Test
    public void testReadAll() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.MAY, 1, 12, 32, 0);
        Date date1 = calendar.getTime();
        calendar.set(2024, Calendar.MAY, 2, 12, 33, 0);
        Date date2 = calendar.getTime();
        calendar.set(2024, Calendar.MAY, 3, 12, 34, 0);
        Date date3 = calendar.getTime();
        calendar.set(2024, Calendar.MAY, 4, 12, 35, 0);
        Date date4 = calendar.getTime();
        Game expectedGame1 = new Game(1L, date1, "Scary game", null);
        Game expectedGame2 = new Game(2L, date2, "Superb game", null);
        Game expectedGame3 = new Game(3L, date3, "Lol game", null);
        Game expectedGame4 = new Game(7L, date4, "Amazing game", null);
        List<Game> expectedGames = Arrays.asList(
                expectedGame1,
                expectedGame2,
                expectedGame3,
                expectedGame4
        );
        when(gameRepository.findAll()).thenReturn(expectedGames);
        List<Game> games = gameService.readAll();
        verify(gameRepository, times(1)).findAll();
        assertEquals(expectedGames, games);
    }

    @Test
    public void testRead() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.MAY, 2, 12, 33, 0);
        Date date = calendar.getTime();
        Game expectedGame = new Game(2L, date, "Superb game", null);
        when(gameRepository.findById(expectedGame.getId())).thenReturn(Optional.of(expectedGame));
        Game readGame = gameService.read(2L);
        verify(gameRepository, times(1)).findById(expectedGame.getId());
        assertEquals(expectedGame, readGame);
    }



    @Test
    public void testUpdate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.MAY, 2, 12, 33, 0);
        Date date = calendar.getTime();
        Game expectedGame = new Game(null, date, "Superb game", null);
        when(gameRepository.existsById(1L)).thenReturn(true);
        when(gameRepository.save(any())).thenReturn(expectedGame);
        Game updatedGame = gameService.update(expectedGame, 1L);
        verify(gameRepository, times(1)).save(expectedGame);
        assertEquals(expectedGame, updatedGame);
    }

    @Test
    public void testDelete() {
        Long id = 37L;
        when(gameRepository.existsById(id)).thenReturn(true);
        boolean isDeleted = gameService.delete(id);
        verify(gameRepository, times(1)).deleteById(id);
        assertTrue(isDeleted);
    }
}