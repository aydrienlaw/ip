package tilo;

import tilo.task.Deadline;
import tilo.task.Event;
import tilo.task.Task;
import tilo.task.ToDo;
import tilo.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class Tilo {
    private static final int TASK_LIST_SIZE = 100;

    private boolean isRunning;
    private final List<Task> tasks;
    private final Ui ui;

    public Tilo() {
        this.tasks = new ArrayList<>(TASK_LIST_SIZE);
        this.ui = new Ui();
        this.isRunning = false;
    }

    private void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        printTaskAdded(newTask);
    }

    private void addToDo(String description) {
        Task newToDo = new ToDo(description);
        tasks.add(newToDo);
        printTaskAdded(newToDo);
    }

    private void addDeadline(String inputLine) {
        String byPrefix = " /by ";
        int byIndex = inputLine.lastIndexOf(byPrefix);

        if (byIndex == -1) {
            ui.showError("Deadline format should be: deadline <description> /by <date>");
            return;
        }

        String description = inputLine.substring(0, byIndex).trim();
        String by = inputLine.substring(byIndex + byPrefix.length()).trim();

        if (description.isEmpty() || by.isEmpty()) {
            ui.showError("Both description and deadline date are required.");
            return;
        }

        Task newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        printTaskAdded(newDeadline);
    }

    private void addEvent(String inputLine) {
        String fromPrefix = " /from ";
        String toPrefix = " /to ";
        int fromIndex = inputLine.lastIndexOf(fromPrefix);
        int toIndex = inputLine.lastIndexOf(toPrefix);

        if (fromIndex == -1 || toIndex == -1) {
            ui.showError("Event format should be: event <description> /from <start> /to <end>");
            return;
        }

        String description = inputLine.substring(0, fromIndex).trim();
        String from = inputLine.substring(fromIndex + fromPrefix.length(), toIndex).trim();
        String to = inputLine.substring(toIndex + toPrefix.length()).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            ui.showError("Description, start time, and end time are all required.");
            return;
        }

        Task newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        printTaskAdded(newEvent);
    }

    private void printTaskAdded(Task task) {
        ui.showTaskAdded(task, tasks.size());
    }

    private void listTasks() {
        ui.showTaskList(tasks);
    }

    private void markTask(Task task) {
        task.markAsDone();
        ui.showTaskMarked(task);
    }

    private void unmarkTask(Task task) {
        task.markAsNotDone();
        ui.showTaskUnmarked(task);
    }

    private void handleMarkCommand(String[] words, boolean isMarking) {
        if (tasks.isEmpty()) {
            ui.showError("No tasks to " + (isMarking ? "mark" : "unmark") + ".");
            return;
        }

        try {
            int taskNum = Integer.parseInt(words[1]) - 1;
            if (taskNum < 0 || taskNum >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task task = tasks.get(taskNum);
            if (isMarking) {
                markTask(task);
            } else {
                unmarkTask(task);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showError("Please enter a valid task number.");
        }
    }

    private void processCommand(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            ui.showError("Please enter a command.");
            return;
        }

        String[] words = userInput.split(" ", 2);
        String command = words[0].toLowerCase();

        switch (command) {
        case "todo":
            addToDo(words[1]);
            break;
        case "deadline":
            addDeadline(words[1]);
            break;
        case "event":
            addEvent(words[1]);
            break;
        case "list":
            listTasks();
            break;
        case "mark":
            handleMarkCommand(words, true);
            break;
        case "unmark":
            handleMarkCommand(words,false);
            break;
        case "bye":
            isRunning = false;
            break;
        default:
            addTask(userInput);
            break;
        }
    }

    public void run() {
        ui.showWelcome();
        isRunning = true;

        while (isRunning) {
            String userInput = ui.readCommand();
            ui.showBorder();
            processCommand(userInput);
            ui.showBorder();
        }

        ui.showWelcome();
    }

    public static void main(String[] args) {
        Tilo tilo = new Tilo();
        tilo.run();
    }
}
