package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Task;
import tilo.exception.TiloException;

public class UnmarkCommand extends Command {
    private final int taskNumber;

    public UnmarkCommand(String arguments) throws TiloException {
        this.taskNumber = parseTaskNumber(arguments);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        validateTaskList(taskList);

        Task task = taskList.getTask(taskNumber - 1); // Convert to 0-based index
        task.markAsNotDone();
        ui.showTaskUnmarked(task);
    }

    private int parseTaskNumber(String arguments) throws TiloException {
        if (arguments.trim().isEmpty()) {
            throw TiloException.noTaskNumber("unmark");
        }

        try {
            return Integer.parseInt(arguments.trim());
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