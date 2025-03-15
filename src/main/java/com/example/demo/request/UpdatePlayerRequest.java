package com.example.demo.request;

import com.example.demo.annotation.ValidBirthday;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Data
public class UpdatePlayerRequest {
    @Min(0)
    @Max(Long.MAX_VALUE)
    @PositiveOrZero
    private long id;
    @Length(min = 1, max = 12)
    private String name;
    @Length(min = 1, max = 30)
    private String title;
    private Race race;
    private Profession profession;
    @ValidBirthday
    private Date birthday;
    private Boolean banned;
    @Min(0)
    @Max(10000000)
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;
}
