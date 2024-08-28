import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final String filePath = "./data/Cloud.txt";

    public Storage() {
        try {
            File f = new File(this.filePath);
            // create directories if they do not exist yet
            f.getParentFile().mkdirs();
            // create file if it does not exist yet
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error initialising storage!");
        }
    }

    public void saveData(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            int taskCount = taskList.getTaskCount();
            for (int i = 0; i < taskCount; i++) {
                writer.write(taskList.getTask(i).formatSave());
            }
        } catch (IOException e) {
            System.out.println("Error writing to save file!");
        }
    }

    public TaskList readData() {
        TaskList taskList = new TaskList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = null;
                String[] splits = line.split(" \\s*\\|\\s* ");
                // split[1] is reserved for the marked indicator
                String description = splits[2];
                try {
                    switch (splits[0]) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        task = new Deadline(description, splits[3]);
                        break;
                    case "E":
                        task = new Event(description, splits[3], splits[4]);
                        break;
                    default:
                        break;
                    }
                } catch (CloudException e) {
                    System.out.println("Error reading from file");
                }
                if (task != null) {
                    if (splits[1].equals("1")) {
                        task.markDone();
                    }
                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from save file!");
        }
        return taskList;
    }
}
