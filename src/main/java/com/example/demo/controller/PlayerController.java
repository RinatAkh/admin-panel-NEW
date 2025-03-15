package com.example.demo.controller;

import com.example.demo.exception.InvalidInputException;
import com.example.demo.mapper.Mapper;
import com.example.demo.request.GetPlayerListRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.dto.FilterDto;
import com.example.demo.request.CreatePlayerRequest;
import com.example.demo.request.UpdatePlayerRequest;
import com.example.demo.response.PlayerResponse;
import com.example.demo.service.PlayerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@Validated
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping("/rest/players")
    public List<PlayerResponse> getPlayerList(GetPlayerListRequest getPlayerListRequest) {
        FilterDto filterDto = Mapper.convertToFilterDtoFromGetPlayerListRequest(getPlayerListRequest);
        return Mapper.convertToPlayerResponse(playerService.getFilteredPlayers(filterDto));
    }

    @GetMapping("/rest/players/count")
    public Integer getPlayersCount(@NotNull @Valid GetPlayerListRequest getPlayerListRequest) {
        FilterDto filterDto = Mapper.convertToFilterDtoFromGetPlayerListRequest(getPlayerListRequest);
        return playerService.getNumberOfFilteredPlayers(filterDto);
    }


    @PostMapping("/rest/players")
    public PlayerResponse createNewPlayer(@RequestBody @Valid CreatePlayerRequest createPlayerRequest) throws InvalidInputException {
        PlayerDto playerDto =  Mapper.convertToPlayerDto(createPlayerRequest);
        PlayerDto newPlayer = playerService.createNewPlayer(playerDto);
        return Mapper.convert(newPlayer);
    }

    @GetMapping("/rest/players/{id}")
    public PlayerResponse getPlayer(@PathVariable("id") Long id) {
        return Mapper.convert(playerService.getPlayerById(id));
    }

    @PostMapping("/rest/players/{id}")
    public PlayerResponse updatePlayer(@PathVariable("id") Long id, @RequestBody UpdatePlayerRequest updatePlayerRequest) {
        updatePlayerRequest.setId(id);
        PlayerDto playerDto = Mapper.convertToPlayerDto(updatePlayerRequest);
        PlayerDto response = playerService.updatePlayer(playerDto);
        return Mapper.convert(response);
    }

    @DeleteMapping("/rest/players/{id}")
    public void deletePlayer(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
    }

}
