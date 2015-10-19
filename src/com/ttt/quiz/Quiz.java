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
     * Get the quiz name.
     *
     * @return Quiz name.
     */
    public String getName() {
        return name;
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
}
