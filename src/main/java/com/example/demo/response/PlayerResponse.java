package com.example.demo.response;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PlayerResponse implements Serializable {
    private Long id;
    private String name;
    private String title;
    private Profession profession;
    private Race race;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;
    private Date birthday;
    private Boolean banned;

    public PlayerResponse() {
    }

    public PlayerResponse(Long id, String name, String title, Profession profession, Race race, Integer experience, Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.profession = profession;
        this.race = race;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
        this.birthday = birthday;
        this.banned = banned;
    }
}
