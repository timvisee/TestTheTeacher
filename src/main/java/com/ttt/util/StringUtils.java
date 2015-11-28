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
        return "<font size=-2><sup>" + numerator + "</sup><i>/</i><sub>" + denominator + "</sub><font size=0>";
    }

    /**
     * Encode text as HTML.
     *
     * @param text The text.
     *
     * @return Encoded text.
     */
    public static String encodeHtml(String text) {
        // Replace the spaces
        text = text.replace(" ", "&nbsp;");

        // Replace the new lines
        text = text.replace("\n", "<br />");

        // Return the encoded text
        return text;
    }

    /**
     * Decode text from HTML.
     *
     * @param html The HTML.
     *
     * @return Decode text.
     */
    public static String decodeHtml(String html) {
        // Replace the spaces
        html = html.replace("&nbsp;", " ");

        // Replace the new lines
        html = html.replace("<br />", "\n");

        // Return the encoded text
        return html;
    }

    /**
     * Get the line separator characters of the current platform.
     *
     * @return Line separator.
     */
    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }
}
