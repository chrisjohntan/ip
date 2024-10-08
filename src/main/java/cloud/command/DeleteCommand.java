package cloud.command;

import cloud.task.Task;
import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

/**
 * Represents a command to delete a task. A <code>DeleteCommand</code>
 * object corresponds to a task specified by the task index.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.delete(index);
        storage.saveData(tasks);
        return ui.showDeletedTask(tasks, deletedTask.toString());
    }
}
