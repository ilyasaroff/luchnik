package com.luchnik.luchnik.entity;

import javax.persistence.*;

@Entity
@Table(name = "game_player")
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String player1;
    private String player2;
    private Integer player;

    public GamePlayer() {
    }

    public GamePlayer(String player1, String player2, Integer player) {
        this.player1 = player1;
        this.player2 = player2;
        this.player = player;
    }

    public Integer getId() {
        return id;
    }


    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }
}
