package com.example.demo.dao;

import com.example.demo.entity.Player;
import com.example.demo.dto.PlayerFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


@Slf4j
public class FilePlayerDao implements PlayerDao {
    private final IdGenerator idGenerator;
    private final PlayerFilerBasedOperations operations;

    private final String filePath = "/Users/rinatahmetgariev/Desktop/Код/checking.txt";

    public FilePlayerDao(IdGenerator idGenerator, PlayerFilerBasedOperations operations) {
        this.idGenerator = idGenerator;
        this.operations = operations;
    }

    @Override
    public Player savePlayer(Player player) {
        player.setId(idGenerator.createNewId());
        List<Player> listOfPlayers = getAllPlayers(null);
        listOfPlayers.add(player);
        operations.savePlayers(listOfPlayers);
        return player;
    }

    // При работе с БД код будет изменен
    @Override
    public int getAllPlayersCount(PlayerFilter playerFilter) {
        return getAllPlayers(playerFilter).size();
    }


    @Override
    public List<Player> getAllPlayers(PlayerFilter playerFilter) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Player> listOfPlayers = (List<Player>) objectInputStream.readObject();
            return listOfPlayers.stream()
                    .filter(player -> isApplicable(player, playerFilter))
                    .sorted(new PlayerComparator(playerFilter == null ? null : playerFilter.getOrder()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("get all players", e);
            return new ArrayList<>();
        }
    }

    private boolean isApplicable(Player player, PlayerFilter playerFilter) {
        if (playerFilter == null) {
            return true;
        }
        return (playerFilter.getName() == null || player.getName().contains(playerFilter.getName())) &&
                (playerFilter.getTitle() == null || player.getTitle().equals(playerFilter.getTitle())) &&
                (playerFilter.getRace() == null || player.getRace().equals(playerFilter.getRace())) &&
                (playerFilter.getProfession() == null || player.getProfession().equals(playerFilter.getProfession())) &&
                (playerFilter.getBanned() == null || player.getBanned().equals(playerFilter.getBanned())) &&
                (playerFilter.getMinLevel() == null || player.getLevel() >= playerFilter.getMinLevel()) &&
                (playerFilter.getMaxLevel() == null || player.getLevel() <= playerFilter.getMaxLevel()) &&
                (playerFilter.getMinExperience() == null || player.getExperience() >= playerFilter.getMinExperience()) &&
                (playerFilter.getMaxExperience() == null || player.getExperience() <= playerFilter.getMaxExperience()) &&
                (playerFilter.getAfter() == null || player.getBirthday().after(new Date(playerFilter.getAfter()))) &&
                (playerFilter.getBefore() == null || player.getBirthday().before(new Date(playerFilter.getBefore())));
    }

    public void deletePlayer(Long id) {
        List<Player> listOfPlayers = getAllPlayers(null);
        listOfPlayers.removeIf(player -> Objects.equals(player.getId(), id));
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(listOfPlayers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player updatePlayer(Player player) {
        List<Player> listOfPlayers = getAllPlayers(null);
        Player playerNew = getPlayerById(player.getId());
        if (player.getName() != null) {
            playerNew.setName(player.getName());
        }
        if (player.getTitle() != null) {
            playerNew.setTitle(player.getTitle());
        }
        if (player.getRace() != null) {
            playerNew.setRace(player.getRace());
        }
        if (player.getProfession() != null) {
            playerNew.setProfession(player.getProfession());
        }
        if (player.getBirthday() != null) {
            playerNew.setBirthday(player.getBirthday());
        }
        if (player.getBanned() != null) {
            playerNew.setBanned(player.getBanned());
        } else {
            playerNew.setBanned(false);
        }
        if (player.getExperience() != null) {
            playerNew.setExperience(player.getExperience());
        }
        listOfPlayers.removeIf(playerStream -> Objects.equals(playerStream.getId(), player.getId()));
        listOfPlayers.add(playerNew);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(listOfPlayers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return playerNew;
    }

    public Player getPlayerById(Long id) {
        List<Player> listOfPlayers = getAllPlayers(null);
        return listOfPlayers.stream()
                .filter(player -> Objects.equals(player.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
