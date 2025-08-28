public class Tilo {
    public static final String BORDER = "----------------------------------------";
    public static final String LOGO = "___________.___.____     ________ \n"
            + "\\__    ___/|   |    |   \\_____  \\\n"
            + "   |    |  |   |    |    /   |   \\\n"
            + "   |    |  |   |    |___/    |    \\\n"
            + "   |____|  |___|_______ \\_______  /\n"
            + "                       \\/       \\/";

    public static void printBorder() {
        System.out.println(BORDER);
    }

    public static void sayHi() {
        String greetMsg = "Hello! I'm Tilo\n"
                + "What can I do for you?";
        System.out.println(LOGO + "\n" + greetMsg);
        printBorder();
    }

    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printBorder();
    }

    public static void main(String[] args) {
        sayHi();
        sayGoodbye();
    }
}
