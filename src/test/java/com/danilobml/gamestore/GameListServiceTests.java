package com.danilobml.gamestore;

import static org.junit.Assert.assertEquals;
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

import com.danilobml.gamestore.dto.GameListDTO;
import com.danilobml.gamestore.entities.GameList;
import com.danilobml.gamestore.repositories.GameListRepository;
import com.danilobml.gamestore.repositories.GameRepository;
import com.danilobml.gamestore.services.GameListServiceImpl;
import com.danilobml.gamestore.exceptions.ListNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class GameListServiceTests {
    
    @Mock
    GameListRepository gameListRepository;

    @Mock
    GameRepository gameRepository;

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
        Long nonExistentId = 999L;
        GameList existentList = new GameList("List 1");
        Long existentId = existentList.getId();
        when(gameListRepository.findById(existentId)).thenReturn(Optional.of(existentList));
        when(gameListRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        GameListDTO result = gameListService.findById(existentId);

        assertEquals("List 1", result.getName());
        assertThrows(ListNotFoundException.class, () -> gameListService.findById(nonExistentId));
    }

    @Test
    public void createListTest() {
        GameList list = new GameList("List 1");
        when(gameListRepository.save(any(GameList.class))).thenReturn(list);

        gameListService.createList("List 1");

        verify(gameListRepository, times(1)).save(any(GameList.class));
    }

    @Test
    public void deleteListTest() {
        GameList list = new GameList("List 1");
        when(gameListRepository.findById(list.getId())).thenReturn(Optional.of(list));
        when(gameRepository.searchByList(list.getId())).thenReturn(Arrays.asList());

        gameListService.deleteList(list.getId());

        verify(gameListRepository, times(1)).delete(list);     
    }
}
