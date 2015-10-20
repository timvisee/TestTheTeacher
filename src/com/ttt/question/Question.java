/**
 * TestTheTeacher.
 * Test the teacher to see whether he or she is really good enough at basing programming.
 * A project for a school of applied sciences.
 *
 * @author MingYan Li, Tim Visee
 * @copyright Copyright (c) MingYan Li & Tim Visee 2015. All rights reserved.
 * @license GNU GPL v3.0
 */

package com.ttt.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {

    /** HTML prefix for returned HTML values. */
    public static final String HTML_PREFIX = "<html>";

    /** The question. */
    private String question;
    /** List of answers. */
    private List<String> answers = new ArrayList<>();
    /** The index of the correct answer. */
    private int correctAnswer;

    /**
     * Constructor.
     */
    public Question() {
        this("<b>Vraag?</b>", new String[]{"Antwoord 1", "Antwoord 2", "Antwoord 3", "Antwoord 4"}, 0);
    }

    /**
     * Constructor.
     *
     * @param question Question.
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
     * @param html True to return as HTML.
     *
     * @return The question.
     */
    public String getQuestion(boolean html) {
        return (html ? HTML_PREFIX : "") + this.question;
    }

    /**
     * Set the question.
     *
     * @param question The question.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Get the answer by it's index.
     *
     * @param i Answer index.
     * @param html True to return as HTML.
     *
     * @return The answer.
     */
    public String getAnswer(int i, boolean html) {
        return (html ? HTML_PREFIX : "") + this.answers.get(i);
    }

    /**
     * Set the answer by it's index.
     *
     * @param i Answer index.
     * @param answer The answer.
     */
    public void setAnswer(int i, String answer) {
        this.answers.set(i, answer);
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

    /**
     * Set the correct answer index.
     *
     * @param i Correct answer index.
     */
    public void setCorrectAnswerIndex(int i) {
        this.correctAnswer = i;
    }

    /**
     * Check whether this question object equals another.
     *
     * @param other The other.
     *
     * @return True if both objects are equal, false if not.
     */
    public boolean equals(Question other) {
        // Compare the question
        if(!getQuestion(false).equals(other.getQuestion(false)))
            return false;

        // Compare the correct answer
        if(!isCorrectAnswerIndex(other.getCorrectAnswerIndex()))
            return false;

        // Compare the answers
        for(int i = 0; i < this.answers.size(); i++)
            if(!getAnswer(i, false).equals(this.getAnswer(i, false)))
                return false;

        // Question looks equal, return result
        return true;
    }

    /**
     * Get the question representation as a string.
     *
     * @return Question as a string.
     */
    public String toString() {
        return getQuestion(true);
    }
}