package taskList;

import taskList.exeptions.TaskNotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    public Collection<Task> getAllByDate(LocalDate date) {
        return taskMap.values().stream()
                .filter(t -> t.appearsIn(date))
                .collect(Collectors.toList());
    }

    public Map<LocalDate, List<Task>> getAllGroupByDate() {
        Map<LocalDate, List<Task>> dataGroupMap = new TreeMap<>();

        for (var value : taskMap.values()) {
            if (dataGroupMap.containsKey(value.getFirstRepetitionDate())) {
                dataGroupMap.get(value.getFirstRepetitionDate()).add(value);
            } else {
                dataGroupMap.put(value.getFirstRepetitionDate(), new ArrayList<>(List.of(value)));
            }
        }

        return dataGroupMap;
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
}
