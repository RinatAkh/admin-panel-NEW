package com.example.demo.dao;

import com.example.demo.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PlayerFilerBasedOperations {
    private final String filePath = "/Users/rinatahmetgariev/Desktop/Код/checking.txt";

    public List<Player> readPlayers() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Player>) objectInputStream.readObject();

        } catch (Exception e) {
            log.error("get all players", e);
            return new ArrayList<>();
        }
    }

    public void savePlayers(List<Player> players) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(players);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
