import java.util.Scanner;

public class Tilo {
    public static final int taskListSize = 100;
    public static final String INDENT = "\t";
    public static final String BORDER = "----------------------------------------";
    public static final String LOGO = INDENT + "___________.___.____     ________\n"
            + INDENT + "\\__    ___/|   |    |   \\_____  \\\n"
            + INDENT + "   |    |  |   |    |    /   |   \\\n"
            + INDENT + "   |    |  |   |    |___/    |    \\\n"
            + INDENT + "   |____|  |___|_______ \\_______  /\n"
            + INDENT + "                       \\/       \\/";

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

    public static String readUserInput(Scanner in) {
        return in.nextLine();
    }

    public static void addTask(Task[] tasks, int taskCount, String line) {
        System.out.println(INDENT + "added: " + line);
        tasks[taskCount] = new Task(line);
    }

    public static void listTasks(Task[] tasks, int taskCount) {
        for (int i = 0; i < taskCount; i += 1) {
            Task task = tasks[i];
            System.out.println(INDENT + (i + 1) + ". [" + task.getStatusIcon() + "] " + task.description);
        }
    }

    public static void markTask(Task task) {
        task.markAsDone();
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + "  [" + task.getStatusIcon() + "] " + task.description);
    }

    public static void unmarkTask(Task task) {
        task.markAsNotDone();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "  [" + task.getStatusIcon() + "] " + task.description);
    }

    public static void main(String[] args) {
        sayHi();
        String userInput;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[taskListSize];
        int taskCount = 0;

        do {
            userInput = readUserInput(in);
            printBorder();
            String[] words = userInput.split(" ");
            switch (words[0]) {
                case "list":
                    if (taskCount > 0) {
                        listTasks(tasks, taskCount);
                    } else {
                        System.out.println(INDENT + "No tasks to list.");
                    }
                    break;
                case "bye":
                    break;
                case "":
                    break;
                case "mark" :
                    if (taskCount == 0) {
                        System.out.println(INDENT + "No tasks to mark.");
                    }
                    else if (words.length < 2) {
                        System.out.println(INDENT + "Please specify the task number to mark.");
                    } else {
                        int taskNum = Integer.parseInt(words[1]) - 1;
                        markTask(tasks[taskNum]);
                    }
                    break;
                case "unmark" :
                    if (taskCount == 0) {
                        System.out.println(INDENT + "No tasks to unmark.");
                    }
                    else if (words.length < 2) {
                        System.out.println(INDENT + "Please specify the task number to unmark.");
                    } else {
                        int taskNum = Integer.parseInt(words[1]) - 1;
                        unmarkTask(tasks[taskNum]);
                    }
                    break;
                default:
                    addTask(tasks, taskCount, userInput);
                    taskCount += 1;
                    break;
            }
            printBorder();
        } while (!userInput.equals("bye"));
        sayGoodbye();
    }
}
