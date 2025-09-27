package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Task;
import tilo.exception.TiloException;

public class UnmarkCommand extends Command {
    private final int taskNumber;

    public UnmarkCommand(String rawInput) throws TiloException {
        this.taskNumber = extractTaskNumber(rawInput) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        validateTaskList(taskList);
        Task task = taskList.unmarkTask(taskNumber);
        ui.showTaskUnmarked(task);
    }

    private int extractTaskNumber(String rawInput) throws TiloException {
        if (rawInput.trim().isEmpty()) {
            throw TiloException.noTaskNumber("unmark");
        }

        try {
            return Integer.parseInt(rawInput.trim());
        } catch (NumberFormatException e) {
            throw TiloException.invalidTaskNumber();
        }
    }

    private void validateTaskList(TaskList taskList) throws TiloException {
        if (taskList.isEmpty()) {
            throw TiloException.emptyTaskList("unmark");
        }
    }
}