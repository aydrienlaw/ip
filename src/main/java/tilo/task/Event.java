package tilo.task;

/**
 * Represents an event task with start and end times.
 * Contains a description, start time, and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates a new Event task with description, start time, and end time.
     *
     * @param description the event description
     * @param from the event start time
     * @param to the event end time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        type = "E";
    }

    /**
     * Returns a string representation of the Event task for display.
     *
     * @return formatted string showing type, status, description, and time range
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the Event task for file storage.
     *
     * @return formatted string for saving to file
     */
    @Override
    public String toSaveString() {
        return super.toSaveString() + " | " + from + " | " + to;
    }
}