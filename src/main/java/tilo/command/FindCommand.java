package tilo.command;

import tilo.exception.TiloException;
import tilo.storage.TaskList;
import tilo.ui.Ui;

public class FindCommand extends Command{
    private final String keyword;

    public FindCommand(String rawInput) throws TiloException {
        this.keyword = parseKeyword(rawInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        TaskList matchingTasks = taskList.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks.getAllTasks());
    }

    private String parseKeyword(String keywordPart) throws TiloException {
        String keyword = keywordPart.trim();
        if (keyword.isEmpty()) {
            throw TiloException.emptyField("keyword");
        }
        return keyword;
    }
}
