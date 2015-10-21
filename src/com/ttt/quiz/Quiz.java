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

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import com.ttt.question.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * Constructor.
     *
     * @param name Quiz name.
     * @param questions Questions.
     */
    public Quiz(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

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
     * Clone the list of questions.
     *
     * @return Cloned questions.
     */
    public List<Question> getQuestionsClone() {
        return createQuestionListCopy(this.questions);
    }

    /**
     * Make a copy of the quiz.
     *
     * @return Quiz copy.
     */
    public Quiz copy() {
        return new Quiz(getName(), getQuestionsClone());
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

    /**
     * Create a copy of a questions list.
     *
     * @param questions Questions to copy.
     *
     * @return Copy of questions.
     */
    public static List<Question> createQuestionListCopy(List<Question> questions) {
        // Create a new list with questions
        List<Question> clones = new ArrayList<>();

        // Clone all questions
        clones.addAll(questions.stream().map(Question::copy).collect(Collectors.toList()));

        // Return the list of cloned questions
        return clones;
    }

    /**
     * Save the question in a configuration section.
     *
     * @param section The configuration section.
     */
    public void save(ConfigurationSection section) {
        // Store the quiz name
        section.set("name", this.name);

        // Create a section for the questions
        ConfigurationSection questionsSection = section.createSection("questions");

        // Create a section for each question
        for(int i = 0; i < this.questions.size(); i++) {
            // Create a section for the question
            ConfigurationSection questionSection = questionsSection.createSection(String.valueOf(i));

            // Save the current question in the configuration section
            getQuestion(i).save(questionSection);
        }
    }
}
