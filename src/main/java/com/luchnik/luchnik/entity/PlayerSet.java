package com.luchnik.luchnik.entity;

import javax.persistence.*;

@Entity
@Table(name = "playerset")
public class PlayerSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String playername;
    private Integer set0;
    private Integer set1;
    private Integer set2;

    public PlayerSet(String playername, Integer set0, Integer set1, Integer set2) {
        this.playername = playername;
        this.set0 = set0;
        this.set1 = set1;
        this.set2 = set2;
    }

    public PlayerSet() {
    }

    public Integer getId() {
        return id;
    }


    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public Integer getSet0() {
        return set0;
    }

    public void setSet0(Integer set0) {
        this.set0 = set0;
    }

    public Integer getSet1() {
        return set1;
    }

    public void setSet1(Integer set1) {
        this.set1 = set1;
    }

    public Integer getSet2() {
        return set2;
    }

    public void setSet2(Integer set2) {
        this.set2 = set2;
    }
}
