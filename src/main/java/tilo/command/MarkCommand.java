package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Task;
import tilo.exception.TiloException;

/**
 * Command for marking a task as completed.
 * Handles parsing of task numbers and validation.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a new MarkCommand by parsing the task number from raw input.
     *
     * @param rawInput the raw input containing the task number
     * @throws TiloException if the task number is invalid or empty
     */
    public MarkCommand(String rawInput) throws TiloException {
        this.taskNumber = parseTaskNumber(rawInput);
    }

    /**
     * Executes the command by marking the specified task as done.
     *
     * @param taskList the task list containing the task to mark
     * @param ui the UI for displaying confirmation
     * @throws TiloException if the task number is out of range
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        Task task = taskList.markTask(taskNumber);
        ui.showTaskMarked(task);
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