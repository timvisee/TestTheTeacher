package com.ttt;

public class App {

    /** App name. */
    public static final String APP_NAME = "Test The Teacher";

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
    public void init()  {
        System.out.println("App");

        QuestionForm form = new QuestionForm();
    }
}
