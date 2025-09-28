package tilo.ui;

import java.util.List;
import java.util.Scanner;
import tilo.task.Task;

/**
 * Handles all user interface interactions for the Tilo application.
 * Manages input/output operations and message formatting.
 */
public class Ui {
    private static final String INDENT = "\t";
    private static final String BORDER = "----------------------------------------";
    private static final String LOGO = INDENT + "___________.___.____     ________\n"
            + INDENT + "\\__    ___/|   |    |   \\_____  \\\n"
            + INDENT + "   |    |  |   |    |    /   |   \\\n"
            + INDENT + "   |    |  |   |    |___/    |    \\\n"
            + INDENT + "   |____|  |___|_______ \\_______  /\n"
            + INDENT + "                       \\/       \\/";
    private static final String WELCOME_MESSAGE = "Hello! I'm Tilo\n" + INDENT + "What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String NO_TASKS_MESSAGE = "No tasks to list.";

    // Message templates
    private static final String TASK_ADDED_TEMPLATE = "Got it. I've added this task:";
    private static final String TASK_DELETED_TEMPLATE = "Got it. I've deleted this task:";
    private static final String TASK_COUNT_TEMPLATE = "Now you have %d task%s in the list";
    private static final String TASK_MARKED_DONE_TEMPLATE = "Nice! I've marked this task as done:";
    private static final String TASK_UNMARKED_TEMPLATE = "OK, I've marked this task as not done yet:";

    private final Scanner scanner;

    /**
     * Creates a new Ui instance and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return the user input string, trimmed of whitespace
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a decorative border.
     */
    public void showBorder() {
        printIndented(BORDER);
    }

    /**
     * Shows the welcome message and logo when the application starts.
     */
    public void showWelcome() {
        System.out.println(LOGO);
        printIndented(WELCOME_MESSAGE);
        showBorder();
    }

    /**
     * Shows the goodbye message when the application exits.
     */
    public void showGoodbye() {
        printIndented(GOODBYE_MESSAGE);
        showBorder();
    }

    /**
     * Displays a general message to the user.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        if (message != null && !message.isEmpty()) {
            printIndented(message);
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage the error message to display
     */
    public void showError(String errorMessage) {
        if (errorMessage != null && !errorMessage.isEmpty()) {
            printIndented("Error: " + errorMessage);
        }
    }

    /**
     * Shows confirmation when a task is added.
     *
     * @param task the added task
     * @param taskCount the current number of tasks
     */
    public void showTaskAdded(Task task, int taskCount) {
        printIndented(TASK_ADDED_TEMPLATE);
        printIndented(" " + task);
        printTaskCount(taskCount);
    }

    /**
     * Shows confirmation when a task is deleted.
     *
     * @param task the deleted task
     * @param taskCount the current number of tasks
     */
    public void showTaskDeleted(Task task, int taskCount) {
        printIndented(TASK_DELETED_TEMPLATE);
        printIndented(" " + task);
        printTaskCount(taskCount);
    }

    /**
     * Displays the complete list of tasks.
     *
     * @param tasks the list of tasks to display
     */
    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            printIndented(NO_TASKS_MESSAGE);
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            printIndented((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Shows confirmation when a task is marked as done.
     *
     * @param task the marked task
     */
    public void showTaskMarked(Task task) {
        printIndented(TASK_MARKED_DONE_TEMPLATE);
        printIndented(" " + task);
    }

    /**
     * Shows confirmation when a task is unmarked.
     *
     * @param task the unmarked task
     */
    public void showTaskUnmarked(Task task) {
        printIndented(TASK_UNMARKED_TEMPLATE);
        printIndented(" " + task);
    }

    /**
     * Displays the results of a task search.
     *
     * @param tasks the list of matching tasks
     */
    public void showMatchingTasks(List<Task> tasks) {
        printIndented("Here are the matching tasks in your list:");
        showTaskList(tasks);
    }

    /**
     * Prints a line with standard indentation.
     *
     * @param line the line to print
     */
    private void printIndented(String line) {
        System.out.println(INDENT + line);
    }

    /**
     * Prints the current task count with proper pluralization.
     *
     * @param taskCount the number of tasks
     */
    private void printTaskCount(int taskCount) {
        String plural = taskCount == 1 ? "" : "s";
        System.out.println(INDENT + String.format(TASK_COUNT_TEMPLATE, taskCount, plural));
    }
}