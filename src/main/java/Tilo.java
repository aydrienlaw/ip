import java.util.Scanner;

public class Tilo {
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

    public static void echo(String line) {
        System.out.println(INDENT + line);
    }

    public static void main(String[] args) {
        sayHi();
        String userInput = "";
        Scanner in = new Scanner(System.in);

        while (!userInput.equals("bye")) {
            echo(userInput);
            userInput = readUserInput(in);
        }
        sayGoodbye();
    }
}
