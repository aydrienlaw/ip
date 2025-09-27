package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Deadline;
import tilo.exception.TiloException;

public class AddDeadlineCommand extends Command {
    private static final String DEADLINE_DELIMITER = " /by ";

    private final String description;
    private final String by;

    public AddDeadlineCommand(String rawInput) throws TiloException {
        ParsedDeadlineInput parts = parseDeadlineInput(rawInput);
        this.description = parts.description;
        this.by = parts.by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Deadline newDeadline = new Deadline(description, by);
        taskList.addTask(newDeadline);
        ui.showTaskAdded(newDeadline, taskList.size());
    }

    private ParsedDeadlineInput parseDeadlineInput(String rawInput) throws TiloException {
        String[] parts = rawInput.split(DEADLINE_DELIMITER, 2);
        if (parts.length != 2) {
            throw TiloException.invalidDeadlineFormat();
        }
        String description = parseField(parts[0], "description");
        String by = parseField(parts[1], "by");

        return new ParsedDeadlineInput(description, by);
    }

    private String parseField(String field, String fieldName) throws TiloException {
        String trimmedField = field.trim();
        if (trimmedField.isEmpty()) {
            throw TiloException.emptyField(fieldName);
        }
        return trimmedField;
    }

    private static class ParsedDeadlineInput {
        final String description;
        final String by;

        ParsedDeadlineInput(String description, String by) {
            this.description = description;
            this.by = by;
        }
    }
}