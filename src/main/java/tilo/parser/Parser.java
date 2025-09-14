package tilo.parser;

import tilo.command.*;
import tilo.exception.TiloException;

public class Parser {
    public Command parse(String userInput) throws TiloException {
        validateUserInput(userInput);

        String[] words = userInput.trim().split(" ", 2);
        String commandWord = words[0].toLowerCase();
        String arguments = words.length > 1 ? words[1].trim() : "";

        return createCommand(commandWord, arguments);
    }

    private void validateUserInput(String userInput) throws TiloException {
        if (userInput == null || userInput.trim().isEmpty()) {
            throw TiloException.noCommand();
        }
    }

    public Command createCommand(String commandWord, String arguments) throws TiloException {
        switch (commandWord) {
        case "todo":
            return new AddToDoCommand(arguments);
        case "deadline":
            return new AddDeadlineCommand(arguments);
        case "event":
            return new AddEventCommand(arguments);
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "bye":
            return new ExitCommand();
        default:
            throw TiloException.invalidCommand();
        }
    }
}