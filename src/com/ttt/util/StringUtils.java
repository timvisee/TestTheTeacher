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
}
