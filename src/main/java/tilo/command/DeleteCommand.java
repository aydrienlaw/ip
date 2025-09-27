package tilo.command;

import tilo.exception.TiloException;
import tilo.task.Task;
import tilo.storage.TaskList;
import tilo.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(String arguments) throws TiloException {
        this.taskNumber = parseTaskNumber(arguments);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        Task deletedTask = taskList.deleteTask(taskNumber);
        ui.showTaskDeleted(deletedTask, taskList.size());
    }

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
