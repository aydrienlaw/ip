package tilo.command;

import tilo.exception.TiloException;
import tilo.task.Task;
import tilo.storage.TaskList;
import tilo.ui.Ui;

/**
 * Command for deleting a task from the task list.
 * Handles parsing of task numbers and validation.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a new DeleteCommand by parsing the task number from arguments.
     *
     * @param arguments the command arguments containing the task number
     * @throws TiloException if the task number is invalid or empty
     */
    public DeleteCommand(String arguments) throws TiloException {
        this.taskNumber = parseTaskNumber(arguments);
    }

    /**
     * Executes the command by deleting the specified task.
     *
     * @param taskList the task list to delete the task from
     * @param ui the UI for displaying confirmation
     * @throws TiloException if the task number is out of range
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        Task deletedTask = taskList.deleteTask(taskNumber);
        ui.showTaskDeleted(deletedTask, taskList.size());
    }

    /**
     * Parses and validates the task number from raw input.
     *
     * @param input the raw task number input
     * @return the parsed task number
     * @throws TiloException if the input is empty or not a valid integer
     */
    private int parseTaskNumber(String input) throws TiloException {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw TiloException.emptyField("taskNumber");
        }

        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw TiloException.invalidTaskNumber(trimmed);
        }
    }
}