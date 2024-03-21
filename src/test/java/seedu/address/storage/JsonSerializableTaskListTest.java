package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TaskList;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class JsonSerializableTaskListTest {

    private static final Task TASK_1 = new TaskBuilder().withTaskName("Test 1").build();
    private static final Task TASK_2 = new TaskBuilder().withTaskName("Test 2").build();

    @Test
    public void toModelType_validTaskList_success() throws IllegalValueException {
        TaskList taskList = new TaskList();
        taskList.addTask(TASK_1);
        taskList.addTask(TASK_2);

        JsonSerializableTaskList jsonSerializableTaskList = new JsonSerializableTaskList(taskList);
        TaskList modelTaskList = jsonSerializableTaskList.toModelType();

        assertEquals(taskList.getSerializeTaskList(), modelTaskList.getSerializeTaskList());
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() {
        TaskList taskList = new TaskList();
        taskList.addTask(TASK_1);
        taskList.addTask(TASK_1); // Duplicate task

        JsonSerializableTaskList jsonSerializableTaskList = new JsonSerializableTaskList(taskList);
        assertThrows(IllegalValueException.class, jsonSerializableTaskList::toModelType);
    }
}
