package com.example.demo.request;

import com.example.demo.annotation.ValidBirthday;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreatePlayerRequest {
    private Long id;
    @Length(min = 1, max = 12)
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    @Length(min = 1, max = 30)
    private String title;
    @NotNull
    private Race race;
    @NotNull
    private Profession profession;
    @ValidBirthday
    @NotNull
    private Date birthday;
    private Boolean banned;
    @Min(0)
    @Max(10000000)
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;
}
