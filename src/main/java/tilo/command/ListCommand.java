package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showTaskList(taskList.getAllTasks());
    }
}