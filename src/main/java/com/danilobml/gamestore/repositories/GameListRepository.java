package com.danilobml.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danilobml.gamestore.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {

}