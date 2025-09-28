package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;

/**
 * Command for displaying all tasks in the task list.
 * Shows tasks in order with their completion status.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying all tasks in the list.
     *
     * @param taskList the task list to display
     * @param ui the UI for displaying the task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showTaskList(taskList.getAllTasks());
    }
}