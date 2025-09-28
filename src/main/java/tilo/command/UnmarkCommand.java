package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Task;
import tilo.exception.TiloException;

/**
 * Command for unmarking a task (marking it as not completed).
 * Handles parsing of task numbers and validation.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a new UnmarkCommand by parsing the task number from raw input.
     *
     * @param rawInput the raw input containing the task number
     * @throws TiloException if the task number is invalid or empty
     */
    public UnmarkCommand(String rawInput) throws TiloException {
        this.taskNumber = parseTaskNumber(rawInput);
    }

    /**
     * Executes the command by unmarking the specified task.
     *
     * @param taskList the task list containing the task to unmark
     * @param ui the UI for displaying confirmation
     * @throws TiloException if the task number is out of range
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        Task task = taskList.unmarkTask(taskNumber);
        ui.showTaskUnmarked(task);
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