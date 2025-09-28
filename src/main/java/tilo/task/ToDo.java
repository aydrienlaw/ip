package tilo.task;

/**
 * Represents a simple todo task with just a description.
 * This is the basic task type without any additional time information.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task with the specified description.
     *
     * @param description the task description
     */
    public ToDo(String description) {
        super(description);
        type = "T";
    }

    /**
     * Returns a string representation of the ToDo task for display.
     *
     * @return formatted string showing type, status, and description
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}