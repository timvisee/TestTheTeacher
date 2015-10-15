/**
 * TestTheTeacher.
 * Test the teacher to see whether he or she is really good enough at basing programming.
 * A project for a school of applied sciences.
 *
 * @author MingYan Li, Tim Visee
 * @copyright Copyright (c) MingYan Li & Tim Visee 2015. All rights reserved.
 * @license GNU GPL v3.0
 */

package com.ttt;

import com.ttt.form.QuestionForm;
import com.ttt.question.Question;
import com.ttt.util.StringUtils;
import com.ttt.util.WindowUtils;

import java.util.ArrayList;
import java.util.List;

public class App {

    /**
     * App name.
     */
    public static final String APP_NAME = "Test The Teacher";

    /**
     * List of all questions.
     */
    private List<Question> questions = new ArrayList<>();

    /**
     * The current question.
     */
    private int currentQuestion = 0;

    /**
     * The question form instance.
     */
    private QuestionForm questionForm;

    /**
     * Constructor.
     *
     * @param init True to initialize the application, false otherwise.
     */
    public App(boolean init) {
        // Initialize
        if (init)
            init();
    }

    /**
     * Initialize the application.
     */
    public void init() {
        // Application has started, show a status message
        System.out.println(App.APP_NAME + " started!");

        // Use the system's GUI look and feel, not the Java one
        WindowUtils.useNativeLookAndFeel();

        // Add all questions
        addQuestions();

        // Create and open the question form
        this.questionForm = new QuestionForm(this);

        // Show the first question in the frame
        this.questionForm.setQuestion(getCurrentQuestion());
    }

    /**
     * Add all questions to the questions list.
     */
    public void addQuestions() {
        // Clear the list of questions
        this.questions.clear();

        // Add the first question
        this.questions.add(new Question(
                "<html><b>Welk volgende types zijn geldig in Java?</b>",
                new String[] {
                        "<html>int, string, Double, Boolean",
                        "<html>boolean, int, String, double",
                        "<html>String, Int, double, boolean",
                        "<html>double, boolean, String, Int"
                },
                1));
        this.questions.add(new Question(
                "<html><b>Wat is de waarde van <i>a</i>?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "int a = (int) 19.6",
                new String[] {
                        "<html>19",
                        "<html>19.6",
                        "<html>20",
                        "<html>18"
                },
                0));
        this.questions.add(new Question(
                "<html><b>Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "(int) (24.768 * 100) / 100",
                new String[] {
                        "<html>25",
                        "<html>24.76",
                        "<html>24",
                        "<html>24.7"
                },
                2));
        this.questions.add(new Question(
                "<html><b>Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "42.5 % 2.1",
                new String[] {
                        "<html>0.5",
                        "<html>1.0",
                        "<html>1.25",
                        "<html><i>Deze expressie geeft een error</i>"
                },
                0));
        this.questions.add(new Question(
                "<html><b>Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "for(int i = 1; i < 10; i += 2) {<br />" +
                        StringUtils.indentHtmlSpaces(8) + "System.out.printf(\"%1d\", i);<br />" +
                        StringUtils.indentHtmlSpaces(4) + "}",
                new String[] {
                        "<html>123456789",
                        "<html>13579",
                        "<html>2468",
                        "<html><i>Geen van de bovenstaande</i>"
                },
                1));
        this.questions.add(new Question(
                "<html><b>Vraag 6?</b>",
                new String[] {
                        "<html>Antwoord 0",
                        "<html>Antwoord 1",
                        "<html>Antwoord 2",
                        "<html>Antwoord 3"
                },
                1));
    }

    /**
     * Get the questions.
     *
     * @return The list of questions.
     */
    public List<Question> getQuestions() {
        return this.questions;
    }

    /**
     * Get a specific question by index.
     *
     * @param i The index of the question.
     * @return The question.
     */
    public Question getQuestionById(int i) {
        return this.questions.get(i);
    }

    /**
     * Get the current question index.
     *
     * @return The current question index.
     */
    public int getCurrentQuestionId() {
        return this.currentQuestion;
    }

    /**
     * Get the current question.
     *
     * @return Current question.
     */
    public Question getCurrentQuestion() {
        return getQuestionById(getCurrentQuestionId());
    }

    /**
     * Load and show the next question.
     */
    public void nextQuestion() {
        // Increase the current question index by one
        currentQuestion++;

        // Show the next question in the frame
        this.questionForm.setQuestion(getCurrentQuestion());
    }

    /**
     * This method is called when a question is selected.
     *
     * @param i The answer index.
     */
    public void selectedAnswer(int i) {
        // Print the answer in the console
        System.out.println("Answered: " + getCurrentQuestion().getAnswer(i));

        // Go to the next question if the answer is correct
        if(getCurrentQuestion().isCorrectAnswerIndex(i))
            nextQuestion();
    }
}