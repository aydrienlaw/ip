package tilo.task;

/**
 * Represents a task with a deadline.
 * Contains a description and a deadline date/time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline task with description and deadline.
     *
     * @param description the task description
     * @param by the deadline date/time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        type = "D";
    }

    /**
     * Returns a string representation of the Deadline task for display.
     *
     * @return formatted string showing type, status, description, and deadline
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline task for file storage.
     *
     * @return formatted string for saving to file
     */
    @Override
    public String toSaveString() {
        return super.toSaveString() + " | " + by;
    }
}
