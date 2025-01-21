package com.danilobml.gamestore.entities;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_belonging")
public class Belonging {

    @EmbeddedId
    private BelongingPk id = new BelongingPk();
    private int position;

    public Belonging() {
    }

    public Belonging(Game game, GameList list, int position) {
        this.id.setGame(game);
        this.id.setList(list);
        this.position = position;
    }

    public BelongingPk getId() {
        return this.id;
    }

    public void setId(BelongingPk id) {
        this.id = id;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(getClass() == obj.getClass())) return false;
        Belonging other = (Belonging) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}