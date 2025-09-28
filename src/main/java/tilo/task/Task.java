package tilo.task;

/**
 * Base class for all task types in the Tilo application.
 * Provides common functionality for task completion status and description management.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Creates a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for display purposes.
     *
     * @return "X" if task is done, " " (space) if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return formatted string showing status and description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task for file storage.
     *
     * @return formatted string for saving to file
     */
    public String toSaveString() {
        return type + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}