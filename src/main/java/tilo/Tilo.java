package tilo;

import tilo.command.Command;
import tilo.exception.TiloException;
import tilo.parser.Parser;
import tilo.storage.Storage;
import tilo.storage.TaskList;
import tilo.ui.Ui;

public class Tilo {
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;
    private final Storage storage;

    public Tilo() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage("./data/tilo.txt");
        this.tasks = loadTasks();
    }

    private TaskList loadTasks() {
        try {
            return new TaskList(storage.load());
        } catch (TiloException e) {
            ui.showError(e.getMessage());
            ui.showMessage("Starting with empty task list.");
            return new TaskList();
        }
    }

    private void saveTasks() {
        try {
            storage.save(tasks.getAllTasks());
        } catch (TiloException e) {
            ui.showError(e.getMessage());
            ui.showMessage("Your changes may not be persisted.");
        }
    }

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

    private void shutdown() {
        ui.showGoodbye();
        saveTasks();
    }

    public void run() {
        ui.showWelcome();
        while (processNextCommand()) {
            // Loop continues until exit command
        }
        shutdown();
    }

    public static void main(String[] args) {
        try {
            new Tilo().run();
        } catch (Exception e) {
            System.err.println("Fatal error occurred: " + e.getMessage());
            System.exit(1);
        }
    }
}
