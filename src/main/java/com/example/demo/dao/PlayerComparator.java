package com.example.demo.dao;

import com.example.demo.entity.Player;
import com.example.demo.filter.PlayerOrder;

import java.util.Comparator;
import java.util.List;

public class PlayerComparator implements Comparator<Player> {
    private final PlayerOrder playerOrder;

    public PlayerComparator(PlayerOrder playerOrder) {
        this.playerOrder = playerOrder;
    }

    @Override
    public int compare(Player o1, Player o2) {
        if (playerOrder == null || playerOrder == PlayerOrder.ID) {
            return o1.getId().compareTo(o2.getId());
        } else if (playerOrder == PlayerOrder.NAME) {
            return o1.getName().compareTo(o2.getName());
        } else if (playerOrder == PlayerOrder.EXPERIENCE) {
            return o1.getExperience().compareTo(o2.getExperience());
        } else if (playerOrder == PlayerOrder.BIRTHDAY) {
            return o1.getBirthday().compareTo(o2.getBirthday());
        } else if (playerOrder == PlayerOrder.LEVEL) {
            return o1.getLevel().compareTo(o2.getLevel());
        }
        return 0;
    }

}
