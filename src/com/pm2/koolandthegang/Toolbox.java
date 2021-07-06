package com.pm2.koolandthegang;

import java.util.ArrayList;

/**
 * General methods to use everywhere
 *
 * @author Gruppe2 IT20ta_WIN
 *
 * @version 06.11.2020
 */
public class Toolbox {

    /**
     * Method to convert a string into an integer
     * @param intAsString - Nummeric value disguised as string
     * @return - Returns the string as an integer
     */
    public int parseStringToInt(String intAsString) {
        int parsedIndex = -1;
        if (tryParse(intAsString)) {
            // why I had to call parseInt(...) twice: https://stackoverflow.com/a/2806564/3157317
            parsedIndex = Integer.parseInt(intAsString);
        }
        return parsedIndex;
    }

    private boolean tryParse(String intAsString) {
        //https://docs.microsoft.com/en-us/dotnet/api/system.int32.tryparse?view=netcore-3.1#System_Int32_TryParse_System_String_System_Int32__
        try {
            Integer.parseInt(intAsString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method to split the user input and to define which part is the command and which is the line number
     * @param userInput - The wish of user what the editor should do (command and line number in one string)
     * @return - Returns the input of user split into command and line number
     */
    public String[] splitInput(String userInput) {
        // Regardless, that line number is numeric, we decided to parse it later on.
        // Java doesnt know Tuples (https://stackoverflow.com/a/5303619/3157317)
        // Possible improvement is to implement a tuple ourself and give that back https://stackoverflow.com/a/521235/3157317
        // Inspiration https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/value-tuples

        // replaceAll(" +", " ") removes all whitespace but leaves 1 (needed between command and lineNr)
        String[] words = userInput.toLowerCase().replaceAll(" +", " ").trim().split(" ");
        String command = "";
        String lineNr = "-1";

        if (words.length == 3) {
            // we know, the command has 2 words and a number (eg. format fix 30)
            command = words[0] + words[1];
            lineNr = words[2];
        } else if(words.length == 2 && !tryParse(words[1])){
            // first and second parameter are strings, therefore concatenate into one (e.g. format raw)
            command = words[0] + words[1];
        } else if (words.length == 2) {
            // there are only two params, so the second param could be a number (e.g. add 1)
            command = words[0];
            lineNr = words[1];
        } else if (words.length == 1) {
            // this should be just a word and the second param is determined elsewhere (eg. add)
            command = words[0];
        }
        String[] commandAndLinenr = new String[2];
        commandAndLinenr[0] = command.trim();
        commandAndLinenr[1] = lineNr.trim();

        return commandAndLinenr;
    }

    /**
     * Method to calculate the line number
     * @param desiredLineNr - The line number which user gives through his input
     * @param isOffsetNeeded - Is it a modification or not
     * @param arrayList - Collection of paragraphs - contains the text which the user has added
     * @return - Returns the desired line number if it is within boundary, or else the last possible line number
     */
    public int calculateAllowedLineNumber(int desiredLineNr, boolean isOffsetNeeded, ArrayList<String> arrayList) {
        int offset = (isOffsetNeeded) ? 1 : 0;
        if (!checkIfIndexIsInBoundary(desiredLineNr, arrayList)){
            return arrayList.size()-offset;
        }
        return desiredLineNr-1;
    }


    /**
     * Method to check if the desired line number of user is within the size of the paragraphs collection
     * @param desiredLineNr - The desired line number of user
     * @param arrayList - Collection of paragraphs - contains the text which the user has added
     * @return - True if line number is within the size of the collection
     */
    public boolean checkIfIndexIsInBoundary(int desiredLineNr, ArrayList<String> arrayList) {
        if (desiredLineNr >= 0 && (desiredLineNr - 1) < arrayList.size()) {
            return true;
        } else if(desiredLineNr <= -1 || (desiredLineNr - 1) > arrayList.size())  {
            printError("Desired line number not in boundary. Effecting last element");
        }
        return false;
    }

    /**
     * Method to check if there are any entries to perform certain actions (e.g. del)
     * @param arrayList - Collection of paragraphs - contains the text which the user has added
     * @return - True if action is possible
     */
    public boolean isListPopulated(ArrayList<String> arrayList){
        if(arrayList.size() <= 0){
            Toolbox.printError("Command has no effect because there are no elements. Please add elements");
            return false;
        }
        return true;
    }

    /**
     * Method to print the error messages when an error appears
     * @param error - The error message
     */
    public static void printError(String error) {
        System.err.println(error);
    }

    /**
     * Method to filter out some of the special character (e.g. used to filter input)
     * @param stringToFilter - The string which is going to be filtered
     * @return - Returns the string where the special characters are replaced with a space
     */
    public static String filterSpecialCharacters(String stringToFilter) {
        /*
            Regex explanation
            Chars before comma are ignored
            Chars after comma are the allowed ones
            This combination results in: Only allow a-z AND A-Z AND 0-9 AND whitespace (after 9), BUT leave . AND ?
        */
        String regexPattern = "[^.,;:!?'\",a-zA-Z0-9äöüÄÖÜ ]";
        return stringToFilter.replaceAll(regexPattern, "");
    }

    /**
     * Method to filter out all the special character (e.g. used for index)
     * @param stringToFilter - The string which is going to be filtered
     * @return - Returns the string where all the special characters are replaced with a space
     */
    public static String filterAllSpecialCharacters(String stringToFilter) {
        /*
            Regex explanation
            Chars before comma are ignored
            Chars after comma are the allowed ones
            This combination results in: Only allow a-z AND A-Z AND 0-9 AND whitespace (after 9)
        */
        String regexPattern = "[^a-zA-Z0-9äöüÄÖÜ ]";
        return stringToFilter.replaceAll(regexPattern, "");
    }
}
