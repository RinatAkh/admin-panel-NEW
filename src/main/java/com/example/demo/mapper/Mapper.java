package com.example.demo.mapper;

import com.example.demo.dto.PlayerFilter;
import com.example.demo.request.GetPlayerListRequest;
import com.example.demo.request.PlayerDaoRequest;
import com.example.demo.entity.Player;
import com.example.demo.request.CreatePlayerRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.request.PlayerFilterRequest;
import com.example.demo.dto.FilterDto;
import com.example.demo.request.UpdatePlayerRequest;
import com.example.demo.response.PlayerResponse;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static PlayerFilterRequest convert(CreatePlayerRequest createPlayerRequest) {
        PlayerFilterRequest playerFilterRequest = new PlayerFilterRequest();
        playerFilterRequest.setName(createPlayerRequest.getName());
        playerFilterRequest.setTitle(createPlayerRequest.getTitle());
        playerFilterRequest.setRace(createPlayerRequest.getRace());
        playerFilterRequest.setProfession(createPlayerRequest.getProfession());
        playerFilterRequest.setBirthday(createPlayerRequest.getBirthday());
        playerFilterRequest.setBanned(createPlayerRequest.getBanned());
        playerFilterRequest.setExperience(createPlayerRequest.getExperience());
        return playerFilterRequest;
    }
    
    public static Player convertToPlayer(PlayerDto playerDto) {
        Player newPlayer = new Player();
        newPlayer.setId(playerDto.getId());
        newPlayer.setName(playerDto.getName());
        newPlayer.setTitle(playerDto.getTitle());
        newPlayer.setRace(playerDto.getRace());
        newPlayer.setProfession(playerDto.getProfession());
        newPlayer.setBirthday(playerDto.getBirthday());
        if(playerDto.getBanned() != null) {
            newPlayer.setBanned(playerDto.getBanned());
        } else {
            newPlayer.setBanned(false);
        }
        newPlayer.setExperience(playerDto.getExperience());
        newPlayer.setLevel(playerDto.getLevel());
        newPlayer.setUntilNextLevel(playerDto.getUntilNextLevel());
        return newPlayer;
    }
    public static PlayerFilter convertToFilterDao(FilterDto filterDto) {
        PlayerFilter playerFilter = new PlayerFilter();
        playerFilter.setName(filterDto.getName());
        playerFilter.setTitle(filterDto.getTitle());
        playerFilter.setRace(filterDto.getRace());
        playerFilter.setProfession(filterDto.getProfession());
        playerFilter.setAfter(filterDto.getAfter());
        playerFilter.setBefore(filterDto.getBefore());
        playerFilter.setBanned(filterDto.getBanned());
        playerFilter.setMinExperience(filterDto.getMinExperience());
        playerFilter.setMaxExperience(filterDto.getMaxExperience());
        playerFilter.setMinLevel(filterDto.getMinLevel());
        playerFilter.setMaxLevel(filterDto.getMaxLevel());
        playerFilter.setOrder(filterDto.getOrder());
        playerFilter.setPageNumber(filterDto.getPageNumber());
        playerFilter.setPageSize(filterDto.getPageSize());
        return playerFilter;
    }

    public static FilterDto convertToFilterDtoFromGetPlayerListRequest(GetPlayerListRequest getPlayerListRequest) {
        FilterDto filterDto = new FilterDto();
        filterDto.setName(getPlayerListRequest.getName());
        filterDto.setRace(getPlayerListRequest.getRace());
        filterDto.setTitle(getPlayerListRequest.getTitle());
        filterDto.setProfession(getPlayerListRequest.getProfession());
        filterDto.setAfter(getPlayerListRequest.getAfter());
        filterDto.setBefore(getPlayerListRequest.getBefore());
        filterDto.setBanned(getPlayerListRequest.getBanned());
        filterDto.setMinExperience(getPlayerListRequest.getMinExperience());
        filterDto.setMaxExperience(getPlayerListRequest.getMaxExperience());
        filterDto.setMinLevel(getPlayerListRequest.getMinLevel());
        filterDto.setMaxLevel(getPlayerListRequest.getMaxLevel());
        filterDto.setOrder(getPlayerListRequest.getOrder());
        filterDto.setPageNumber(getPlayerListRequest.getPageNumber());
        filterDto.setPageSize(getPlayerListRequest.getPageSize());
        return filterDto;
    }

    public static PlayerResponse convert(PlayerDto playerDto) {
        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.setId(playerDto.getId());
        playerResponse.setName(playerDto.getName());
        playerResponse.setTitle(playerDto.getTitle());
        playerResponse.setRace(playerDto.getRace());
        playerResponse.setProfession(playerDto.getProfession());
        playerResponse.setBirthday(playerDto.getBirthday());
        playerResponse.setBanned(playerDto.getBanned());
        playerResponse.setExperience(playerDto.getExperience());
        playerResponse.setLevel(playerDto.getLevel());
        playerResponse.setUntilNextLevel(playerDto.getUntilNextLevel());
        return playerResponse;
    }

    public static PlayerDto convertToPlayerDto (CreatePlayerRequest createPlayerRequest) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(createPlayerRequest.getId());
        playerDto.setName(createPlayerRequest.getName());
        playerDto.setTitle(createPlayerRequest.getTitle());
        playerDto.setRace(createPlayerRequest.getRace());
        playerDto.setProfession(createPlayerRequest.getProfession());
        playerDto.setBirthday(createPlayerRequest.getBirthday());
        playerDto.setBanned(createPlayerRequest.getBanned());
        playerDto.setExperience(createPlayerRequest.getExperience());
        playerDto.setLevel(createPlayerRequest.getLevel());
        playerDto.setUntilNextLevel(createPlayerRequest.getUntilNextLevel());
        return playerDto;
    }
    public static PlayerDto convertToPlayerDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setTitle(player.getTitle());
        playerDto.setRace(player.getRace());
        playerDto.setProfession(player.getProfession());
        playerDto.setBirthday(player.getBirthday());
        playerDto.setBanned(player.getBanned());
        playerDto.setExperience(player.getExperience());
        playerDto.setLevel(player.getLevel());
        playerDto.setUntilNextLevel(player.getUntilNextLevel());
        return playerDto;
    }

    public static PlayerResponse convert(PlayerFilterRequest playerFilterRequest) {
        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.setId(playerFilterRequest.getId());
        playerResponse.setName(playerFilterRequest.getName());
        playerResponse.setTitle(playerFilterRequest.getTitle());
        playerResponse.setProfession(playerFilterRequest.getProfession());
        playerResponse.setRace(playerFilterRequest.getRace());
        playerResponse.setBirthday(playerFilterRequest.getBirthday());
        playerResponse.setBanned(playerFilterRequest.getBanned());
        playerResponse.setExperience(playerFilterRequest.getExperience());
        return playerResponse;
    }

    public static Player convertToPlayer(PlayerFilterRequest playerFilterRequest) {
        Player player = new Player();
        player.setId(playerFilterRequest.getId());
        player.setName(playerFilterRequest.getName());
        player.setTitle(playerFilterRequest.getTitle());
        player.setProfession(playerFilterRequest.getProfession());
        player.setRace(playerFilterRequest.getRace());
        player.setBirthday(playerFilterRequest.getBirthday());
        player.setBanned(playerFilterRequest.getBanned());
        player.setExperience(playerFilterRequest.getExperience());
        return player;
    }

    public static PlayerFilterRequest convert(Player player) {
        PlayerFilterRequest playerFilterRequest = new PlayerFilterRequest();
        playerFilterRequest.setId(player.getId());
        playerFilterRequest.setName(player.getName());
        playerFilterRequest.setTitle(player.getTitle());
        playerFilterRequest.setProfession(player.getProfession());
        playerFilterRequest.setRace(player.getRace());
        playerFilterRequest.setBirthday(player.getBirthday());
        playerFilterRequest.setBanned(player.getBanned());
        playerFilterRequest.setExperience(player.getExperience());
        return playerFilterRequest;
    }

    public static PlayerFilterRequest convert(UpdatePlayerRequest updatePlayerRequest) {
        PlayerFilterRequest playerFilterRequest = new PlayerFilterRequest();
        playerFilterRequest.setId(updatePlayerRequest.getId());
        playerFilterRequest.setName(updatePlayerRequest.getName());
        playerFilterRequest.setTitle(updatePlayerRequest.getTitle());
        playerFilterRequest.setProfession(updatePlayerRequest.getProfession());
        playerFilterRequest.setRace(updatePlayerRequest.getRace());
        playerFilterRequest.setBirthday(updatePlayerRequest.getBirthday());
        playerFilterRequest.setBanned(updatePlayerRequest.getBanned());
        playerFilterRequest.setExperience(updatePlayerRequest.getExperience());
        return playerFilterRequest;
    }

    public static PlayerDto convertToPlayerDto(UpdatePlayerRequest updatePlayerRequest) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(updatePlayerRequest.getId());
        playerDto.setName(updatePlayerRequest.getName());
        playerDto.setTitle(updatePlayerRequest.getTitle());
        playerDto.setProfession(updatePlayerRequest.getProfession());
        playerDto.setRace(updatePlayerRequest.getRace());
        playerDto.setBirthday(updatePlayerRequest.getBirthday());
        playerDto.setBanned(updatePlayerRequest.getBanned());
        playerDto.setExperience(updatePlayerRequest.getExperience());
        playerDto.setLevel(updatePlayerRequest.getLevel());
        playerDto.setUntilNextLevel(updatePlayerRequest.getUntilNextLevel());
        return playerDto;
    }

    public static PlayerDaoRequest convert(FilterDto filterDto) {
        PlayerDaoRequest playerDaoRequest = new PlayerDaoRequest();
        playerDaoRequest.setName(filterDto.getName());
        playerDaoRequest.setRace(filterDto.getRace());
        playerDaoRequest.setTitle(filterDto.getTitle());
        playerDaoRequest.setProfession(filterDto.getProfession());
        playerDaoRequest.setAfter(filterDto.getAfter());
        playerDaoRequest.setBefore(filterDto.getBefore());
        playerDaoRequest.setBanned(filterDto.getBanned());
        playerDaoRequest.setMinExperience(filterDto.getMinExperience());
        playerDaoRequest.setMaxExperience(filterDto.getMaxExperience());
        playerDaoRequest.setMinLevel(filterDto.getMinLevel());
        playerDaoRequest.setMaxLevel(filterDto.getMaxLevel());
        playerDaoRequest.setOrder(filterDto.getOrder());
        playerDaoRequest.setPageNumber(filterDto.getPageNumber());
        playerDaoRequest.setPageSize(filterDto.getPageSize());
        return playerDaoRequest;
    }


    public static List<PlayerDto> convert(List<Player> listOfPlayer) {
        return listOfPlayer.stream()
                .map(listA -> new PlayerDto(listA.getId(), listA.getName(), listA.getTitle(), listA.getProfession(), listA.getRace(), listA.getExperience(), listA.getLevel(), listA.getUntilNextLevel(), listA.getBirthday(), listA.getBanned()))
                .collect(Collectors.toList());
    }

    public static List<PlayerDto> convertToListOfPlayerDto(List<Player> listOfPlayer) {
        return listOfPlayer.stream()
                .map(listA -> new PlayerDto(listA.getId(), listA.getName(), listA.getTitle(), listA.getProfession(), listA.getRace(), listA.getExperience(), listA.getLevel(), listA.getUntilNextLevel(), listA.getBirthday(), listA.getBanned()))
                .collect(Collectors.toList());
    }

    public static List<PlayerResponse> convertToPlayerResponse(List<PlayerDto> listOfPlayer) {
        return listOfPlayer.stream()
                .map(listA -> new PlayerResponse(listA.getId(), listA.getName(), listA.getTitle(), listA.getProfession(), listA.getRace(), listA.getExperience(), listA.getLevel(), listA.getUntilNextLevel(), listA.getBirthday(), listA.getBanned()))
                .collect(Collectors.toList());
    }


}
