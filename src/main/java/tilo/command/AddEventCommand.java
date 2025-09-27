package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Event;
import tilo.exception.TiloException;

public class AddEventCommand extends Command {
    private static final String FROM_DELIMITER = " /from ";
    private static final String TO_DELIMITER = " /to ";

    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String rawInput) throws TiloException {
        ParsedEventInput parts = parseEventInput(rawInput);
        this.description = parts.description;
        this.from = parts.from;
        this.to = parts.to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Event newEvent = new Event(description, from, to);
        taskList.addTask(newEvent);
        ui.showTaskAdded(newEvent, taskList.size());
    }

    private ParsedEventInput parseEventInput(String rawInput) throws TiloException {
        // First split by /from
        String[] fromParts = rawInput.split(FROM_DELIMITER, 2);
        if (fromParts.length != 2) {
            throw TiloException.invalidEventFormat();
        }

        // Then split the second part by /to
        String[] toParts = fromParts[1].split(TO_DELIMITER, 2);
        if (toParts.length != 2) {
            throw TiloException.invalidEventFormat();
        }

        String description = parseField(fromParts[0], "description");
        String from = parseField(toParts[0], "from");
        String to = parseField(toParts[1], "to");

        return new ParsedEventInput(description, from, to);
    }

    private String parseField(String field, String fieldName) throws TiloException {
        String trimmedField = field.trim();
        if (trimmedField.isEmpty()) {
            throw TiloException.emptyField(fieldName);
        }
        return trimmedField;
    }

    private static class ParsedEventInput {
        final String description;
        final String from;
        final String to;

        ParsedEventInput(String description, String from, String to) {
            this.description = description;
            this.from = from;
            this.to = to;
        }
    }
}