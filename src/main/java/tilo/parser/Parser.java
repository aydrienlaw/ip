package tilo.parser;

import tilo.command.*;
import tilo.exception.TiloException;

/**
 * Parses user input strings into Command objects.
 * Handles command word recognition and argument extraction.
 */
public class Parser {

    /**
     * Parses a user input string into a Command object.
     *
     * @param userInput the raw user input string
     * @return the appropriate Command object
     * @throws TiloException if input is invalid or command is unrecognized
     */
    public Command parse(String userInput) throws TiloException {
        ParsedInput parsed = parseInput(userInput);
        return createCommand(parsed.commandWord, parsed.arguments);
    }

    /**
     * Splits user input into command word and arguments.
     *
     * @param userInput the raw user input
     * @return ParsedInput containing command word and arguments
     * @throws TiloException if input is null or empty
     */
    private ParsedInput parseInput(String userInput) throws TiloException {
        if (userInput == null || userInput.trim().isEmpty()) {
            throw TiloException.emptyCommand();
        }

        String[] words = userInput.trim().split("\\s+", 2);
        String commandWord = words[0].toLowerCase();
        String arguments = words.length > 1 ? words[1].trim() : "";

        return new ParsedInput(commandWord, arguments);
    }

    /**
     * Creates the appropriate Command object based on the command word.
     *
     * @param commandWord the command word (e.g., "todo", "list")
     * @param arguments the command arguments
     * @return the appropriate Command object
     * @throws TiloException if command word is not recognized
     */
    public Command createCommand(String commandWord, String arguments) throws TiloException {
        switch (commandWord) {
        case "todo":
            return new AddToDoCommand(arguments);
        case "deadline":
            return new AddDeadlineCommand(arguments);
        case "event":
            return new AddEventCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        case "bye":
            return new ExitCommand();
        default:
            throw TiloException.invalidCommand();
        }
    }

    /**
     * Internal data structure for holding parsed command input.
     */
    private static class ParsedInput {
        final String commandWord;
        final String arguments;

        /**
         * Creates a new ParsedInput.
         *
         * @param commandWord the command word
         * @param arguments the command arguments
         */
        ParsedInput(String commandWord, String arguments) {
            this.commandWord = commandWord;
            this.arguments = arguments;
        }
    }
}