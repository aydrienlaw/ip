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
        String line = in.nextLine();
        printBorder();
        return line;
    }

    public static void addTask(String[] tasks, int taskCount, String line) {
        System.out.println(INDENT + "added: " + line);
        tasks[taskCount] = line;
    }

    public static void listTasks(String[] tasks, int taskCount) {
        for (int i = 0; i < taskCount; i += 1) {
            System.out.println(INDENT + (i + 1) + ". " + tasks[i]);
        }
    }

    public static void main(String[] args) {
        sayHi();
        String userInput;
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[taskListSize];
        int taskCount = 0;

        do {
            userInput = readUserInput(in);
            switch (userInput) {
                case "list":
                    listTasks(tasks, taskCount);
                    break;
                case "bye":
                    break;
                case "":
                    break;
                default:
                    addTask(tasks, taskCount, userInput);
                    taskCount += 1;
                    break;
            }
        } while (!userInput.equals("bye"));
        sayGoodbye();
    }
}
