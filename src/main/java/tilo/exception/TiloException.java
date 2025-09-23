package tilo.exception;

public class TiloException extends Exception {
    public TiloException(String message) {
        super(message);
    }

    public static TiloException noCommand() {
        return new TiloException("Please enter a command.");
    }

    public static TiloException invalidCommand() {
        return new TiloException("Please enter a valid command.");
    }

    public static TiloException emptyTaskDescription(String task) {
        return new TiloException("The description of a " + task + " cannot be empty.");
    }

    public static TiloException invalidDeadlineFormat() {
        return new TiloException("Deadline format should be: deadline <description> /by <date>");
    }

    public static TiloException emptyDeadlineBy() {
        return new TiloException("The deadline date cannot be empty.");
    }

    public static TiloException invalidEventFormat() {
        return new TiloException("Event format should be: event <description> /from <start date> /to <end date>");
    }

    public static TiloException emptyEventFrom() {
        return new TiloException("The event start date cannot be empty.");
    }

    public static TiloException emptyEventTo() {
        return new TiloException("The event end date cannot be empty.");
    }

    public static TiloException noTaskNumber(String operation) {
        return new TiloException("Please specify a task number to " + operation + ".");
    }

    public static TiloException invalidTaskNumber() {
        return new TiloException("Please enter a valid task number.");
    }

    public static TiloException emptyTaskList(String operation) {
        return new TiloException("No tasks to " + operation + ".");
    }

    public static TiloException corruptedLine(String line) {
        return new TiloException("Corrupted line in storage file: " + line + ".");
    }

    public static TiloException saveFileError(String operation) {
        return new TiloException("Error " + operation + " save file.");
    }
}
