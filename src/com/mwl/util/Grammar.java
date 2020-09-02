package com.mwl.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Grammar {

    private Map<String, List<String>> grammar;

    public Grammar() {
        // default constructor
        grammar = new HashMap<>();
        readRules();
    }

    private void readRules() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/grammar/dungeon_grammar.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rules = line.split("::=");
                if (rules.length > 1) {
                    String nonterm = rules[0].strip();
                    List<String> terms = Arrays.stream(rules[1].split("[|]"))
                            .map(String::strip).collect(Collectors.toList());
                    addTo(nonterm, terms);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addTo(String key, List<String> values) {
        if (grammar.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        grammar.put(key, values);
    }

    public String generate_Sentence() {
        if (!grammar.containsKey("<sentence>")) {
            throw new IllegalArgumentException("Grammar is missing sentence non-terminal!");
        }
        return capitalize(generate("<sentence>")).replaceAll(" ,", ",") + ".";


    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private String generate(String symbol) {
        List<String> values = grammar.get(symbol);
        String value = values.get(ThreadLocalRandom.current().nextInt(values.size()));
        return Arrays.stream(value.split(" "))
                .map(e -> {
                    if (grammar.containsKey(e)) {
                        return generate(e);
                    } else {
                        return e;
                    }
                })
                .collect(Collectors.joining(" "));
    }
}
