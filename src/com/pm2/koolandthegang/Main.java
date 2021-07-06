package com.pm2.koolandthegang;

import java.util.Scanner;

/**
 * Main is to initiate the application
 * @author Gruppe2 IT20ta_WIN
 * @version 2020-11-06
 */
public class Main {

    private static TextEditor textEditor;
    private static Scanner scanner;

    /**
     * The entry point of the application
     * @param args - Array of strings of parameters passed via command line. We do not do that here.
     */
    public static void main(String[] args) {
        initEditor();
    }

    private static void initEditor() {
        scanner = new Scanner(System.in);
        textEditor = new TextEditor(scanner);
        startup();
    }

    private static void startup() {
        while (textEditor.getIsRunning()) {
            System.out.println("\nPlease enter your command.");
            textEditor.readInput(scanner.nextLine());
        }
    }
}
