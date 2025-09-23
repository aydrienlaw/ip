package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.exception.TiloException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui) throws TiloException;
    public boolean isRunning() {
        return true;
    }
}
