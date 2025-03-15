package com.example.demo.service;

import com.example.demo.dao.PlayerDao;
import com.example.demo.entity.Player;
import com.example.demo.mapper.Mapper;
import com.example.demo.dto.PlayerFilter;
import com.example.demo.dto.PlayerDto;
import com.example.demo.dto.FilterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerDao playerDao;

    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public PlayerDto createNewPlayer(PlayerDto playerDto) {
        Player newPlayer = Mapper.convertToPlayer(playerDto);
        newPlayer.setLevel(calculateLevel(playerDto.getExperience()));
        newPlayer.setUntilNextLevel(calculateUntilNextLevel(newPlayer.getLevel(), newPlayer.getExperience()));
        Player savedPlayer = playerDao.savePlayer(newPlayer);
        return Mapper.convertToPlayerDto(savedPlayer);
    }

    // 3. Редактировать характеристики существующего игрока
    public PlayerDto updatePlayer(PlayerDto player) {
        Player convertedPlayer = Mapper.convertToPlayer(player);
        Mapper.convertToPlayer(player);
        Player updatedPlayer = playerDao.updatePlayer(convertedPlayer);
        if(updatedPlayer == null) {
            return null;
        }

        return Mapper.convertToPlayerDto(updatedPlayer);
    }

    // 4. удаление игрока
    public void deletePlayer(Long id) {
        playerDao.deletePlayer(id);
    }

    //5. получать игрока по id;
    public PlayerDto getPlayerById(Long id) {
        Player player = playerDao.getPlayerById(id);
        if(player == null) {
            return null;
        }

        return Mapper.convertToPlayerDto(player);
    }

    public List<PlayerDto> getFilteredPlayers(FilterDto filterDto) {
        PlayerFilter playerFilter = Mapper.convertToFilterDao(filterDto);
        List<Player> listOfPlayers = playerDao.getAllPlayers(playerFilter);
        return Mapper.convertToListOfPlayerDto(listOfPlayers);
    }

    public Integer getNumberOfFilteredPlayers(FilterDto filterDto) {
        PlayerFilter playerFilter = Mapper.convertToFilterDao(filterDto);
        return playerDao.getAllPlayersCount(playerFilter);
    }
    private int calculateLevel(int experience) {
        return (int) ((Math.sqrt(2500 + 200 * experience)) - 50) / 100;
    }

    private int calculateUntilNextLevel(int level, int experience) {
        return 50 * (level + 1) * (level + 2) - experience;
    }
}
