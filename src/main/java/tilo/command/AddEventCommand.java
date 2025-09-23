package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Event;
import tilo.exception.TiloException;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String rawInput) throws TiloException {
        String[] parsedArguments = parseEventArguments(rawInput);
        this.description = parsedArguments[0];
        this.from = parsedArguments[1];
        this.to = parsedArguments[2];
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Event newEvent = new Event(description, from, to);
        taskList.addTask(newEvent);
        ui.showTaskAdded(newEvent, taskList.size());
    }

    private String[] parseEventArguments(String rawInput) throws TiloException {
        String[] fromParts = splitByFromDelimiter(rawInput);
        String description = extractDescription(fromParts[0]);
        String[] toParts = splitByToDelimiter(fromParts[1]);
        String from = extractFrom(toParts[0]);
        String to = extractTo(toParts[1]);

        return new String[]{description, from, to};
    }

    private String[] splitByFromDelimiter(String rawInput) throws TiloException {
        String[] parts = rawInput.split(" /from ", 2);
        if (parts.length != 2) {
            throw TiloException.invalidEventFormat();
        }
        return parts;
    }

    private String[] splitByToDelimiter(String fromParts) throws TiloException {
        String[] parts = fromParts.split(" /to ", 2);
        if (parts.length != 2) {
            throw TiloException.invalidEventFormat();
        }
        return parts;
    }

    private String extractDescription(String descriptionPart) throws TiloException {
        String description = descriptionPart.trim();
        if (description.isEmpty()) {
            throw TiloException.emptyTaskDescription("event");
        }
        return description;
    }

    private String extractFrom(String fromPart) throws TiloException {
        String from = fromPart.trim();
        if (from.isEmpty()) {
            throw TiloException.emptyEventFrom();
        }
        return from;
    }

    private String extractTo(String toPart) throws TiloException {
        String to = toPart.trim();
        if (to.isEmpty()) {
            throw TiloException.emptyEventTo();
        }
        return to;
    }
}