package tilo.command;

import tilo.exception.TiloException;
import tilo.storage.TaskList;
import tilo.ui.Ui;

/**
 * Command for finding tasks that contain a specific keyword.
 * Performs case-insensitive substring matching on task descriptions.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new FindCommand by parsing the keyword from raw input.
     *
     * @param rawInput the raw input containing the search keyword
     * @throws TiloException if the keyword is empty
     */
    public FindCommand(String rawInput) throws TiloException {
        this.keyword = parseKeyword(rawInput);
    }

    /**
     * Executes the command by searching for tasks containing the keyword.
     *
     * @param taskList the task list to search in
     * @param ui the UI for displaying search results
     * @throws TiloException if an error occurs during search
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws TiloException {
        TaskList matchingTasks = taskList.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks.getAllTasks());
    }

    /**
     * Parses and validates the search keyword from raw input.
     *
     * @param keywordPart the raw keyword input
     * @return the trimmed and validated keyword
     * @throws TiloException if the keyword is empty
     */
    private String parseKeyword(String keywordPart) throws TiloException {
        String keyword = keywordPart.trim();
        if (keyword.isEmpty()) {
            throw TiloException.emptyField("keyword");
        }
        return keyword;
    }
}