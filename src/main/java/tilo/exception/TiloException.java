package tilo.exception;

/**
 * Custom exception class for Tilo application-specific errors.
 * Provides factory methods for creating common exception types with appropriate messages.
 */
public class TiloException extends Exception {

    /**
     * Creates a new TiloException with the specified message.
     *
     * @param message the exception message
     */
    public TiloException(String message) {
        super(message);
    }

    /**
     * Creates an exception for empty command input.
     *
     * @return TiloException for empty command
     */
    public static TiloException emptyCommand() {
        return new TiloException("Please enter a command.");
    }

    /**
     * Creates an exception for unrecognized commands.
     *
     * @return TiloException for invalid command
     */
    public static TiloException invalidCommand() {
        return new TiloException("I don't understand that command. Try 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', 'delete', 'find', or 'bye'.");
    }

    /**
     * Creates an exception for empty required fields.
     *
     * @param fieldName the name of the empty field
     * @return TiloException for empty field
     */
    public static TiloException emptyField(String fieldName) {
        return new TiloException("/" + fieldName + " cannot be empty.");
    }

    /**
     * Creates an exception for invalid deadline command format.
     *
     * @return TiloException for invalid deadline format
     */
    public static TiloException invalidDeadlineFormat() {
        return new TiloException("Invalid deadline format. Use: deadline <description> /by <date>");
    }

    /**
     * Creates an exception for invalid event command format.
     *
     * @return TiloException for invalid event format
     */
    public static TiloException invalidEventFormat() {
        return new TiloException("Invalid event format. Use: event <description> /from <start> /to <end>");
    }

    /**
     * Creates an exception for invalid task number format.
     *
     * @param taskNumber the invalid task number string
     * @return TiloException for invalid task number
     */
    public static TiloException invalidTaskNumber(String taskNumber) {
        return new TiloException("Task number must be a positive integer. Found: " + taskNumber + ".");
    }

    /**
     * Creates an exception for task number out of valid range.
     *
     * @param taskNumber the invalid task number
     * @param maxTasks the maximum valid task number
     * @return TiloException for invalid task range
     */
    public static TiloException invalidTaskRange(int taskNumber, int maxTasks) {
        if (maxTasks == 0) {
            return emptyTaskList();
        }
        return new TiloException("Task number must be between 1 and " + maxTasks + ", but got " + taskNumber + ".");
    }

    /**
     * Creates an exception for operations on empty task lists.
     *
     * @return TiloException for empty task list
     */
    public static TiloException emptyTaskList() {
        return new TiloException("No tasks available. Add some tasks firsts.");
    }

    /**
     * Creates an exception for invalid file paths.
     *
     * @return TiloException for invalid file path
     */
    public static TiloException invalidFilePath() {
        return new TiloException("File path cannot be null or empty.");
    }

    /**
     * Creates an exception for corrupted data in storage files.
     *
     * @param line the corrupted line
     * @return TiloException for corrupted line
     */
    public static TiloException corruptedLine(String line) {
        return new TiloException("Found corrupted data in storage file: " + line + ".");
    }

    /**
     * Creates an exception for file operation failures.
     *
     * @param operation the failed operation name
     * @return TiloException for save file error
     */
    public static TiloException saveFileError(String operation) {
        return new TiloException("Failed to perform file operation: " + operation + ".");
    }
}