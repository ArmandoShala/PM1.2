# gruppe02-koolandthegang-projekt2-textverarbeitung

## Description

A simple text processing tool with In- & Output via console. Every input is stored as paragraph

## Getting Started
Clone the project to your hard disk and open the project in an IDE (InteliJ preferred, Eclipse should work fine too).
Main.java is the startup point. Run ```main()``` in there and you're good to write your next novella (like ```50 Shades Of IDEs```)

## Possible commands
These commands can be called, where ```n``` is a number starting from 1:

| Command  | Description |
| ------------- | ------------- |
| add [n] | Adds a new paragraph, where ```n``` is the index to do so. The next input is the actual text  |
| del [n] | Deletes a paragraph, where ```n``` is the index to do so  |
| dummy [n] | Adds a paragraph, where ```n``` is the index to do so. The text, that is going to be added, is a dummy text (Lorem ipsum)  |
| dummyfull | Adds 6 dummy paragraphs to quickly have some debug data. This does not remove any existing data  |
| replace [n] | Replaces a String, where ```n``` is the index to do so. The next inputs are one for the word to be replaced and the new word |
| format raw | Sets the format to be raw with line numbers. This is also the default and fallback, if format fix gets a invalid/impossible number |
| format fix n | Sets the format to be as wide as ```n```. If format fix gets a invalid/impossible number, format raw is fallback |
| print | Prints the paragraphs in the set format |
| index | Creats a list of word (with associated line numbers) that appear more than 3 times |
| exit | Closes the application |

* The command affects the last line, if ```n``` is not passed with the command or the number is not in the boundary of the paragraphs
* Some commands require additional input (e.g. add) to modify the paragraphs
* Common punctuation marks are allowed. Special characters will be filtered

## Version History

* 06.11.2020
    * Finished product
    
## License

This project is licensed under the MIT License
