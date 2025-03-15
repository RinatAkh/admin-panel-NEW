package com.example.demo.dao;

import com.example.demo.dto.PlayerFilter;
import com.example.demo.entity.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PlayerDbDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayerDbDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Player savePlayer(Player player) {
        String sql = "insert into player (name, title, profession, race, experience, level, untilNextLevel, birthday, banned)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?) returning id";

        Long playerId = jdbcTemplate.queryForObject(sql, Long.class,
                player.getName(),
                player.getTitle(),
                player.getProfession().name(),
                player.getRace().name(),
                player.getExperience(),
                player.getLevel(),
                player.getUntilNextLevel(),
                new Date(player.getBirthday().getTime()),
                player.getBanned()
        );
        return getPlayerById(playerId);
    }

    // Прописывать конкретные поля
    @Override
    public List<Player> getAllPlayers(PlayerFilter player) {
        StringBuilder sql = new StringBuilder("SELECT * FROM player");
        List<Object> params = new ArrayList<>();
        appendFilters(sql, params, player);

        return jdbcTemplate.query(sql.toString(), params.toArray(), new PlayerRowMapper());
    }

    private void appendFilters(StringBuilder sql, List<Object> params,  PlayerFilter playerFilter) {
        List<String> clauses = new ArrayList<>();
        if (playerFilter.getName() != null) {
            clauses.add("name ILIKE ?");
            params.add("%" + playerFilter.getName() + "%"); // Поиск по подстроке (регистронезависимый)
        }
        if (playerFilter.getTitle() != null) {
            clauses.add("title = ?");
            params.add(playerFilter.getTitle());
        }
        if (playerFilter.getRace() != null) {
            clauses.add("race = ?");
            params.add(playerFilter.getRace().name());
        }
        if (playerFilter.getProfession() != null) {
            clauses.add("profession = ?");
            params.add(playerFilter.getProfession().name());
        }
        if (playerFilter.getBanned() != null) {
            clauses.add("banned = ?");
            params.add(playerFilter.getBanned());
        }
        if (playerFilter.getMinLevel() != null) {
            clauses.add("level >= ?");
            params.add(playerFilter.getMinLevel());
        }
        if (playerFilter.getMaxLevel() != null) {
            clauses.add("level <= ?");
            params.add(playerFilter.getMaxLevel());
        }
        if (playerFilter.getMinExperience() != null) {
            clauses.add("experience >= ?");
            params.add(playerFilter.getMinExperience());
        }
        if (playerFilter.getMaxExperience() != null) {
            clauses.add("experience <= ?");
            params.add(playerFilter.getMaxExperience());
        }
        if (playerFilter.getAfter() != null) {
            clauses.add("birthday >= ?");
            params.add(new Date(playerFilter.getAfter()));
        }
        if (playerFilter.getBefore() != null) {
            clauses.add("birthday <= ?");
            params.add(new Date(playerFilter.getBefore()));
        }

        if (!clauses.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(buildClauses(clauses));
        }
    }

    private String buildClauses(List<String> clauses) {
        // реализовать склейку условий
        return String.join(" AND ", clauses);
    }

    @Override
    public int getAllPlayersCount(PlayerFilter player) {
        StringBuilder sql = new StringBuilder("select count(*) from player");
        List<Object> params = new ArrayList<>();
        appendFilters(sql, params, player);
        return jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
    }

    @Override
    public void deletePlayer(Long id) {
        String sql = "delete from player where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Player updatePlayer(Player player) {
        StringBuilder sql = new StringBuilder("UPDATE player SET ");
        List<Object> params = new ArrayList<>();
        List<String> fieldsForUpdate = new ArrayList<>();

        prepareUpdateFields(player, fieldsForUpdate, params);

        if (fieldsForUpdate.isEmpty()) {
            return getPlayerById(player.getId());
        }

        sql.append(String.join(", ", fieldsForUpdate)).append(" WHERE id = ?");
        params.add(player.getId());

        int updatedRows = jdbcTemplate.update(sql.toString(), params.toArray());

        if (updatedRows > 0) {
            return getPlayerById(player.getId());
        } else {
            throw new RuntimeException("Игрок с id " + player.getId() + " не найден");
        }
    }

    private void prepareUpdateFields(Player player, List<String> fieldsForUpdate, List<Object> params) {
        if (player.getName() != null) {
            fieldsForUpdate.add("name = ?");
            params.add(player.getName());
        }
        if (player.getTitle() != null) {
            fieldsForUpdate.add("title = ?");
            params.add(player.getTitle());
        }
        if (player.getProfession() != null) {
            fieldsForUpdate.add("profession = ?");
            params.add(player.getProfession().name()); // Enum → String
        }
        if (player.getRace() != null) {
            fieldsForUpdate.add("race = ?");
            params.add(player.getRace().name()); // Enum → String
        }
        if (player.getExperience() != null) {
            fieldsForUpdate.add("experience = ?");
            params.add(player.getExperience());
        }
        if (player.getLevel() != null) {
            fieldsForUpdate.add("level = ?");
            params.add(player.getLevel());
        }
        if (player.getUntilNextLevel() != null) {
            fieldsForUpdate.add("until_next_level = ?");
            params.add(player.getUntilNextLevel());
        }
        if (player.getBirthday() != null) {
            fieldsForUpdate.add("birthday = ?");
            params.add(new java.sql.Date(player.getBirthday().getTime())); // Date → SQL Date
        }
        if (player.getBanned() != null) {
            fieldsForUpdate.add("banned = ?");
            params.add(player.getBanned());
        }
    }

    // Почему-то не возвращает игрока
    @Override
    public Player getPlayerById(Long id) {
        String sql = "select id, name, title, profession, race, experience, level, untilNextLevel, birthday, banned from player where id = ?";
        return jdbcTemplate.queryForObject(sql, new PlayerRowMapper(), id);
    }
}
