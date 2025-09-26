package tilo.storage;

import tilo.exception.TiloException;
import tilo.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTask(int taskNumber) throws TiloException {
        Task task = getTask(taskNumber);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int taskNumber) throws TiloException {
        Task task = getTask(taskNumber);
        task.markAsNotDone();
        return task;
    }

    public void deleteTask(int taskNumber) throws TiloException {
        validateTaskNumber(taskNumber);
        tasks.remove(taskNumber);
    }

    public Task getTask(int taskNumber) throws TiloException {
        validateTaskNumber(taskNumber);
        return tasks.get(taskNumber);
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

    private void validateTaskNumber(int taskNumber) throws TiloException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw TiloException.invalidTaskNumber();
        }
    }
}
