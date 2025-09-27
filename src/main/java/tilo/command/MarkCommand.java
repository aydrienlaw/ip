package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Task;
import tilo.exception.TiloException;

public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(String rawInput) throws TiloException {
        this.taskNumber = extractTaskNumber(rawInput) - 1;  // Convert to 0-based index
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        validateTaskList(taskList);
        Task task = taskList.markTask(taskNumber);
        ui.showTaskMarked(task);
    }

    private int extractTaskNumber(String rawInput) throws TiloException {
        if (rawInput.trim().isEmpty()) {
            throw TiloException.noTaskNumber("mark");
        }

        try {
            return Integer.parseInt(rawInput.trim());
        } catch (NumberFormatException e) {
            throw TiloException.invalidTaskNumber();
        }
    }

    private void validateTaskList(TaskList taskList) throws TiloException {
        if (taskList.isEmpty()) {
            throw TiloException.emptyTaskList("mark");
        }
    }
}