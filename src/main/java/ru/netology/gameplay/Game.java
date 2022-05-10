package ru.netology.gameplay;

import lombok.*;
import ru.netology.domain.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {
    private List<Player> players = new ArrayList<>();

    public void register(Player player) {
        if (getById(player.getId()) != null) {
            throw new AlreadyExistsException("Игрок с id " + player.getId() + " уже зарегистрирован");
        }

        if (getByName(player.getName()) != null) {
            throw new AlreadyExistsException("Игрок с именем " + player.getName() + " уже зарегистрирован");
        }

        players.add(player);
    }

    public int round(String playerName1, String playerName2) {
        if (!playerName1.equals(playerName2)) {
            Player first = getByName(playerName1);
            Player second = getByName(playerName2);
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

    public Player getByName(String name) {
        for (Player player : players) {
            if (player.getName() == name) {
                return player;
            }
        }
        return null;
    }

    public Player getById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }
}
