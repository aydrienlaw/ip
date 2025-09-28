package tilo.command;

import tilo.exception.TiloException;
import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.ToDo;

/**
 * Command for adding a new ToDo task to the task list.
 * Handles parsing of the task description and validation.
 */
public class AddToDoCommand extends Command {
    private final String description;

    /**
     * Creates a new AddToDoCommand with the specified raw input.
     *
     * @param rawInput the raw command input containing the task description
     * @throws TiloException if the description is empty
     */
    public AddToDoCommand(String rawInput) throws TiloException {
        this.description = parseDescription(rawInput);
    }

    /**
     * Executes the command by creating and adding a new ToDo task.
     *
     * @param taskList the task list to add the task to
     * @param ui the UI for displaying confirmation
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ToDo newToDo = new ToDo(description);
        taskList.addTask(newToDo);
        ui.showTaskAdded(newToDo, taskList.size());
    }

    /**
     * Parses and validates the task description from raw input.
     *
     * @param description the raw description input
     * @return the trimmed and validated description
     * @throws TiloException if the description is empty
     */
    private String parseDescription(String description) throws TiloException {
        String trimmedDescription = description.trim();
        if (description.isEmpty()) {
            throw TiloException.emptyField("description");
        }
        return trimmedDescription;
    }
}