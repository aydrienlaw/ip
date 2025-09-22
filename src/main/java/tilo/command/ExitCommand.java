// ExitCommand.java - Command for exiting the application
package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        // No action needed - the isExit() method handles the exit logic
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}