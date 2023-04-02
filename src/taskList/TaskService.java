package taskList;

import java.time.LocalDate;
import java.util.*;

public class TaskService {
    private final Map<Integer, Task> taskMap;
    private final LinkedList<Task> removedTasks;

    public TaskService() {
        this.taskMap = new HashMap<>();
        this.removedTasks = new LinkedList<>();
    }

    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public Task remove(int id) {
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
        return null;
    }

    public Collection<Task> getAllByDate(LocalDate lD) {
        LocalDate dataCreation;
        Collection<Task> tasks = new ArrayList<>();
        
        for (final var map : taskMap.values()) {
            dataCreation = LocalDate.from(map.getDateTime());
            if (dataCreation.isEqual(lD) || map.appearsIn(lD)) {
                tasks.add(map);
            }
        }
        return tasks;
    }

    public Collection<Task> getAllTasks() {
        return taskMap.values();
    }
}
