package tilo;

import tilo.command.Command;
import tilo.exception.TiloException;
import tilo.parser.Parser;
import tilo.task.*;
import tilo.ui.Ui;

public class Tilo {
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;

    public Tilo() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
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
    }

    public static void main(String[] args) {
        Tilo tilo = new Tilo();
        tilo.run();
    }
}
