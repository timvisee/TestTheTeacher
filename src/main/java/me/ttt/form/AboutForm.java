package me.ttt.form;

import me.ttt.App;
import me.ttt.util.StringUtils;

import javax.swing.*;
import java.awt.*;

public class AboutForm extends JDialog {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME + " - Over";

    /** App instance. */
    private App app;

    /** The question label. */
    private JLabel mainLabel = new JLabel("<html><b>Test The Teacher v" + App.APP_VERSION_NAME + " (" + App.APP_VERSION_CODE + ")</b> <br /><br />" +
            "Developed by: <br />" +
            StringUtils.indentHtmlSpaces(4) + "MingYan Li<br />" +
            StringUtils.indentHtmlSpaces(4) + "Tim Vis&eacute;e<br /><br />" +
            "Website: http://github.com/timvisee/TestTheTeacher<br /><br />" +
            "Copyright &copy; MingYan Li, Tim Vis&eacute;e 2015.<br />" +
            "All rights reserved.");

    /**
     * Constructor.
     *
     * @param app App instance.
     * @param show True to show the frame once it has been initialized.
     */
    public AboutForm(App app, boolean show) {
        // Construct the form
        super(app.getMainForm(), FORM_TITLE, true);

        // Store the app instance
        this.app = app;

        // Create the form UI
        createUIComponents();

        // Set the window location to the system's default
        this.setLocationRelativeTo(app.getMainForm());

        // Show the form
        this.setVisible(show);
    }

    /**
     * Create all UI components for the frame.
     */

    private void createUIComponents() {
        // Create the main panel to put everything in
        JPanel mainPanel = new JPanel();

        // Set the frame layout
        mainPanel.setLayout(new GridLayout(1, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

        // Make the frame non-resizable
        this.setResizable(false);

        // Configure the placement of the questions label, and add it to the questions panel
        mainPanel.add(mainLabel);

        // Add the main panel to the frame
        add(mainPanel);

        // Pack the frame
        pack();
    }
}

