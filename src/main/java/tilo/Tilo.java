package tilo;

import tilo.command.Command;
import tilo.task.Task;
import tilo.exception.TiloException;
import tilo.parser.Parser;
import tilo.storage.Storage;
import tilo.storage.TaskList;
import tilo.ui.Ui;

import java.util.List;

public class Tilo {
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;
    private final Storage storage;

    public Tilo() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage("./data/tilo.txt");
    }

    public void run() {
        ui.showWelcome();
        try {
            List<Task> loadedTasks = storage.load();
            for (Task task : loadedTasks) {
                taskList.addTask(task);
            }
        } catch (TiloException e) {
            ui.showError(e.getMessage());
        }

        boolean isRunning = true;

        while (isRunning) {
            String userInput = ui.readCommand();
            ui.showBorder();
            try {
                Command command = parser.parse(userInput);
                command.execute(taskList, ui);
                isRunning = command.isRunning();
            } catch (TiloException e) {
                ui.showError(e.getMessage());
            }

            ui.showBorder();
        }

        ui.showGoodbye();
        try {
            storage.save(taskList.getAllTasks());
        } catch (TiloException e) {
            ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Tilo tilo = new Tilo();
        tilo.run();
    }
}
