package tilo.command;

import tilo.exception.TiloException;
import tilo.task.Task;
import tilo.task.TaskList;
import tilo.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(String arguments) throws TiloException {
        this.taskNumber = parseTaskNumber(arguments) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        validateTaskList(taskList);

        Task deletedTask = taskList.getTask(taskNumber);
        taskList.deleteTask(taskNumber);
        ui.showTaskDeleted(deletedTask, taskList.size());
    }

    private int parseTaskNumber(String arguments) throws TiloException {
        if (arguments.trim().isEmpty()) {
            throw TiloException.noTaskNumber("delete");
        }

        try {
            return Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw TiloException.invalidTaskNumber();
        }
    }

    private void validateTaskList(TaskList taskList) throws TiloException {
        if (taskList.isEmpty()) {
            throw TiloException.emptyTaskList("delete");
        }
    }
}
