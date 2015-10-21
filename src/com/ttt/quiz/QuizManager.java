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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizManager {

    /**
     * List of quizzes.
     */
    private List<Quiz> quizzes = new ArrayList<>();

    /**
     * Constructor.
     */
    public QuizManager() { }

    /**
     * Add a quiz.
     *
     * @param quiz Quiz.
     */
    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }

    /**
     * Get a quiz by it's index.
     *
     * @param i Quiz index.
     *
     * @return Quiz.
     */
    public Quiz getQuiz(int i) {
        return this.quizzes.get(i);
    }

    /**
     * Get all quizzes.
     *
     * @return Quizzes.
     */
    public List<Quiz> getQuizzes() {
        return this.quizzes;
    }

    /**
     * Get a clone of all quizzes
     *
     * @return Cloned quizzes.
     */
    public List<Quiz> getQuizzesClone() {
        return createQuizListCopy(this.quizzes);
    }

    /**
     * Set the current list of quizzes.
     *
     * @param quizzes Quizzes.
     */
    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;

        // TODO: Do we need to copy the list?
    }

    /**
     * Get the number of quizzes.
     *
     * @return Number of quizzes.
     */
    public int getQuizCount() {
        return this.quizzes.size();
    }

    /**
     * Copy a quiz list.
     *
     * @param quizzes Quizzes to copy.
     *
     * @return Copy of quizzes.
     */
    public static List<Quiz> createQuizListCopy(List<Quiz> quizzes) {
        // Create a list to put the clones in
        List<Quiz> clones = new ArrayList<>();

        // Put a clone of all quizzes in the list
        clones.addAll(quizzes.stream().map(Quiz::copy).collect(Collectors.toList()));

        // Return the clones
        return clones;
    }

    /**
     * Save the quizzes in a configuration section.
     *
     * @param section The configuration section.
     */
    public void save(ConfigurationSection section) {
        // Create a section for the quizzes
        ConfigurationSection quizzesSection = section.createSection("quizzes");

        // Create a section for each question
        for(int i = 0; i < this.quizzes.size(); i++) {
            // Create a section for the quiz
            ConfigurationSection quizSection = quizzesSection.createSection(String.valueOf(i));

            // Save the current quiz in the configuration section
            getQuiz(i).save(quizSection);
        }
    }

    public void load(ConfigurationSection section) {
        // Get the configuration section for quizzes
        ConfigurationSection quizzesSection = section.getSection("quizzes");

        // Get the quizzes sections and loop through them
        for(String quizSectionKey : quizzesSection.getKeys("")) {
            // Get the quiz section
            ConfigurationSection quizSection = quizzesSection.getSection(quizSectionKey);

            // Create and add a quiz for this section
            addQuiz(new Quiz(quizSection));
        }
    }
}
