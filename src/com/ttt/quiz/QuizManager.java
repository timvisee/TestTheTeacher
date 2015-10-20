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
import com.ttt.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QuizManager {

    /**
     * List of quizzes.
     */
    private List<Quiz> quizzes = new ArrayList<>();

    /**
     * Constructor.
     */
    public QuizManager() {
        // TODO: Remove this, this is debug code!
        Quiz quizOne = new Quiz();
        quizOne.setName("Eerste quiz");
        quizOne.addQuestion(new Question(
                StringUtils.encodeHtml("<b>Welk volgende types zijn geldig in Java?</b>"),
                new String[] {
                        StringUtils.encodeHtml("int, string, Double, Boolean"),
                        StringUtils.encodeHtml("boolean, int, String, double"),
                        StringUtils.encodeHtml("String, Int, double, boolean"),
                        StringUtils.encodeHtml("double, boolean, String, Int")
                },
                1));
        quizOne.addQuestion(new Question(
                StringUtils.encodeHtml("<b>Wat is de waarde van <i>a</i>?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "int a = (int) 19.6"),
                new String[] {
                        StringUtils.encodeHtml("19"),
                        StringUtils.encodeHtml("19.6"),
                        StringUtils.encodeHtml("20"),
                        StringUtils.encodeHtml("18")
                },
                0));

        Quiz quizTwo = new Quiz();
        quizTwo.setName("Een andere quiz");
        quizTwo.addQuestion(new Question(
                StringUtils.encodeHtml("<b>Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "(int) (24.768 * 100) / 100"),
                new String[] {
                        StringUtils.encodeHtml("25"),
                        StringUtils.encodeHtml("24.76"),
                        StringUtils.encodeHtml("24"),
                        StringUtils.encodeHtml("24.7")
                },
                2));

        // Add both quizzes
        this.addQuiz(quizOne);
        this.addQuiz(quizTwo);
    }

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
}
