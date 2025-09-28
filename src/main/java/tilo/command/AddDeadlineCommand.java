package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Deadline;
import tilo.exception.TiloException;

/**
 * Command for adding a new Deadline task to the task list.
 * Handles parsing of deadline format: "deadline description /by date"
 */
public class AddDeadlineCommand extends Command {
    private static final String DEADLINE_DELIMITER = " /by ";

    private final String description;
    private final String by;

    /**
     * Creates a new AddDeadlineCommand by parsing the raw input.
     *
     * @param rawInput the raw command input in format "description /by date"
     * @throws TiloException if the input format is invalid or fields are empty
     */
    public AddDeadlineCommand(String rawInput) throws TiloException {
        ParsedDeadlineInput parts = parseDeadlineInput(rawInput);
        this.description = parts.description;
        this.by = parts.by;
    }

    /**
     * Executes the command by creating and adding a new Deadline task.
     *
     * @param taskList the task list to add the task to
     * @param ui the UI for displaying confirmation
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Deadline newDeadline = new Deadline(description, by);
        taskList.addTask(newDeadline);
        ui.showTaskAdded(newDeadline, taskList.size());
    }

    /**
     * Parses the raw input into description and deadline parts.
     *
     * @param rawInput the raw input string
     * @return ParsedDeadlineInput containing the parsed components
     * @throws TiloException if format is invalid or fields are empty
     */
    private ParsedDeadlineInput parseDeadlineInput(String rawInput) throws TiloException {
        String[] parts = rawInput.split(DEADLINE_DELIMITER, 2);
        if (parts.length != 2) {
            throw TiloException.invalidDeadlineFormat();
        }
        String description = parseField(parts[0], "description");
        String by = parseField(parts[1], "by");

        return new ParsedDeadlineInput(description, by);
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
     * Internal data structure for holding parsed deadline input.
     */
    private static class ParsedDeadlineInput {
        final String description;
        final String by;

        /**
         * Creates a new ParsedDeadlineInput.
         *
         * @param description the task description
         * @param by the deadline date/time
         */
        ParsedDeadlineInput(String description, String by) {
            this.description = description;
            this.by = by;
        }
    }
}