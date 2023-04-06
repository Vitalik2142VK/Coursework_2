package taskList;

import taskList.exeptions.TaskNotFoundException;

import java.time.LocalDate;
import java.util.*;

public class TaskService {
    private final Map<Integer, Task> taskMap;
    private final LinkedList<Task> removedTasks;

    public TaskService() {
        this.taskMap = new HashMap<>();
        this.removedTasks = new LinkedList<>();
    }

    public void addTask(Task task) throws TaskNotFoundException {
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

    public Task remove(int id) throws TaskNotFoundException {
        if (taskMap.containsKey(id)) {
            if (taskMap.get(id).getType() == Type.PERSONAL) {
                removedTasks.addFirst(taskMap.get(id));
                taskMap.remove(id);
                return removedTasks.getFirst();
            } else {
                removedTasks.addLast(taskMap.get(id));
                taskMap.remove(id);
                return removedTasks.getLast();
            }
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

    public Collection<Task> getAllTasks() {
        return taskMap.values();
    }
}
