package com.ttt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {

    private String question;
    private List<String> answers = new ArrayList<>();
    private int correctAnswer;

    public Question(String question, List<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public Question(String question, String[] answers, int correctAnswer) {
        this(question, Arrays.asList(answers), correctAnswer);
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer(int i) {
        return this.answers.get(i);
    }

    public int getCorrectAnswerIndex() {
        return this.correctAnswer;
    }
}