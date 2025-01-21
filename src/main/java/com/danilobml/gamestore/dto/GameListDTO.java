package com.danilobml.gamestore.dto;

import org.springframework.beans.BeanUtils;

import com.danilobml.gamestore.entities.GameList;

public class GameListDTO {
    
    private long id;
    private String name;

    public GameListDTO() {
    }

    public GameListDTO(GameList entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
