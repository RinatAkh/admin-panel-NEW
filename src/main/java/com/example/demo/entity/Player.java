package com.example.demo.entity;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Generated;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "players") //!!!
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 12, message = "Name should be less than 13 characters")
    private String name;
    @Size(max = 30, message = "Title should be less than 30 characters")
    private String title;
    @Enumerated(EnumType.STRING)
    private Profession profession;
    @Enumerated(EnumType.STRING)
    private Race race;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private Boolean banned;
}
