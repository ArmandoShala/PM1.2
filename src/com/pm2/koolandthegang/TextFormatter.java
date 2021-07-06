package com.pm2.koolandthegang;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible to handle formatting and printing in set formatting
 * @author Gruppe2 IT20ta_WIN
 * @version 06.11.2020
 */
public class TextFormatter {

    private int maxRowWidth = -1; // Default value, because a number under 0 is not possible in this context
    private int minimumOccurrenceToBeIndexed = 4;
    private Toolbox toolbox;

    /**
     * The constructor for the class. Toolbox is getting initialised withing
     */
    public TextFormatter() {
        toolbox = new Toolbox();
    }

    /**
     * Sets the maximal allowed width for the output.
     * Any value > 0 will set the default behavior.
     * @param input The desired width for the formatted output
     */
    public void modifyMaxRowWidth(int input) {
        maxRowWidth=input;
        System.out.println("Format set to " + (input <= -1 ? "default" : input));
    }

    /**
     * Prints the paragraphs out with desired max format width (aka max row width).
     * Depending on the value of set max row width, it fires method to prints with line number (default behavior) or width format
     * @param paragraphs The paragraphs to print
     */
    public void print(ArrayList<String> paragraphs) {
        if (!toolbox.isListPopulated(paragraphs)) {
            return;
        }
        if (maxRowWidth >= 0) {
            printWithFormat(maxRowWidth, paragraphs);
        } else {
            // Default with visible line numbers but no format
            printRaw(paragraphs);
        }
    }

    private void printRaw(ArrayList<String> paragraphs) {
        // Default behavior. It prints with line number and no format
        // We add +1 to the line number, because humans tend to start counting from 1, but devs start from 0
        for (int i = 0; i< paragraphs.size(); i++) {
            System.out.println((i+1) + ": " + paragraphs.get(i));
        }
    }

    private void printWithFormat(int maxRowWidth, ArrayList<String> paragraphs) {
        for (int i = 0; i < paragraphs.size(); i++) {
            // Set the paragraph to apply format
            String currentParagraph = paragraphs.get(i);
            while (currentParagraph.length() >= maxRowWidth) {
                // While the length is LONGER than allowed width
                String portionCurrentParagraph = "";
                boolean isPossibleToAdd = true;
                while (isPossibleToAdd) {
                    // While the length is SHORTER than allowed width
                    // Cut paragraph until next whitespace and append to the portion
                    portionCurrentParagraph += currentParagraph.substring(0, getNextIndexOfWhitespace(currentParagraph));
                    //
                    currentParagraph = currentParagraph.substring(getNextIndexOfWhitespace(currentParagraph));
                    if (!currentParagraph.contains(" ") || maxRowWidth <= portionCurrentParagraph.length() + currentParagraph.indexOf(" ")) {
                        // Print out the portion of the string because adding more to the portion would exceed the allowed row width (so to say "format applied")
                        isPossibleToAdd = false;
                        System.out.println(portionCurrentParagraph.trim());
                    }
                }
            }
            // Print the rest (is save under allowed width)
            System.out.print(currentParagraph.length() != 0 ? currentParagraph.trim() + "\n": "");
        }
    }

    private int getNextIndexOfWhitespace(String strToSplit) {
        int indexOfNextWhiteSpace = strToSplit.indexOf(" ");
        if (indexOfNextWhiteSpace == -1) {
            // There is no whitespace anymore, so just give the length of the string back
            return strToSplit.length();
        }
        // Increment by 1 because splits second value is excluding
        return indexOfNextWhiteSpace + 1;
    }

    /**
     * Prints Creats a list of word (with associated line numbers) that appear more than 3 times
     * To make printIndex() testable, add return type of ArrayList<String>
     * and a new local variable of same type. Add the string that gets printed to
     * new ArrayList and return it finally.
     * @param paragraphsToBuildIndexOff The paragraphs the build the index off
     */
    public void printIndex(ArrayList<String> paragraphsToBuildIndexOff) {
        // remove comment for testing purposes
        // ArrayList<String> dataForTesting= new ArrayList<>();

        if (!toolbox.isListPopulated(paragraphsToBuildIndexOff)) {
            //return;
        }

        // Get all uppercase words -> Candidates to be indexed
        List<String> potentialWordsToBeIndexed = getAllUppercaseWords(paragraphsToBuildIndexOff);

        // Filter words that appear more than minimum needed to be indexed
        Set<String> wordsToBeIndexed = getAllWordsToBeIndexed(potentialWordsToBeIndexed);

        if (wordsToBeIndexed.size() < 1) {
            Toolbox.printError("There are no words to be indexed");
            //return;
        }
        // I did a tricky here
        // Loop over all paragraphs again to actually get the line number
        // BUT add the acquired line number to a String in order to print it out later nicely with String.join(',', ...);
        for (String indexWord : wordsToBeIndexed) {
            String rowNumbers = "";
            for (int i = 0; i < paragraphsToBuildIndexOff.size(); i++) {
                if (isContain(paragraphsToBuildIndexOff.get(i), indexWord)) {
                    // Add + 1 because the user starts to count at 1 and we work with the index (start 0)
                    // Use space to split and join later on
                    rowNumbers+= String.valueOf(i + 1) + " ";
                }
            }
            // Print the result of each word with the tricky
            System.out.println(indexWord + " " + String.join(",", rowNumbers.split(" ")));

            // remove comment for testing purposes
            //dataForTesting.add(indexWord + " " + String.join(",", rowNumbers.split(" ")));

        }

        // remove comment for testing purposes
        // return dataForTesting;
    }



    private List<String> getAllUppercaseWords(ArrayList<String> arrayList) {
        ArrayList<String> potentialWordsToBeIndexed = new ArrayList<>();
        for (String paragraph : arrayList) {
            for (String word : Toolbox.filterAllSpecialCharacters(paragraph).split(" ")) {
                // Add only, if first letter is upper case
                if (Character.isUpperCase(word.codePointAt(0))) {
                    potentialWordsToBeIndexed.add(word);
                }
            }
        }
        return potentialWordsToBeIndexed;
    }
    public List<String> testGetAllUppercaseWords(ArrayList<String> arrayList){
        return getAllUppercaseWords(arrayList);
    }

    private HashSet<String> getAllWordsToBeIndexed(List<String> potentialWordsToBeIndexed) {
        HashSet<String> wordsToBeIndexed = new HashSet<>();
        for (String word : potentialWordsToBeIndexed) {
            // Add only if the word occurs more or same than limit
            if (Collections.frequency(potentialWordsToBeIndexed, word) >= minimumOccurrenceToBeIndexed) {
                wordsToBeIndexed.add(word);
            }
        }
        return wordsToBeIndexed;
    }

    public HashSet<String> testGetAllWordsToBeIndexed(List<String> potentialWordsToBeIndexed){
        return getAllWordsToBeIndexed(potentialWordsToBeIndexed);
    }


    private boolean isContain(String source, String subItem) {
        // Hint and code from mohamsch, thanks for that! :)
        // set up the pattern word boundary
        String pattern = "\\b" + subItem + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }
}
