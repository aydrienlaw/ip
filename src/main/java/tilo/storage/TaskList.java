package tilo.storage;

import tilo.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final int TASK_LIST_SIZE = 100;
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(TASK_LIST_SIZE);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        validateTaskIndex(index);
        return tasks.get(index);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    private void validateTaskIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task number out of range");
        }
    }
}
