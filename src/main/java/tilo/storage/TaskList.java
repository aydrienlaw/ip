package tilo.storage;

import tilo.exception.TiloException;
import tilo.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of tasks with operations for adding, deleting, marking, and searching.
 * Provides validation for task operations and maintains task ordering.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the provided list of tasks.
     *
     * @param tasks initial list of tasks to populate the TaskList
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a new task to the end of the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified position.
     *
     * @param taskNumber 1-based task number to delete
     * @return the deleted task
     * @throws TiloException if task number is invalid or task list is empty
     */
    public Task deleteTask(int taskNumber) throws TiloException {
        validateTaskNumber(taskNumber);
        int index = taskNumber - 1;
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified position without removing it.
     *
     * @param taskNumber 1-based task number to retrieve
     * @return the task at the specified position
     * @throws TiloException if task number is invalid or task list is empty
     */
    public Task getTask(int taskNumber) throws TiloException {
        validateTaskNumber(taskNumber);
        int index = taskNumber - 1;
        return tasks.get(index);
    }

    /**
     * Returns all tasks in the list.
     *
     * @return list of all tasks
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list contains no tasks, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNumber 1-based task number to mark as done
     * @return the marked task
     * @throws TiloException if task number is invalid or task list is empty
     */
    public Task markTask(int taskNumber) throws TiloException {
        Task task = getTask(taskNumber);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the specified task as not done.
     *
     * @param taskNumber 1-based task number to mark as not done
     * @return the unmarked task
     * @throws TiloException if task number is invalid or task list is empty
     */
    public Task unmarkTask(int taskNumber) throws TiloException {
        Task task = getTask(taskNumber);
        task.markAsNotDone();
        return task;
    }

    /**
     * Finds all tasks whose descriptions contain the specified keyword (case-insensitive).
     *
     * @param keyword the search keyword
     * @return a new TaskList containing matching tasks
     */
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

    /**
     * Validates that the given task number is within valid range.
     *
     * @param taskNumber the task number to validate
     * @throws TiloException if task number is invalid or task list is empty
     */
    private void validateTaskNumber(int taskNumber) throws TiloException {
        if (isEmpty()) {
            throw TiloException.emptyTaskList();
        }

        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw TiloException.invalidTaskRange(taskNumber, size());
        }
    }
}
