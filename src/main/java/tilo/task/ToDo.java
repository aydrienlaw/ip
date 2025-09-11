package tilo.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        type = "T";
    }
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
