import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tilo {
    private static final int TASK_LIST_SIZE = 100;
    private static final String INDENT = "\t";
    private static final String BORDER = "----------------------------------------";
    private static final String LOGO = INDENT + "___________.___.____     ________\n"
            + INDENT + "\\__    ___/|   |    |   \\_____  \\\n"
            + INDENT + "   |    |  |   |    |    /   |   \\\n"
            + INDENT + "   |    |  |   |    |___/    |    \\\n"
            + INDENT + "   |____|  |___|_______ \\_______  /\n"
            + INDENT + "                       \\/       \\/";

    private boolean isRunning;
    private final List<Task> tasks;
    private final Scanner scanner;

    public Tilo() {
        this.tasks = new ArrayList<>(TASK_LIST_SIZE);
        this.scanner = new Scanner(System.in);
        this.isRunning = false;
    }

    private void printBorder() {
        System.out.println(INDENT + BORDER);
    }

    private void sayHi() {
        String greetMsg = INDENT + "Hello! I'm Tilo\n"
                + INDENT + "What can I do for you?";
        System.out.println(LOGO + "\n" + greetMsg);
        printBorder();
    }

    private void sayGoodbye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        printBorder();
    }

    private void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        printTaskAdded(newTask);
    }

    private void addToDo(String description) {
        Task newToDo = new ToDo(description);
        tasks.add(newToDo);
        printTaskAdded(newToDo);
    }

    private void addDeadline(String inputLine) {
        String byPrefix = " /by ";
        int byIndex = inputLine.lastIndexOf(byPrefix);

        if (byIndex == -1) {
            System.out.println(INDENT + "Deadline format should be: deadline <description> /by <date>");
            return;
        }

        String description = inputLine.substring(0, byIndex).trim();
        String by = inputLine.substring(byIndex + byPrefix.length()).trim();

        if (description.isEmpty() || by.isEmpty()) {
            System.out.println(INDENT + "Both description and deadline date are required.");
            return;
        }

        Task newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        printTaskAdded(newDeadline);
    }

    private void addEvent(String inputLine) {
        String fromPrefix = " /from ";
        String toPrefix = " /to ";
        int fromIndex = inputLine.lastIndexOf(fromPrefix);
        int toIndex = inputLine.lastIndexOf(toPrefix);

        if (fromIndex == -1 || toIndex == -1) {
            System.out.println(INDENT + "Event format should be: event <description> /from <start> /to <end>");
            return;
        }

        String description = inputLine.substring(0, fromIndex).trim();
        String from = inputLine.substring(fromIndex + fromPrefix.length(), toIndex).trim();
        String to = inputLine.substring(toIndex + toPrefix.length()).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println(INDENT + "Description, start time, and end time are all required.");
            return;
        }

        Task newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        printTaskAdded(newEvent);
    }

    private boolean isInputValid(String[] words) {
        if (words.length < 2) {
            System.out.println(INDENT + "Invalid Input.");
            return true;
        }
        if (words[1].trim().isEmpty()) {
            System.out.println(INDENT + "Invalid input.");
            return true;
        }
        return false;
    }

    private void printTaskAdded(Task task) {
        System.out.println(INDENT + "Got it. I've added this task: ");
        System.out.println(INDENT + " " + task);
        System.out.println(INDENT + "Now you have " + tasks.size() + " in the list");
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "No tasks to list.");
            return;
        }

        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println(INDENT + (i + 1) + ". " + tasks.get(i));
        }
    }

    private void markTask(Task task) {
        task.markAsDone();
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + "  " + task);
    }

    private void unmarkTask(Task task) {
        task.markAsNotDone();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "  " + task);
    }

    private void handleMarkCommand(String[] words, boolean isMarking) {
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "No tasks to " + (isMarking ? "mark" : "unmark") + ".");
            return;
        }

        try {
            int taskNum = Integer.parseInt(words[1]) - 1;
            if (taskNum < 0 || taskNum >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task task = tasks.get(taskNum);
            if (isMarking) {
                markTask(task);
            } else {
                unmarkTask(task);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(INDENT + "Please enter a valid task number.");
        }
    }

    private void processCommand(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            System.out.println(INDENT + "Please enter a command.");
            return;
        }

        String[] words = userInput.split(" ", 2);
        String command = words[0].toLowerCase();

        switch (command) {
        case "todo":
            addToDo(words[1]);
            break;
        case "deadline":
            addDeadline(words[1]);
            break;
        case "event":
            addEvent(words[1]);
            break;
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

    public void run() {
        sayHi();
        isRunning = true;

        while (isRunning) {
            String userInput = scanner.nextLine();
            printBorder();
            processCommand(userInput);
            printBorder();
        }

        sayGoodbye();
    }

    public static void main(String[] args) {
        Tilo tilo = new Tilo();
        tilo.run();
    }
}
