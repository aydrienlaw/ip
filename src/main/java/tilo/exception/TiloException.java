package tilo.exception;

public class TiloException extends Exception {
    public TiloException(String message) {
        super(message);
    }

    public static TiloException invalidTaskNumber() {
        return new TiloException("Please enter a valid task number.");
    }

    public static TiloException emptyTaskList(String operation) {
        return new TiloException("No tasks to " + operation + ".");
    }
}
