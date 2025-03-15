package com.example.demo.dao;

import com.example.demo.entity.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdGenerator {
    private final PlayerFilerBasedOperations playerFilerBasedOperations;

    public IdGenerator(PlayerFilerBasedOperations playerFilerBasedOperations) {
        this.playerFilerBasedOperations = playerFilerBasedOperations;
    }

    public Long createNewId() {
        List<Player> listOfPlayers = playerFilerBasedOperations.readPlayers();
        Long player = listOfPlayers.stream()
                .map(Player::getId) // Извлекаем поле id
                .max(Long::compareTo) // Находим максимальный id
                .orElse(null);
        if(player == null) {
            return 0L;
        }
        return player + 1;
    }
}
