package com.danilobml.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danilobml.gamestore.entities.Game;


public interface GameRepository extends JpaRepository<Game, Long> {
    
}
