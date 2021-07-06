package com.pm2.koolandthegang;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * In this class the commands are executed
 * @author Gruppe2 IT20ta_WIN
 * @version 06.11.2020
 */
public class TextModifyer {

    private String blindtext;
    private Toolbox toolbox;
    /**
     * Constructor for objects of class TextModifyer
     */
    public TextModifyer() {
        blindtext = "Lorem ipsum dolor sit amet, adipscing elitr, sed diam nonumy Soluta tempor invidunt ut Soluta et magna aliquyam erat, sed Soluta voluptua.";
        toolbox = new Toolbox();

    }

    /**
     * Sets some predefined paragraphs
     * @param paragraphs- Collection of paragraphs
     */
    public void insertDummyFull (ArrayList<String> paragraphs){
        paragraphs.add(blindtext);
        paragraphs.add("At vero eos et et justo duo dolores et ea Soluta.");
        paragraphs.add("Stet clita gubergren, no takimata sanctus est ipsum dolor sit amet.");
        paragraphs.add("Duis autem vel eum dolor in hendrerit in velit esse molestie, vel illum dolore eu nulla facilisis at vero et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.");
        paragraphs.add("Ut wisi enim ad minim Lorem, nostrud tation ullamcorper suscipit lobortis ut aliquip ex ea Lorem Lorem consequat.");
        paragraphs.add("Nam liber Lorem cum nobis option congue nihil imperdiet doming id quod mazim facer.");
        System.out.println("Added 6 example paragraphs");
    }

    /**
     * Adds a predefined paragraph to the desired paragraph line
     * @param index-Insert the desired paragraph line
     * @param paragraphs- Collection of paragraphs
     */
    public void insertDummyTextAtIndex(int index, ArrayList<String> paragraphs) {
        paragraphs.add(index, blindtext);
    }

    /**
     * Adds a paragraph to the desired line
     * @param userTextnr-Insert the desired paragraph line
     * @param scanner- Input from user
     * @param paragraphs- Collection of paragraphs
     */
    public void addParagraphAtIndex(int userTextnr, Scanner scanner, ArrayList<String> paragraphs) {
        System.out.println("Please write your text");
        String newParagraph = readAndSanitizeInput(scanner);
        if (newParagraph.length() == 0) {
            Toolbox.printError("There is nothing to add.");
            return;
        }
        paragraphs.add(userTextnr, newParagraph);
    }

    /**
     * Remove a paragraph from the desired line
     * @param userTextnr-Removes the desired paragraph line
     * @param paragraphs- Collection of paragraphs
     */
    public void deleteParagraphAtIndex(int userTextnr, ArrayList<String> paragraphs) {
        if (!toolbox.isListPopulated(paragraphs)) {
            return;
        }
        paragraphs.remove(userTextnr);
    }

    /**
     * Replaces a word in the desired paragraph
     * @param index-Replace the desired paragraph line
     * @param scanner- Input from user
     * @param paragraphs- Collection of paragraphs
     */
    public void replaceWordAtIndex(int index, Scanner scanner, ArrayList<String> paragraphs) {
        if (!toolbox.isListPopulated(paragraphs)) {
            return;
        }

        System.out.println("Word to be replaced:");
        String oldText = scanner.nextLine();
        System.out.println("New word:");
        String newText = readAndSanitizeInput(scanner);

        newText = Toolbox.filterSpecialCharacters(newText);
        String newParagraphs = paragraphs.get(index).replaceAll("\\b"+oldText+"\\b", newText);
        //TODO: tell how many words got replaces #19
        paragraphs.set(index, newParagraphs);
    }

    private String readAndSanitizeInput(Scanner scanner) {
        // Read and then filters special characters from user input before returning
        return Toolbox.filterSpecialCharacters(scanner.nextLine());
    }
}
