package cloud.command;

import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

/**
 * Represents a command to unmark a task as not done.
 * A <code>UnmarkCommand</code> object sets the status of the task at the index to not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(index);
        storage.saveData(tasks);
        return ui.showUnmarked(tasks.getTaskStatus(index));
    }
}
