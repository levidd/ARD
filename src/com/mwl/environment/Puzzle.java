package com.mwl.environment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Puzzle {
    private String question;
    private String answer;
    private Set<String> wrong_answers;
    private String difficulty;

    public Puzzle(String question, String difficulty, String answer, Set<String> wrong_answers) {
        this.question = question;
        this.answer = answer;
        this.wrong_answers = wrong_answers;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Set<String> getWrong_answers() {
        return wrong_answers;
    }

    public List<String> getAllAnswers() {
        List<String> temp = new ArrayList<>(wrong_answers);
        temp.add(answer);
        return temp;
    }

    public int getDifficultyInt() {
        int result = 1;
        switch (difficulty) {
            case "easy" -> result = 1;
            case "medium" -> result = 2;
            case "hard" -> result = 3;
        }
        return result;
    }
}
