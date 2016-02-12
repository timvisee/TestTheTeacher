/**
 * TestTheTeacher.
 * Test the teacher to see whether he or she is really good enough at basing programming.
 * A project for a school of applied sciences.
 *
 * @author MingYan Li, Tim Visee
 * @copyright Copyright (c) MingYan Li & Tim Visee 2015. All rights reserved.
 * @license GNU GPL v3.0
 */

package me.ttt;

public class Main {

    /**
     * Main method.
     * This method starts an application instance.
     *
     * @param args Startup arguments.
     */
    public static void main(String[] args) {
	    // Construct the application
        App app = new App(false);

        // Initialize the application
        app.init();

        // Start the application
        app.start();
    }
}
