package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Deadline;
import tilo.exception.TiloException;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String arguments) throws TiloException {
        String[] parsedArguments = parseDeadlineArguments(arguments);
        this.description = parsedArguments[0];
        this.by = parsedArguments[1];
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Deadline newDeadline = new Deadline(description, by);
        taskList.addTask(newDeadline);
        ui.showTaskAdded(newDeadline, taskList.size());
    }

    private String[] parseDeadlineArguments(String arguments) throws TiloException {
        String[] parts = splitByDelimiter(arguments);
        String description = extractDescription(parts[0]);
        String by = extractBy(parts[1]);

        return new String[]{description, by};
    }

    private String[] splitByDelimiter(String arguments) throws TiloException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length != 2) {
            throw TiloException.invalidDeadlineFormat();
        }
        return parts;
    }

    private String extractDescription(String descriptionPart) throws TiloException {
        String description = descriptionPart.trim();
        if (description.isEmpty()) {
            throw TiloException.emptyTaskDescription("deadline");
        }
        return description;
    }

    private String extractBy(String byPart) throws TiloException {
        String by = byPart.trim();
        if (by.isEmpty()) {
            throw TiloException.emptyDeadlineBy();
        }
        return by;
    }
}