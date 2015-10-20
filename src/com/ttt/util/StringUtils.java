/**
 * TestTheTeacher.
 * Test the teacher to see whether he or she is really good enough at basing programming.
 * A project for a school of applied sciences.
 *
 * @author MingYan Li, Tim Visee
 * @copyright Copyright (c) MingYan Li & Tim Visee 2015. All rights reserved.
 * @license GNU GPL v3.0
 */

package com.ttt.util;

public class StringUtils {

    /**
     * Indent with HTML no break spaces.
     *
     * @param count The number of spaces.
     *
     * @return String with spaces.
     */
    public static String indentHtmlSpaces(int count) {
        // Create a string builder for the spaces
        StringBuilder builder = new StringBuilder();

        // Add the spaces
        for(int i = 0; i < count; i++)
            builder.append("&nbsp;");

        // Return the result
        return builder.toString();
    }

    /**
     * Create a fraction as HTML to render.
     *
     * @param numerator The numerator.
     * @param denominator The denominator.
     *
     * @return The fraction as HTML.
     */
    public static String createHtmlFraction(int numerator, int denominator) {
        return "<sup>" + numerator + "</sup><font size=+1>/<font size=-1><sub>" + denominator + "</sub>";"
    }
}
