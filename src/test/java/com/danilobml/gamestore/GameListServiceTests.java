package com.danilobml.gamestore;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.danilobml.gamestore.dto.GameListDTO;
import com.danilobml.gamestore.entities.GameList;
import com.danilobml.gamestore.repositories.GameListRepository;
import com.danilobml.gamestore.services.GameListServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class GameListServiceTests {
    
    @Mock
    GameListRepository gameListRepository;

    @InjectMocks
    GameListServiceImpl gameListService;

    @Test
    public void findAllTest() {
        when(gameListRepository.findAll()).thenReturn(Arrays.asList(
            new GameList("List 1"),
            new GameList("List 2")
        ));

        List<GameListDTO> result = gameListService.findAll();
        
        assertEquals("List 1", result.get(0).getName());
        assertEquals("List 2", result.get(1).getName());
    }

    @Test
    public void findByIdTest() {
        
    }

    @Test
    public void createListTest() {
        
    }

    @Test
    public void deleteListTest() {
        
    }

    @Test
    public void changeGamePositionInListTest() {
        
    }

    @Test
    public void addGameToListTest() {
        
    }

    @Test
    public void removeGameFromListTest() {

    }

}
