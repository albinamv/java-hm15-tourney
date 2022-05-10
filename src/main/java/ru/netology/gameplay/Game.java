package ru.netology.gameplay;

import lombok.*;
import ru.netology.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {
    private HashMap<String, Player> players = new HashMap<>();

    public void register(Player player) {
        if (getById(player.getId()) != null) {
            throw new AlreadyExistsException("Игрок с id " + player.getId() + " уже зарегистрирован");
        }

        if (players.containsKey(player.getName())) {
            throw new AlreadyExistsException("Игрок с именем " + player.getName() + " уже зарегистрирован");
        }

        players.put(player.getName(), player);
    }

    public int round(String playerName1, String playerName2) {
        if (!playerName1.equals(playerName2)) {
            Player first = players.get(playerName1);
            Player second = players.get(playerName2);
            if (first == null || second == null) {
                throw new NotRegisteredException("К участию в турнире допускаются только зарегистрированные игроки");
            }

            int result = first.getStrength() - second.getStrength();

            if (result > 0) {
                return 1;
            }
            if (result < 0) {
                return 2;
            }
            return 0;
        } else {
            throw new SamePlayerException("Нельзя провести поединок с одним и тем же игроком");
        }

    }

    // убран, т.к. есть замена в методах для HashMap
//    public Player getByName(String name) {
//        for (Player player : players) {
//            if (player.getName() == name) {
//                return player;
//            }
//        }
//        return null;
//    }

    public Player getById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }
}
