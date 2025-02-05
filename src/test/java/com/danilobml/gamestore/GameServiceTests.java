package com.danilobml.gamestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.danilobml.gamestore.dto.GameCreateDTO;
import com.danilobml.gamestore.dto.GameDTO;
import com.danilobml.gamestore.dto.GameMinDTO;
import com.danilobml.gamestore.entities.Game;
import com.danilobml.gamestore.exceptions.GameNotFoundException;
import com.danilobml.gamestore.repositories.GameRepository;
import com.danilobml.gamestore.services.GameServiceImpl;
import com.danilobml.gamestore.projections.GameMinProjection;


@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

    @Mock
    GameRepository gameRepository;

    @InjectMocks
    GameServiceImpl gameService;

    private GameMinProjection gameProjection1;
    private GameMinProjection gameProjection2;

    @Test
    public void getAllGamesTest() {
        when(gameRepository.findAll()).thenReturn(Arrays.asList(
                new Game(1, "Test 1", 2020, "Platform", "PS5", 7.9, "www.testimage.com", "Short description1",
                        "Long description1"),
                new Game(2, "Test 2", 2021, "Platform", "XBOX", 8.1, "www.testimage2.com", "Short description2",
                        "Long description2")));

        List<GameMinDTO> result = gameService.findAll();

        assertEquals("Test 1", result.get(0).getTitle());
        assertEquals("Short description2", result.get(1).getShortDescription());
    }

    @Test
    public void getGameByIdTest() {
        Long existentId = 1L;
        Long nonExistentId = 999L;
        Game game = new Game(existentId, "Test 1", 2020, "Platform", "PS5", 7.9, "www.testimage.com",
                "Short description1", "Long description1");
        when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));
        when(gameRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        GameDTO result = gameService.findById(existentId);

        assertEquals(game.getTitle(), result.getTitle());
        assertThrows(GameNotFoundException.class, () -> gameService.findById(nonExistentId));
    }

    @Test
    public void searchByListTest() {

        gameProjection1 = new GameMinProjection() {
            @Override
            public long getId() { return 1L; }
            @Override
            public String getTitle() { return "Game 1"; }
            @Override
            public int getYear() { return 2020; }  
            @Override
            public String getImgUrl() { return "www.testimage1.com"; }  
            @Override
            public String getShortDescription() { return "Short description 1"; }   
            @Override
            public int getPosition() { return 1; }
        };
    
        gameProjection2 = new GameMinProjection() {
            @Override
            public long getId() { return 2L; }
            @Override
            public String getTitle() { return "Game 2"; }
            @Override
            public int getYear() { return 2021; }
            @Override
            public String getImgUrl() { return "www.testimage2.com"; }
            @Override
            public String getShortDescription() { return "Short description 2"; }
            @Override
            public int getPosition() { return 2; }
        };

        Long listId = 1L;
        List<GameMinProjection> mockProjections = Arrays.asList(gameProjection1, gameProjection2);
        
        when(gameRepository.searchByList(listId)).thenReturn(mockProjections);

        List<GameMinDTO> result = gameService.searchByList(listId);

        assertEquals(2, result.size());
        assertEquals("Game 1", result.get(0).getTitle());
        assertEquals("Short description 2", result.get(1).getShortDescription());

        verify(gameRepository, times(1)).searchByList(listId);
    }


    @Test
    public void createGameTest() {
        Game game = new Game(1, "Test 1", 2020, "Platform", "PS5", 7.9, "www.testimage.com", "Short description1",
                "Long description1");
        GameCreateDTO newGame = new GameCreateDTO("Test 1", 2020, "Platform", "PS5", 7.9, "www.testimage.com",
                "Short description1", "Long description1");
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        gameService.createGame(newGame);

        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    public void updateGameTest() {
        Game game = new Game(1, "Test 1", 2020, "Platform", "PS5", 7.9, "www.testimage.com", "Short description1",
        "Long description1");
        GameDTO updatedGame = new GameDTO(game);
        when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        updatedGame.setTitle("Updated Test 1");
        gameService.updateGame(game.getId(), updatedGame);

        verify(gameRepository, times(1)).save(game);
    }

    @Test
    public void deleteGameTest() {
        Game game = new Game(1, "Test 1", 2020, "Platform", "PS5", 7.9, "www.testimage.com", "Short description1",
        "Long description1");
        when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));

        gameService.deleteGame(game.getId());

        verify(gameRepository, times(1)).delete(game);
    }

}
