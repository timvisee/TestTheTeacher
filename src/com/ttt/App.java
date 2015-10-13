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
    private int currentQuestion = 0;

    /** The question form instance. */
    private QuestionForm questionForm;

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
        this.questionForm = new QuestionForm(this);

        // Set the question
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
                "Wat is je mood vandaag?",
                new String[]{
                        "Stout",
                        "Aardig",
                        "Antwoord C",
                        "Antwoord D"
                },
                1));
        this.questions.add(new Question(
                "Questions",
                new String[] {
                        "Antwoord A",
                        "Antwoord B",
                        "Antwoord C",
                        "Antwoord D"
                },
                1));
        this.questions.add(new Question(
                "Questions",
                new String[]{
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
        currentQuestion = currentQuestion + 1;
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
    }
}