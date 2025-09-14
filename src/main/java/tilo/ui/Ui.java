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

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showBorder() {
        System.out.println(INDENT + BORDER);
    }

    public void showWelcome() {
        String greetMsg = INDENT + "Hello! I'm Tilo\n"
                + INDENT + "What can I do for you?";
        System.out.println(LOGO + "\n" + greetMsg);
        showBorder();
    }

    public void showGoodbye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        showBorder();
    }

    public void showError(String errorMessage) {
        System.out.println(INDENT + errorMessage);
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(INDENT + "Got it. I've added this task: ");
        System.out.println(INDENT + " " + task);
        System.out.println(INDENT + "Now you have " + taskCount + " in the list");
    }

    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "No tasks to list.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + ". " + tasks.get(i));
        }
    }

    public void showTaskMarked(Task task) {
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + "  " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "  " + task);
    }
}
