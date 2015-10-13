package com.ttt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {

    /** The question. */
    private String question;
    /** List of answers. */
    private List<String> answers = new ArrayList<>();
    /** The index of the correct answer. */
    private int correctAnswer;

    /**
     * Constructor.
     *
     * @param question Quetion.
     * @param answers Four answers in a list.
     * @param correctAnswer The index of the correct answer.
     */
    public Question(String question, List<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Constructor.
     *
     * @param question Question.
     * @param answers Four answers in an array.
     * @param correctAnswer The index of the correct answer.
     */
    public Question(String question, String[] answers, int correctAnswer) {
        this(question, Arrays.asList(answers), correctAnswer);
    }

    /**
     * Get the question.
     *
     * @return The question.
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * Get the answer by it's index.
     *
     * @param i Answer index.
     *
     * @return The answer.
     */
    public String getAnswer(int i) {
        return this.answers.get(i);
    }

    /**
     * Get the index of the answer that is correct.
     *
     * @return Correct answer index.
     */
    public int getCorrectAnswerIndex() {
        return this.correctAnswer;
    }

    /**
     * Check whether the specified answer index is correct.
     *
     * @param i The answer index to check.
     *
     * @return True if the answer is correct, false if not.
     */
    public boolean isCorrectAnswerIndex(int i) {
        return getCorrectAnswerIndex() == i;
    }
}