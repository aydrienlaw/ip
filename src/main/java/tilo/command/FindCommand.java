git package tilo.command;

import tilo.exception.TiloException;
import tilo.storage.TaskList;
import tilo.task.Task;
import tilo.ui.Ui;
import java.util.List;

public class FindCommand extends Command{
    private final String keyword;

    public FindCommand(String rawInput) throws TiloException {
        this.keyword = extractKeyword(rawInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        List<Task> matchedTasks = taskList.findTasks(keyword);
        ui.showMatchingTasks(matchedTasks);
    }

    private String extractKeyword(String keywordPart) throws TiloException {
        String keyword = keywordPart.trim();
        if (keyword.isEmpty()) {
            throw TiloException.emptyTaskDescription("todo");
        }
        return keyword;
    }
}
