package com.danilobml.gamestore.dto;

import org.springframework.beans.BeanUtils;

import com.danilobml.gamestore.entities.GameList;

import jakarta.validation.constraints.NotBlank;

public class GameListCreateDTO {
    
    @NotBlank(message = "The name cannot be blank")
    private String name;

    public GameListCreateDTO() {
    }

    public GameListCreateDTO(String name) {
        this.name = name;
    }

    public GameListCreateDTO(GameList entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
