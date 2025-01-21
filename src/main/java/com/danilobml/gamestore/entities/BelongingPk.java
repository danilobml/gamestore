package com.danilobml.gamestore.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class BelongingPk {

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private GameList list;

    public BelongingPk() {
    }

    public BelongingPk(Game game, GameList list) {
        this.game = game;
        this.list = list;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameList getList() {
        return this.list;
    }

    public void setList(GameList list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(getClass() == obj.getClass()))
            return false;
        BelongingPk other = (BelongingPk) obj;
        return Objects.equals(this.game, other.game) && Objects.equals(this.list, other.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, list);
    }

}
