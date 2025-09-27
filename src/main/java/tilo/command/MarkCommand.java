package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.Task;
import tilo.exception.TiloException;

public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(String rawInput) throws TiloException {
        this.taskNumber = parseTaskNumber(rawInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        Task task = taskList.markTask(taskNumber);
        ui.showTaskMarked(task);
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