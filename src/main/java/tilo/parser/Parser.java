package tilo.parser;

import tilo.command.*;
import tilo.exception.TiloException;

public class Parser {
    public Command parse(String userInput) throws TiloException {
        ParsedInput parsed = parseInput(userInput);

        return createCommand(parsed.commandWord, parsed.arguments);
    }

    private ParsedInput parseInput(String userInput) throws TiloException {
        if (userInput == null || userInput.trim().isEmpty()) {
            throw TiloException.emptyCommand();
        }

        String[] words = userInput.trim().split("\\s+", 2);
        String commandWord = words[0].toLowerCase();
        String arguments = words.length > 1 ? words[1].trim() : "";

        return new ParsedInput(commandWord, arguments);
    }

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

    private static class ParsedInput {
        final String commandWord;
        final String arguments;

        ParsedInput(String commandWord, String arguments) {
            this.commandWord = commandWord;
            this.arguments = arguments;
        }
    }
}