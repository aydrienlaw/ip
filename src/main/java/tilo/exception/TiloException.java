package tilo.exception;

public class TiloException extends Exception {
    public TiloException(String message) {
        super(message);
    }

    public static TiloException emptyCommand() {
        return new TiloException("Please enter a command.");
    }

    public static TiloException invalidCommand() {
        return new TiloException("I don't understand that command. Try 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', 'delete', 'find', or 'bye'.");
    }

    public static TiloException emptyField(String fieldName) {
        return new TiloException("/" + fieldName + " cannot be empty.");
    }

    public static TiloException invalidDeadlineFormat() {
        return new TiloException("Invalid deadline format. Use: deadline <description> /by <date>");
    }

    public static TiloException invalidEventFormat() {
        return new TiloException("Invalid event format. Use: event <description> /from <start> /to <end>");
    }

    public static TiloException invalidTaskNumber(String taskNumber) {
        return new TiloException("Task number must be a positive integer. Found: " + taskNumber + ".");
    }

    public static TiloException invalidTaskRange(int taskNumber, int maxTasks) {
        if (maxTasks == 0) {
            return emptyTaskList();
        }
        return new TiloException("Task number must be between 1 and " + maxTasks + ", but got " + taskNumber + ".");
    }

    public static TiloException emptyTaskList() {
        return new TiloException("No tasks available. Add some tasks firsts.");
    }

    public static TiloException invalidFilePath() {
        return new TiloException("File path cannot be null or empty.");
    }

    public static TiloException corruptedLine(String line) {
        return new TiloException("Cound corrupted data in storage file: " + line + ".");
    }

    public static TiloException saveFileError(String operation) {
        return new TiloException("Failed to perform file operation: " + operation + ".");
    }
}
