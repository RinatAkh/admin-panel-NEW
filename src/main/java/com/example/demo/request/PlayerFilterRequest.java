package com.example.demo.request;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import lombok.Data;

import java.util.Date;

@Data
public class PlayerFilterRequest {
    private Long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Date birthday;
    private Long after;
    private Long before;
    private Boolean banned;
    private Integer experience;
    private Integer minExperience;
    private Integer maxExperience;
    private Integer level;
    private Integer untilNextLevel;
    private Integer minLevel;
    private Integer maxLevel;
    private PlayerOrder order;
    private Integer pageNumber;
    private Integer pageSize;
}
