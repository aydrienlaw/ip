package tilo.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        type = "D";
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: " + by + ")";
    }
}
