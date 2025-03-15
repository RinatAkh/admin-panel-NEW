package com.example.demo.dao;

import com.example.demo.entity.Player;
import com.example.demo.dto.PlayerFilter;

import java.util.List;

public interface PlayerDao {
    Player savePlayer(Player player);
    List<Player> getAllPlayers(PlayerFilter player);
    int getAllPlayersCount(PlayerFilter player);
    void deletePlayer(Long id);
    Player updatePlayer(Player player);
    Player getPlayerById(Long id);
}
