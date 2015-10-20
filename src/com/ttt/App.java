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

import com.ttt.form.MainForm;
import com.ttt.form.QuestionForm;
import com.ttt.form.QuizManagerForm;
import com.ttt.question.Question;
import com.ttt.quiz.Quiz;
import com.ttt.quiz.QuizManager;
import com.ttt.util.StringUtils;
import com.ttt.util.WindowUtils;

import javax.swing.*;
import java.util.List;

public class App {

    /**
     * App name.
     */
    public static final String APP_NAME = "Test The Teacher";

    /**
     * This instance of the current quiz.
     */
    private Quiz currentQuiz = null;

    /**
     * Main quiz instance.
     */
    private Quiz mainQuiz = new Quiz();

    /**
     * Quiz manager instance.
     */
    private QuizManager quizManager = new QuizManager();

    /**
     * The current question.
     */
    private int currentQuestion = 0;

    /**
     * The current score. Number of correct answers.
     */
    private int score = 0;

    /**
     * The main form instance.
     */
    private MainForm mainForm;

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
        // Application initializing, show a message
        System.out.println("Initializing " + App.APP_NAME + "...");

        // Use the system's GUI look and feel, not the Java one
        WindowUtils.useNativeLookAndFeel();

        // Add all questions
        setUpMainQuiz();

        // Initialize the main form
        this.mainForm = new MainForm(this, false);

        // Create and open the question form
        this.questionForm = new QuestionForm(this, false);

        // Set the current quiz
        setCurrentQuiz(getMainQuiz());

        // Show the first question in the frame
        this.questionForm.setQuestion(getCurrentQuestion());

        // Application initialized, show a message
        System.out.println("Initialized " + App.APP_NAME + " successfully! Cave Johnson here!");
    }

    /**
     * Start the application, after it has been initialized.
     */
    public void start() {
        // Start the application
        System.out.println("Starting " + App.APP_NAME + "...");

        // hide questionForm
        this.questionForm.setVisible(false);

        // Show the main form
        this.mainForm.setVisible(true);

        // TODO: Test for the question manager form
        QuizManagerForm form = new QuizManagerForm(this, true);
    }

    /**
     * Set up the main quiz and add it's questions.
     */
    public void setUpMainQuiz() {
        // Set the quiz name
        // TODO: Move this line to a better place, and think of a better name!
        this.mainQuiz.setName("Main quiz");

        // Clear the main quiz
        this.mainQuiz.clearQuestions();

        // Add the first question
        this.mainQuiz.addQuestion(new Question(
                "<b>Welk volgende types zijn geldig in Java?</b>",
                new String[] {
                        "int, string, Double, Boolean",
                        "boolean, int, String, double",
                        "String, Int, double, boolean",
                        "double, boolean, String, Int"
                },
                1));
        this.mainQuiz.addQuestion(new Question(
                "<b>Wat is de waarde van <i>a</i>?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "int a = (int) 19.6",
                new String[] {
                        "19",
                        "19.6",
                        "20",
                        "18"
                },
                0));
        this.mainQuiz.addQuestion(new Question(
                "<b>Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "(int) (24.768 * 100) / 100",
                new String[] {
                        "25",
                        "24.76",
                        "24",
                        "24.7"
                },
                2));
        this.mainQuiz.addQuestion(new Question(
                "<b>Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "42.5 % 2.1",
                new String[] {
                        "0.5",
                        "1.0",
                        "1.25",
                        "<i>Deze expressie geeft een error</i>"
                },
                0));
        this.mainQuiz.addQuestion(new Question(
                "<b>Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "for(int i = 1; i < 10; i += 2) {<br />" +
                        StringUtils.indentHtmlSpaces(8) + "System.out.printf(\"%1d\", i);<br />" +
                        StringUtils.indentHtmlSpaces(4) + "}",
                new String[] {
                        "123456789",
                        "13579",
                        "2468",
                        "<i>Geen van de bovenstaande</i>"
                },
                1));
    }

    /**
     * Get the current quiz.
     *
     * @return Current quiz, or null.
     */
    public Quiz getCurrentQuiz() {
        return this.currentQuiz;
    }

    /**
     * Set the current quiz.
     *
     * @param current The current quiz.
     */
    public void setCurrentQuiz(Quiz current) {
        // Set the current quiz
        this.currentQuiz = current;

        // Set the current quiz in the form
        this.questionForm.setQuiz(current);
    }

    /**
     * Get the current score.
     *
     * @return Score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the current score.
     *
     * @param score Current score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Increase the score by one.
     */
    public void addScore(){
        this.score++;
    }

    /**
     * Get the main quiz instance.
     *
     * @return Main quiz.
     */
    public Quiz getMainQuiz() {
        return this.mainQuiz;
    }

    /**
     * Get the quiz manager instance.
     *
     * @return Quiz manager instance.
     */
    public QuizManager getQuizManager() {
        return this.quizManager;
    }

    /**
     * Get the questions of the current quiz.
     *
     * @return The list of questions.
     */
    public List<Question> getQuestions() {
        return getCurrentQuiz().getQuestions();
    }

    /**
     * Get the number of questions in the current quiz.
     *
     * @return Questions in the current quiz.
     */
    public int getQuestionCount() {
        return getQuestions().size();
    }

    /**
     * Get a specific question by index of the current quiz.
     *
     * @param i The index of the question.
     * @return Question.
     */
    public Question getQuestion(int i) {
        return getQuestions().get(i);
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
     * Get the current question of the current quiz.
     *
     * @return Current question of the current quiz.
     */
    public Question getCurrentQuestion() {
        return getQuestion(getCurrentQuestionId());
    }

    /**
     * Load and show the next question.
     */
    public void nextQuestion() {
        // Check whether the current question is the last
        if(currentQuestion < getQuestionCount() - 1) {
            // Increase the current question index by one
            currentQuestion++;

            // Show the next question in the frame
            this.questionForm.setQuestion(getCurrentQuestion());

        } else {
            // Show the current score to the player
            JOptionPane.showMessageDialog(questionForm, "Uw score is: " + getScore());

            // Show the main form, hide the quiz form
            showMainForm();
        }
    }

    /**
     * This method is called when a question is selected.
     *
     * @param i The answer index.
     */
    public void selectedAnswer(int i) {
        // Print the answer in the console
        System.out.println("Answered: " + getCurrentQuestion().getAnswer(i, false));

        // Increase the score if answer is correct
        if(getCurrentQuestion().isCorrectAnswerIndex(i)) {
            addScore();
        }

        // Show the current score
        System.out.println("Current score: " + getScore());

        // Go to next question
        nextQuestion();
    }

    /**
     * Show the question form.
     */
    public void showQuestionForm() {
        // Show a status message
        System.out.println("Showing the question form...");

        // Show the question form, hide the others
        questionForm.setVisible(true);
        mainForm.setVisible(false);
    }

    /**
     * Show the main form.
     */
    public void showMainForm(){
        // Show a status message
        System.out.println("Showing the main form...");

        // Show the main form, hide the others
        questionForm.setVisible(false);
        mainForm.setVisible(true);
    }
}