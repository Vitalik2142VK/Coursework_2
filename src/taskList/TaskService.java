package taskList;

import taskList.exeptions.TaskNotFoundException;

import java.time.LocalDate;
import java.util.*;

public class TaskService {
    private final Map<Integer, Task> taskMap;
    private final List<Task> removedTasks;

    public TaskService() {
        this.taskMap = new HashMap<>();
        this.removedTasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        int i;
        for (i = 0; i < 2_000_000; i++) {
            if (taskMap.containsKey(task.getId())) {
                task.changeId();
            } else {
                break;
            }
        }
        if (i >= 2_000_000)
            throw new TaskNotFoundException("Нет совбодного места для записи новой задачи, удалите старые.");
        taskMap.put(task.getId(), task);
    }

    public Task remove(int idTask) {
        if (taskMap.containsKey(idTask)) {
            removedTasks.add(taskMap.get(idTask));
            return taskMap.remove(idTask);
        }
        throw new TaskNotFoundException("Задачи с таким индексом не существует.");
    }

    public Collection<Task> getAllByDate(LocalDate lD) {
        LocalDate dataCreation;
        Collection<Task> tasks = new ArrayList<>();
        
        for (final var value : taskMap.values()) {
            dataCreation = LocalDate.from(value.getDateTime());

            if (dataCreation.isEqual(lD) || value.appearsIn(lD)) {
                tasks.add(value);
            }
        }
        return tasks;
    }

    public Map<LocalDate, List<Task>> getAllGroupByDate() {
        Map<LocalDate, List<Task>> dataGroupMap = new HashMap<>();
        return null;
    }

    public List<Task> getRemovedTasks() {
        return removedTasks;
    }

    public Task updateTitle(int idTask, String newDescription) {
        if (taskMap.containsKey(idTask)) {
            Task task = taskMap.get(idTask);
            task.setTitle(newDescription);
            return task;
        }
        throw new TaskNotFoundException("Задачи с таким индексом не существует.");
    }

    public Task updateDescription(int idTask, String newDescription) {
        if (taskMap.containsKey(idTask)) {
            Task task = taskMap.get(idTask);
            task.setDescription(newDescription);
            return task;
        }
        throw new TaskNotFoundException("Задачи с таким индексом не существует.");
    }

    public Collection<Task> getAllTasks() {
        return taskMap.values();
    }
}
