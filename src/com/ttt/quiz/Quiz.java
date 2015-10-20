/**
 * TestTheTeacher.
 * Test the teacher to see whether he or she is really good enough at basing programming.
 * A project for a school of applied sciences.
 *
 * @author MingYan Li, Tim Visee
 * @copyright Copyright (c) MingYan Li & Tim Visee 2015. All rights reserved.
 * @license GNU GPL v3.0
 */

package com.ttt.quiz;

import com.ttt.question.Question;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    /**
     * Quiz name.
     */
    private String name;

    /**
     * List of questions in this quiz.
     */
    private List<Question> questions = new ArrayList<>();

    /**
     * Constructor.
     */
    public Quiz() { }

    /**
     * Get the quiz name.
     *
     * @return Quiz name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the quiz name.
     *
     * @param name Quiz name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add a question to the quiz.
     *
     * @param question Question to add.
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    /**
     * Get a question by it's ID.
     *
     * @param i Question ID.
     *
     * @return The question.
     */
    public Question getQuestion(int i) {
        return this.questions.get(i);
    }

    /**
     * Get the quiz questions.
     *
     * @return Quiz questions.
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Get the number of questions in this quiz.
     *
     * @return Question count.
     */
    public int getQuestionCount() {
        return this.questions.size();
    }

    /**
     * Set the quiz questions.
     *
     * @param questions Quiz questions.
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Clear the list of questions for this quiz.
     */
    public void clearQuestions() {
        this.questions.clear();
    }

    /**
     * Check whether this quiz equals another.
     *
     * @param other The other quiz.
     *
     * @return True if the quizzes are equal, false if not.
     */
    public boolean equals(Quiz other) {
        // Compare the names
        if(!getName().equals(other.getName()))
            return false;

        // Make sure the number of questions is the same
        if(getQuestionCount() != other.getQuestionCount())
            return false;

        // Compare the questions
        for(int i = 0; i < getQuestionCount(); i++)
            if(!getQuestion(i).equals(other.getQuestion(i)))
                return false;

        // The quiz looks equal, return the result
        return true;
    }

    /**
     * Quiz to string.
     *
     * @return Quiz as a string.
     */
    public String toString() {
        return this.getName();
    }
}
