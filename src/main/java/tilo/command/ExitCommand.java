package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;

/**
 * Command for exiting the application.
 * Signals the main loop to terminate by returning false from isRunning().
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * No action needed as the isRunning() method handles the exit logic.
     *
     * @param taskList the task list (unused)
     * @param ui the UI (unused)
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        // No action needed - the isRunning() method handles the exit logic
    }

    /**
     * Indicates that the application should stop running.
     *
     * @return false to signal application termination
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}