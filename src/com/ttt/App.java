package com.ttt;

import com.ttt.util.WindowUtils;

import java.util.ArrayList;
import java.util.List;

public class App {

    /** App name. */
    public static final String APP_NAME = "Test The Teacher";

    /** List of all questions. */
    private List<Question> questions = new ArrayList<>();

    /** The current question. */
    int currentQuestion = 0;

    /**
     * Constructor.
     *
     * @param init True to initialize the application, false otherwise.
     */
    public App(boolean init) {
        // Initialize
        if(init)
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
        QuestionForm questionForm = new QuestionForm();

        // Set the question
        questionForm.setQuestion(getQuestion(getCurrentQuestion()));
    }

    /**
     * Add all questions to the questions list.
     */
    public void addQuestions() {
        // Clear the list of questions
        this.questions.clear();

        // Add the first question
        this.questions.add(new Question(
                "Antwoord B is goed",
                new String[] {
                        "Antwoord A",
                        "Antwoord B",
                        "Antwoord C",
                        "Antwoord D"
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
     *
     * @return The question.
     */
    public Question getQuestion(int i) {
        return this.questions.get(i);
    }

    /**
     * Get the current question index.
     *
     * @return The current question index.
     */
    public int getCurrentQuestion() {
        return this.currentQuestion;
    }
}