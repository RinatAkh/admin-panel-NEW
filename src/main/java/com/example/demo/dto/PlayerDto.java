package com.example.demo.dto;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;

import java.util.Date;

@Data
public class PlayerDto {
    private Long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Date birthday;
    private Boolean banned;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;

    public PlayerDto() {

    }
    public PlayerDto(Long id, String name, String title, Profession profession, Race race, Integer experience, Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
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
