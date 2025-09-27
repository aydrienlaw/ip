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

    public Task deleteTask(int taskNumber) throws TiloException {
        validateTaskNumber(taskNumber);
        int index = taskNumber - 1;
        return tasks.remove(index);
    }

    public Task getTask(int taskNumber) throws TiloException {
        validateTaskNumber(taskNumber);
        int index = taskNumber - 1;
        return tasks.get(index);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
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

    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        String lowercaseKeyword = keyword.toLowerCase().trim();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lowercaseKeyword)) {
                matchingTasks.addTask(task);
            }
        }

        return matchingTasks;
    }

    private void validateTaskNumber(int taskNumber) throws TiloException {
        if (isEmpty()) {
            throw TiloException.emptyTaskList();
        }

        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw TiloException.invalidTaskRange(taskNumber, size());
        }
    }
}
