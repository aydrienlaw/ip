package tilo.command;

import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.exception.TiloException;

/**
 * Abstract base class for all commands in the Tilo application.
 * Defines the common interface that all commands must implement.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list and UI.
     *
     * @param taskList the task list to operate on
     * @param ui the UI for user interaction
     * @throws TiloException if command execution fails
     */
    public abstract void execute(TaskList taskList, Ui ui) throws TiloException;

    /**
     * Determines if the application should continue running after this command.
     *
     * @return true if the application should keep running, false to exit
     */
    public boolean isRunning() {
        return true;
    }
}