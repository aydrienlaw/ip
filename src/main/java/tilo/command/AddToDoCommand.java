package tilo.command;

import tilo.exception.TiloException;
import tilo.storage.TaskList;
import tilo.ui.Ui;
import tilo.task.ToDo;

public class AddToDoCommand extends Command {
    private final String description;

    public AddToDoCommand(String rawInput) throws TiloException {
        this.description = extractDescription(rawInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ToDo newToDo = new ToDo(description);
        taskList.addTask(newToDo);
        ui.showTaskAdded(newToDo, taskList.size());
    }

    private String extractDescription(String descriptionPart) throws TiloException {
        String description = descriptionPart.trim();
        if (description.isEmpty()) {
            throw TiloException.emptyTaskDescription("todo");
        }
        return description;
    }
}
