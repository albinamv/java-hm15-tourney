package ru.netology.domain;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Player {
    private int id;
    private String name;
    private int strength;
}
