package com.pm2.koolandthegang;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * In this class the commands are read and dispatched.
 * Therefore this is the main class
 * @author Gruppe2 IT20ta_WIN
 * @version 06.11.2020
 */
public class TextEditor {

    private ArrayList<String> paragraphs;

    private Scanner scanner;
    private TextModifyer textModifyer;
    private TextFormatter textFormatter;
    private Toolbox toolbox;
    private static boolean isRunning = true;

    /**
     * Constructor for objects of class TextEditor
     */
    public TextEditor(Scanner scanner) {
        // scanner and paragraphs are defined here once
        // and passed everywhere to use within the method
        this.scanner = scanner;
        paragraphs = new ArrayList<>();
        textModifyer = new TextModifyer();
        textFormatter = new TextFormatter();
        toolbox = new Toolbox();
    }

    /**
     * Reads the user input and calls the appropriate methods
     * @param - Input from User as String
     */
    public void readInput(String commandAndIndex) {
        String[] splitCommand = toolbox.splitInput(commandAndIndex);
        String command = splitCommand[0];
        int lineNr = toolbox.parseStringToInt(splitCommand[1]);
        boolean isOffsetNeeded = false;
        if (command.equals("add")) {
            lineNr = toolbox.calculateAllowedLineNumber(lineNr, isOffsetNeeded, paragraphs);
            textModifyer.addParagraphAtIndex(lineNr, scanner, paragraphs); //example with lineNr. Problem here: lineNr is a String .
        } else if (command.equals("dummy")) {
            lineNr = toolbox.calculateAllowedLineNumber(lineNr, isOffsetNeeded, paragraphs);
            textModifyer.insertDummyTextAtIndex(lineNr, paragraphs);
        } else if (command.equals("dummyfull")) {
            textModifyer.insertDummyFull(paragraphs);
        } else if (command.equals("del")) {
            isOffsetNeeded = true;
            lineNr = toolbox.calculateAllowedLineNumber(lineNr, isOffsetNeeded, paragraphs);
            textModifyer.deleteParagraphAtIndex(lineNr, paragraphs); //example with lineNr. Problem:lineNr is here an String
        } else if (command.equals("replace")) {
            isOffsetNeeded = true;
            lineNr = toolbox.calculateAllowedLineNumber(lineNr, isOffsetNeeded, paragraphs);
            textModifyer.replaceWordAtIndex(lineNr, scanner, paragraphs);
        } else if (command.equals("print")) {
            textFormatter.print(paragraphs);
        } else if (command.equals("formatraw")) {
        	textFormatter.modifyMaxRowWidth(-1);
        } else if (command.equals("formatfix")) {
            // in this case: lineNr = max width to print
        	textFormatter.modifyMaxRowWidth(lineNr);
        } else if (command.equals("index")) {
            textFormatter.printIndex(paragraphs);
        } else if (command.equals("exit")) {
            exit();
        } else {
            toolbox.printError("Unsupported command: " + command);
        }
    }


    /**
     * Method to initialize end of application
     */
    public void exit() {
        System.out.println("Thank you and have a nice day.");
        scanner.close();
        isRunning = false;
    }


    // Getters and Setters beyond this point only

    public boolean getIsRunning(){
        return isRunning;
    }

}
