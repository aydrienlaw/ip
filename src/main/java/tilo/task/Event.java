package tilo.task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        type = "E";
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + " | " + from + " | " + to;
    }
}
