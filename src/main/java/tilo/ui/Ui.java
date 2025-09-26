package tilo.ui;

import java.util.List;
import java.util.Scanner;
import tilo.task.Task;

public class Ui {
    private static final String INDENT = "\t";
    private static final String BORDER = "----------------------------------------";
    private static final String LOGO = INDENT + "___________.___.____     ________\n"
            + INDENT + "\\__    ___/|   |    |   \\_____  \\\n"
            + INDENT + "   |    |  |   |    |    /   |   \\\n"
            + INDENT + "   |    |  |   |    |___/    |    \\\n"
            + INDENT + "   |____|  |___|_______ \\_______  /\n"
            + INDENT + "                       \\/       \\/";
    private static final String WELCOME_MESSAGE = "Hello! I'm Tilo\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String NO_TASKS_MESSAGE = "No tasks to list.";

    // Message templates
    private static final String TASK_ADDED_TEMPLATE = "Got it. I've added this task:";
    private static final String TASK_DELETED_TEMPLATE = "Got it. I've deleted this task:";
    private static final String TASK_COUNT_TEMPLATE = "Now you have %d task%s in the list";
    private static final String TASK_MARKED_DONE_TEMPLATE = "Nice! I've marked this task as done:";
    private static final String TASK_UNMARKED_TEMPLATE = "OK, I've marked this task as not done yet:";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showBorder() {
        printIndented(BORDER);
    }

    public void showWelcome() {
        System.out.println(LOGO);
        printIndented(WELCOME_MESSAGE);
        showBorder();
    }

    public void showGoodbye() {
        printIndented(GOODBYE_MESSAGE);
        showBorder();
    }

    public void showMessage(String message) {
        if (message != null && !message.isEmpty()) {
            printIndented(message);
        }
    }

    public void showError(String errorMessage) {
        if (errorMessage != null && !errorMessage.isEmpty()) {
            printIndented("Error: " + errorMessage);
        }
    }

    public void showTaskAdded(Task task, int taskCount) {
        printIndented(TASK_ADDED_TEMPLATE);
        printIndented(" " + task);
        printTaskCount(taskCount);
    }

    public void showTaskDeleted(Task task, int taskCount) {
        printIndented(TASK_DELETED_TEMPLATE);
        printIndented(" " + task);
        printTaskCount(taskCount);
    }

    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            printIndented(NO_TASKS_MESSAGE);
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            printIndented((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showTaskMarked(Task task) {
        printIndented(TASK_MARKED_DONE_TEMPLATE);
        printIndented(" " + task);
    }

    public void showTaskUnmarked(Task task) {
        printIndented(TASK_UNMARKED_TEMPLATE);
        printIndented(" " + task);
    }

    private void printIndented(String line) {
        System.out.println(INDENT + line);
    }

    private void printTaskCount(int taskCount) {
        String plural = taskCount == 1 ? "" : "s";
        System.out.println(INDENT + String.format(TASK_COUNT_TEMPLATE, taskCount, plural));
    }
}
