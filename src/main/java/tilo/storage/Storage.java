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

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public List<Task> load() throws TiloException {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } catch (IOException e) {
                throw TiloException.saveFileError("loading");
            }
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

    public void save(List<Task> tasks) throws TiloException {
        try (FileWriter fw = new FileWriter(filePath.toFile())) {
            for (Task task : tasks) {
                fw.write(task.toSaveString() + "\n");
            }
        } catch (IOException e) {
            throw TiloException.saveFileError("saving");
        }
    }

    private Task parseTask(String rawInput) throws TiloException {
        try {
            String[] parts = rawInput.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (taskType) {
            case "T":
                Task todo = new ToDo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                String by = parts[3];
                Task deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                String from = parts[3];
                String to = parts[4];
                Task event = new Event(description, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                throw TiloException.corruptedLine(rawInput);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw TiloException.corruptedLine(rawInput);
        }
    }
}