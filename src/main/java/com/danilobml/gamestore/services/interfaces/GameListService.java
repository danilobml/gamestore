package com.danilobml.gamestore.services.interfaces;

import java.util.List;

import com.danilobml.gamestore.dto.GameListDTO;

public interface GameListService {
    List<GameListDTO> findAll();
}
