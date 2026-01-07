package se.sprinto.hakan.springboot2.util;

import org.springframework.stereotype.Component;

@Component
public class TextFormatter {
    public String formatText(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        // Example formatting: convert to uppercase and trim whitespace
        return input.trim().toUpperCase();
    }

    public String toLowerCase(String input) {
        if (input == null) {
            return null;
        }
        return input.toLowerCase();
    }

    public String replaceHyphensWithSpaces(String input) {
        if (input == null) {
            return null;
        }
        return input.replace("-", " ");
    }
}
