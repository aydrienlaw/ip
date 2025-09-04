import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tilo {
    public static final int TASK_LIST_SIZE = 100;
    public static final String INDENT = "\t";
    public static final String BORDER = "----------------------------------------";
    public static final String LOGO = INDENT + "___________.___.____     ________\n"
            + INDENT + "\\__    ___/|   |    |   \\_____  \\\n"
            + INDENT + "   |    |  |   |    |    /   |   \\\n"
            + INDENT + "   |    |  |   |    |___/    |    \\\n"
            + INDENT + "   |____|  |___|_______ \\_______  /\n"
            + INDENT + "                       \\/       \\/";

    public static boolean isRunning;
    public static List<Task> tasks = new ArrayList<>(TASK_LIST_SIZE);

    public static void printBorder() {
        System.out.println(INDENT + BORDER);
    }

    public static void sayHi() {
        String greetMsg = INDENT + "Hello! I'm Tilo\n"
                + INDENT + "What can I do for you?";
        System.out.println(LOGO + "\n" + greetMsg);
        printBorder();
    }

    public static void sayGoodbye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        printBorder();
    }

    public static void addTask(String line) {
        Task newTask = new Task(line);
        tasks.add(newTask);
        System.out.println(INDENT + "added: " + line);
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "No tasks to list.");
            return;
        }

        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println(INDENT + (i + 1) + ". " + tasks.get(i));
        }
    }

    public static void markTask(Task task) {
        task.markAsDone();
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + "  " + task);
    }

    public static void unmarkTask(Task task) {
        task.markAsNotDone();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "  " + task);
    }

    private static void handleMarkCommand(String[] words, boolean isMarking) {
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "No tasks to " + (isMarking ? "mark" : "unmark") + ".");
            return;
        }

        if (words.length < 2) {
            System.out.println(INDENT + "Please specify the task number to " + (isMarking ? "mark" : "unmark") + ".");
            return;
        }

        try {
            int taskNum = Integer.parseInt(words[1]) - 1;
            Task task = tasks.get(taskNum);
            if (isMarking) {
                markTask(task);
            } else {
                unmarkTask(task);
            }
        } catch (NumberFormatException e) {
            System.out.println(INDENT + "Please enter a valid task number.");
        }
    }

    private static void processCommand(String userInput) {
        String[] words = userInput.split(" ", 2);
        String command = words[0].toLowerCase();

        switch (command) {
        case "list":
            listTasks();
            break;
        case "mark":
            handleMarkCommand(words, true);
            break;
        case "unmark":
            handleMarkCommand(words,false);
            break;
        case "bye":
            isRunning = false;
            break;
        default:
            addTask(userInput);
            break;
        }
    }

    public static void main(String[] args) {
        sayHi();
        String userInput;
        Scanner in = new Scanner(System.in);
        isRunning = true;

        do {
            userInput = in.nextLine();
            printBorder();
            processCommand(userInput);
            printBorder();
        } while (isRunning);

        sayGoodbye();
    }
}
