// Storage.java
package tilo.storage;

import tilo.exception.TiloException;
import tilo.task.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Handles persistent storage of tasks to and from the filesystem.
 * Manages file format parsing and generation for task data.
 */
public class Storage {
    // File format constants
    private static final String DONE_MARKER = "1";

    // Task type identifiers
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";

    // Expected field counts for validation
    private static final int TODO_FIELD_COUNT = 3;
    private static final int DEADLINE_FIELD_COUNT = 4;
    private static final int EVENT_FIELD_COUNT = 5;

    private final Path filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath path to the storage file
     * @throws TiloException if the file path is null or empty
     */
    public Storage(String filePath) throws TiloException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw TiloException.invalidFilePath();
        }
        this.filePath = Path.of(filePath);
    }

    /**
     * Loads all tasks from the storage file.
     * Creates the file and directories if they don't exist.
     *
     * @return list of tasks loaded from storage
     * @throws TiloException if file operations fail or data is corrupted
     */
    public List<Task> load() throws TiloException {
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            createFileAndDirectories();
            return tasks; // Return early for new file
        }

        try (Scanner s = new Scanner(filePath.toFile())) {
            while (s.hasNext()) {
                Task task = parseTask(s.nextLine());
                tasks.add(task);
            }
        } catch (IOException e) {
            throw TiloException.saveFileError("loading");
        }

        return tasks;
    }

    /**
     * Creates the storage file and any necessary parent directories.
     *
     * @throws TiloException if file creation fails
     */
    private void createFileAndDirectories() throws TiloException {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException e) {
            throw TiloException.saveFileError("creating");
        }
    }

    /**
     * Saves all tasks to the storage file.
     *
     * @param tasks list of tasks to save
     * @throws TiloException if file writing fails
     */
    public void save(List<Task> tasks) throws TiloException {
        try (FileWriter fw = new FileWriter(filePath.toFile())) {
            for (Task task : tasks) {
                fw.write(task.toSaveString() + "\n");
            }
        } catch (IOException e) {
            throw TiloException.saveFileError("saving");
        }
    }

    /**
     * Parses a single line from the storage file into a Task object.
     *
     * @param line the line to parse
     * @return the parsed Task object
     * @throws TiloException if the line format is invalid or corrupted
     */
    private Task parseTask(String line) throws TiloException {
        if (line == null || line.trim().isEmpty()) {
            throw TiloException.corruptedLine("Empty line");
        }

        String[] parts = line.split(" \\| ");

        try {
            String taskType = parts[0].trim();
            boolean isDone = parts[1].equals(DONE_MARKER);
            String description = parts[2].trim();

            Task task = createTaskByType(taskType, description, parts, line);

            if (isDone) {
                task.markAsDone();
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw TiloException.corruptedLine(line);
        }
    }

    /**
     * Creates a specific task type based on the task type identifier.
     *
     * @param taskType the task type identifier
     * @param description the task description
     * @param parts all parsed parts from the line
     * @param originalLine the original line for error reporting
     * @return the created Task object
     * @throws TiloException if task type is unknown or data is invalid
     */
    private Task createTaskByType(String taskType, String description, String[] parts, String originalLine)
            throws TiloException {
        switch (taskType) {
        case TODO_TYPE:
            validateFieldCount(parts.length, TODO_FIELD_COUNT, originalLine);
            return new ToDo(description);

        case DEADLINE_TYPE:
            validateFieldCount(parts.length, DEADLINE_FIELD_COUNT, originalLine);
            String by = parts[3].trim();
            return new Deadline(description, by);

        case EVENT_TYPE:
            validateFieldCount(parts.length, EVENT_FIELD_COUNT, originalLine);
            String from = parts[3].trim();
            String to = parts[4].trim();
            return new Event(description, from, to);

        default:
            throw TiloException.corruptedLine("(Unknown task type '" + taskType + "': " + originalLine);
        }
    }

    /**
     * Validates that the parsed line has the expected number of fields.
     *
     * @param actualCount actual number of fields found
     * @param expectedCount expected number of fields
     * @param originalLine original line for error reporting
     * @throws TiloException if field count doesn't match expected
     */
    private void validateFieldCount(int actualCount, int expectedCount, String originalLine) throws TiloException {
        if (actualCount != expectedCount) {
            throw TiloException.corruptedLine(
                    String.format("Expected %d fields, got %d: %s", expectedCount, actualCount, originalLine)
            );
        }
    }
}
