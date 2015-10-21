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

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import com.timvisee.yamlwrapper.configuration.YamlConfiguration;
import com.ttt.form.MainForm;
import com.ttt.form.QuestionForm;
import com.ttt.form.QuizManagerForm;
import com.ttt.question.Question;
import com.ttt.quiz.Quiz;
import com.ttt.quiz.QuizManager;
import com.ttt.util.DirectoryUtils;
import com.ttt.util.StringUtils;
import com.ttt.util.WindowUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
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
     * Set whether the user completed the quiz.
     */
    private boolean completedQuiz = false;

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
    public void resetQuiz (){
        currentQuestion = 0;
        score = 0;

        // Update the question
        this.questionForm.setQuestion(getCurrentQuestion());
    }

    /**
     * Initialize the app.
     */
    public void init() {
        // Application initializing, show a message
        System.out.println("Initializing " + App.APP_NAME + "...");

        // Use the system's GUI look and feel, not the Java one
        WindowUtils.useNativeLookAndFeel();

        // Load the data
        load();

        // Add all questions
        setupMainQuiz();

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

        // Show the main form
        showMainForm();
    }

    /**
     * Set up the main quiz and add it's questions.
     */
    public void setupMainQuiz() {
        // Set the quiz name
        // TODO: Move this line to a better place, and think of a better name!
        this.mainQuiz.setName("Main quiz");

        // Clear the main quiz
        this.mainQuiz.clearQuestions();

        // Add the first question
        this.mainQuiz.addQuestion(new Question(
                "<b>1. Welk volgende types zijn geldig in Java?</b>",
                new String[]{
                        "int, string, Double, Boolean",
                        "boolean, int, String, double",
                        "String, Int, double, boolean",
                        "double, boolean, String, Int"
                },
                1));
        this.mainQuiz.addQuestion(new Question(
                "<b>2. Wat is de waarde van <i>a</i>?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "int a = (int) 19.6",
                new String[]{
                        "19",
                        "19.6",
                        "20",
                        "18"
                },
                0));
        this.mainQuiz.addQuestion(new Question(
                "<b>3. Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "(int) (24.768 * 100) / 100",
                new String[]{
                        "25",
                        "24.76",
                        "24",
                        "24.7"
                },
                2));
        this.mainQuiz.addQuestion(new Question(
                "<b>4. Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "42.5 % 2.1",
                new String[]{
                        "0.5",
                        "1.0",
                        "1.25",
                        "<i>Deze expressie geeft een error</i>"
                },
                0));
        this.mainQuiz.addQuestion(new Question(
                "<b>5. Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "for(int i = 1; i < 10; i += 2) {<br />" +
                        StringUtils.indentHtmlSpaces(8) + "System.out.printf(\"%1d\", i);<br />" +
                        StringUtils.indentHtmlSpaces(4) + "}",
                new String[]{
                        "123456789",
                        "13579",
                        "2468",
                        "<i>Geen van de bovenstaande</i>"
                },
                1));
        this.mainQuiz.addQuestion(new Question(
                "<b>Wat is de uitkomst van de som: " + StringUtils.createHtmlFraction(1, 5) + " x " + StringUtils.createHtmlFraction(1, 3) + " x " + StringUtils.createHtmlFraction(1, 4) + "? </b><br />",
                new String[]{
                        StringUtils.createHtmlFraction(3, 60),
                        "60",
                        StringUtils.createHtmlFraction(47,60),
                        StringUtils.createHtmlFraction(1, 60),
                },
                3));
        this.mainQuiz.addQuestion(new Question(
                "<b>7. Welk van de volgende declaraties is juist? </b><br />",
                new String[]{
                        "boolean b=TRUE",
                        "byte b =255",
                        "String s =\"null\"",
                        "int i=new Interger(\"56')",
                },
                2));
        this.mainQuiz.addQuestion(new Question(
                "<b>8. Wat komt hier uit? </b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "import java.util.*;<br />" +
                        StringUtils.indentHtmlSpaces(4) + "public class Primes {<br />" +
                        StringUtils.indentHtmlSpaces(8) + "public static void main(String[] args) {<br />" +
                        StringUtils.indentHtmlSpaces(12) + "List p = new ArrayList();<br />" +
                        StringUtils.indentHtmlSpaces(12) + "p.add(7)<br />" +
                        StringUtils.indentHtmlSpaces(12) + "p.add(2)<br />" +
                        StringUtils.indentHtmlSpaces(12) + "p.add(5)<br />" +
                        StringUtils.indentHtmlSpaces(12) + "p.add(2)<br />" +
                        StringUtils.indentHtmlSpaces(12) + "p.sort()<br />" +
                        StringUtils.indentHtmlSpaces(12) + "System.out.println(p);<br />" +
                        StringUtils.indentHtmlSpaces(8) + "}<br />" +
                        StringUtils.indentHtmlSpaces(4) + "}",
                new String[]{
                        "[2, 2, 5, 7]",
                        "[7, 2, 5, 2]",
                        "[7, 5, 2, 2]",
                        "<i>Het systeem geeft error aan</i>",

                },
                3));
        this.mainQuiz.addQuestion(new Question(
                "<b>9. Welk antwoord is goed? </b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "int []a = {1,2,3,4,5,6};<br />" +
                        StringUtils.indentHtmlSpaces(4) + "int i = a.length - 1;<br />" + "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "while(i>=0){<br />" +
                        StringUtils.indentHtmlSpaces(8) + "System.out.print(a[i]);#sthash.NhjUgVjX.dpuf<br />" +
                        StringUtils.indentHtmlSpaces(8) + "i--;<br />" +
                        StringUtils.indentHtmlSpaces(4) + "}<br />",
                new String[]{
                        "654321",
                        "65432",
                        "123456",
                        "12345",

                },
                0));
        this.mainQuiz.addQuestion(new Question(
                "<b>10.  </b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "int []a = {1,2,3,4,5,6};<br />" +
                        StringUtils.indentHtmlSpaces(4) + "int i = a.length - 1;<br />" + "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "while(i>=0){<br />" +
                        StringUtils.indentHtmlSpaces(8) + "System.out.print(a[i]);#sthash.NhjUgVjX.dpuf<br />" +
                        StringUtils.indentHtmlSpaces(8) + "i--;<br />" +
                        StringUtils.indentHtmlSpaces(4) + "}<br />",
                new String[]{
                        "654321",
                        "65432",
                        "123456",
                        "12345",

                },
                0));
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
    public void addScore() {
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
        if (currentQuestion < getQuestionCount() - 1) {
            // Increase the current question index by one
            currentQuestion++;

            // Show the next question in the frame
            this.questionForm.setQuestion(getCurrentQuestion());

        } else {
            double score = getScore();
            double total = getQuestionCount();
            int result = (int) (score / total * 100.0);

            // Show the current score to the player
            JOptionPane.showMessageDialog(questionForm, "Uw score is: " + result + "%");

            // Check whether the user has completed the quiz
            if(!hasCompletedQuiz()) {
                if(result >= 55) {
                    // Set the completed flag
                    this.completedQuiz = true;

                    // Save the data
                    save();

                    // Show a status message
                    JOptionPane.showMessageDialog(questionForm, "Je hebt een voldoende gehaald. Je kan nu een eigen quiz maken.", "Voldoende gehaald", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(questionForm, "U heeft een onvoldoende behaald. U dient een voldoende te halen om uw eigen quiz te maken.", "Onvoldoende gehaald", JOptionPane.INFORMATION_MESSAGE);
                }
            }

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
        System.out.println("Antwoord: " + getCurrentQuestion().getAnswer(i, false));

        // Increase the score if answer is correct
        if (getCurrentQuestion().isCorrectAnswerIndex(i)) {
            addScore();
        }

        // Show the current score
        System.out.println("Uw score: " + getScore());

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
        mainForm.setVisible(false);
        questionForm.setVisible(true);

        // Focus the question form
        questionForm.requestFocusInWindow();
    }

    /**
     * Get the main form instance.
     *
     * @return Main form.
     */
    public MainForm getMainForm() {
        return this.mainForm;
    }

    /**
     * Show the main form.
     */
    public void showMainForm() {
        // Show a status message
        System.out.println("Showing the main form...");

        // Show the main form, hide the others
        questionForm.setVisible(false);
        mainForm.setVisible(true);

        // Refresh the main form
        mainForm.refreshList();

        // Focus the question form
        mainForm.toFront();
        mainForm.repaint();
    }

    /**
     * Show the quiz manager form.
     * Save all data afterwards.
     *
     * @param create True to create a quiz.
     */
    public void showQuizManagerForm(boolean create) {
        // show QuizManagerForm, hide other
        new QuizManagerForm(this, true, create);

        // Save everything after editing questions
        save();
    }

    /**
     * Show the about form.
     */
    public void showAboutForm() {
        // show QuizManagerForm, hide other
        new AboutForm(this, true);
    }

    /**
     * Start the quiz.
     */
    public void startQuiz() {
        // Reset the current quiz
        resetQuiz();

        // Show the question form
        showQuestionForm();
    }

    /**
     * Get the data directory.
     *
     * @return Data directory.
     */
    public File getDataDirectory() {
        return new File(DirectoryUtils.getAppDataDirectory(), "TestTheTeacher");
    }

    /**
     * Get the data file.
     *
     * @return Data file.
     */
    public File getDataFile() {
        return new File(getDataDirectory(), "data.ttt");
    }

    /**
     * Save all data to a file.
     */
    public void save() {
        // Get the file to save the data to
        File dataFile = getDataFile();

        // Create a configuration file to save the data in
        YamlConfiguration configFile = new YamlConfiguration();

        // Create a section for the quiz manager and store it's data
        ConfigurationSection quizManagerSection = configFile.createSection("quizManager");
        getQuizManager().save(quizManagerSection);

        // Set the version number
        // TODO: Implement this!
        configFile.set("versionName", "0.1");
        configFile.set("versionCode", 1);

        // Save the file
        try {
            configFile.save(dataFile);
        } catch(IOException e) {
            // Show an error message
            JOptionPane.showMessageDialog(getMainForm(), "Failed to save application data!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Load all data from a file.
     */
    public void load() {
        // Get the file to save the data to
        File dataFile = getDataFile();

        // Make sure the file exists
        if(!dataFile.isFile())
            return;

        // Create a configuration file to save the data in
        YamlConfiguration configFile = YamlConfiguration.loadConfiguration(dataFile);

        // Create a section for the quiz manager and store it's data
        ConfigurationSection quizManagerSection = configFile.getSection("quizManager");
        getQuizManager().load(quizManagerSection);

        // Check whether the user had completed the quiz
        this.completedQuiz = configFile.getBoolean("completedQuiz", false);
    }

    /**
     * Check whether the user has completed the quiz.
     *
     * @return True if the user completed the quiz.
     */
    public boolean hasCompletedQuiz() {
        return this.completedQuiz;
    }
}