package com.mwl.characters;

import com.mwl.environment.Room;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonsterFactory {
    private MonsterFactory(){

    }


    public static Monster createMonster(){
        Stream<String> content = null;
        try {
            content = Files.lines(Paths.get("resources/normal_monsters.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

       // Map<String, String> monsters = content.collect(Collectors.toMap());
        List<String> monsters = content.collect(Collectors.toList());
        Random rand = new Random();
        int random = rand.nextInt(monsters.size());
        String[] str = monsters.get(random).split(",");

        Monster monster = new Normal(str[0], 50, str[1].strip());

       // Monster monster = new Normal("Guru", 50, "This is a carnivore");

        return monster;
    }

}
