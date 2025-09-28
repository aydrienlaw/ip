package tilo;

import tilo.command.Command;
import tilo.exception.TiloException;
import tilo.parser.Parser;
import tilo.storage.Storage;
import tilo.storage.TaskList;
import tilo.ui.Ui;

/**
 * Main application class for the Tilo task management system.
 * Coordinates the interaction between UI, storage, parsing, and task management components.
 *
 * @author Adrian Law Yu Hng
 * @version 1.0
 */
public class Tilo {
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;
    private final Storage storage;

    /**
     * Constructs a new Tilo application instance.
     * Initializes all components including UI, parser, storage, and task list.
     */
    public Tilo() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = loadStorage();
        this.tasks = loadTasks();
    }

    /**
     * Initializes the storage component with the default file path.
     *
     * @return Storage instance configured with default file path
     * @throws RuntimeException if storage initialization fails
     */
    private Storage loadStorage() {
        try {
            return new Storage("./data/tilo.txt");
        } catch (TiloException e) {
            ui.showError(e.getMessage());
            ui.showMessage("Storage initialization failed. Exiting.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads existing tasks from storage into a TaskList.
     * If loading fails, returns an empty TaskList.
     *
     * @return TaskList containing loaded tasks or empty list if loading fails
     */
    private TaskList loadTasks() {
        try {
            return new TaskList(storage.load());
        } catch (TiloException e) {
            ui.showError(e.getMessage());
            ui.showMessage("Starting with empty task list.");
            return new TaskList();
        }
    }

    /**
     * Saves the current task list to storage.
     * Shows error message if saving fails but continues execution.
     */
    private void saveTasks() {
        try {
            storage.save(tasks.getAllTasks());
        } catch (TiloException e) {
            ui.showError(e.getMessage());
            ui.showMessage("Your changes may not be persisted.");
        }
    }

    /**
     * Processes the next user command.
     * Reads user input, parses it, executes the command, and handles any errors.
     *
     * @return true if the application should continue running, false if exit command was executed
     */
    private boolean processNextCommand() {
        String userInput = ui.readCommand();
        ui.showBorder();

        try {
            Command command = parser.parse(userInput);
            command.execute(tasks, ui);
            return command.isRunning();
        } catch (TiloException e) {
            ui.showError(e.getMessage());
            return true; // Continue running on error
        } finally {
            ui.showBorder();
        }
    }

    /**
     * Performs cleanup operations before application shutdown.
     * Shows goodbye message and saves tasks to storage.
     */
    private void shutdown() {
        ui.showGoodbye();
        saveTasks();
    }

    /**
     * Runs the main application loop.
     * Shows welcome message, processes commands until exit, then performs shutdown.
     */
    public void run() {
        ui.showWelcome();
        while (processNextCommand()) {
            // Loop continues until exit command
        }
        shutdown();
    }

    /**
     * Main entry point for the Tilo application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            new Tilo().run();
        } catch (Exception e) {
            System.err.println("Fatal error occurred: " + e.getMessage());
            System.exit(1);
        }
    }
}
