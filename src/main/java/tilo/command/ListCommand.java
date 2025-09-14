package tilo.command;

import tilo.task.TaskList;
import tilo.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showTaskList(taskList.getAllTasks());
    }
}