package tilo.command;

import tilo.task.TaskList;
import tilo.ui.Ui;
import tilo.task.Task;
import tilo.exception.TiloException;

public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(String arguments) throws TiloException {
        this.taskNumber = parseTaskNumber(arguments);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        validateTaskList(taskList);

        Task task = taskList.getTask(taskNumber - 1); // Convert to 0-based index
        task.markAsDone();
        ui.showTaskMarked(task);
    }

    private int parseTaskNumber(String arguments) throws TiloException {
        if (arguments.trim().isEmpty()) {
            throw TiloException.noTaskNumber("mark");
        }

        try {
            return Integer.parseInt(arguments.trim());
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