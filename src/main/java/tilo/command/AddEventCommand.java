package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Event;
import tilo.exception.TiloException;

/**
 * Command for adding a new Event task to the task list.
 * Handles parsing of event format: "event description /from start /to end"
 */
public class AddEventCommand extends Command {
    private static final String FROM_DELIMITER = " /from ";
    private static final String TO_DELIMITER = " /to ";

    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates a new AddEventCommand by parsing the raw input.
     *
     * @param rawInput the raw command input in format "description /from start /to end"
     * @throws TiloException if the input format is invalid or fields are empty
     */
    public AddEventCommand(String rawInput) throws TiloException {
        ParsedEventInput parts = parseEventInput(rawInput);
        this.description = parts.description;
        this.from = parts.from;
        this.to = parts.to;
    }

    /**
     * Executes the command by creating and adding a new Event task.
     *
     * @param taskList the task list to add the task to
     * @param ui the UI for displaying confirmation
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Event newEvent = new Event(description, from, to);
        taskList.addTask(newEvent);
        ui.showTaskAdded(newEvent, taskList.size());
    }

    /**
     * Parses the raw input into description, from, and to parts.
     *
     * @param rawInput the raw input string
     * @return ParsedEventInput containing the parsed components
     * @throws TiloException if format is invalid or fields are empty
     */
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

    /**
     * Parses and validates a single field from the input.
     *
     * @param field the raw field value
     * @param fieldName the name of the field for error messages
     * @return the trimmed and validated field value
     * @throws TiloException if the field is empty
     */
    private String parseField(String field, String fieldName) throws TiloException {
        String trimmedField = field.trim();
        if (trimmedField.isEmpty()) {
            throw TiloException.emptyField(fieldName);
        }
        return trimmedField;
    }

    /**
     * Internal data structure for holding parsed event input.
     */
    private static class ParsedEventInput {
        final String description;
        final String from;
        final String to;

        /**
         * Creates a new ParsedEventInput.
         *
         * @param description the event description
         * @param from the event start time
         * @param to the event end time
         */
        ParsedEventInput(String description, String from, String to) {
            this.description = description;
            this.from = from;
            this.to = to;
        }
    }
}