package com.danilobml.gamestore.services.interfaces;

import java.util.List;

import com.danilobml.gamestore.dto.GameDTO;
import com.danilobml.gamestore.dto.GameMinDTO;

public interface GameService {
    List<GameMinDTO> findAll();
    GameDTO findById(long id);
    List<GameMinDTO> searchByList(Long listId);
}
