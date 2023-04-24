package taskList;

import taskList.exeptions.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task {

    private static int idGenerator = 0;
    private String title, description;
    private final Type type;
    private int id;
    private final LocalDateTime dateTime;

    protected LocalDate dateReplay;

    public Task(String title, String description, Type type) throws IncorrectArgumentException {
        if (title == null || description == null || type == null)
            throw new IncorrectArgumentException("Не корректно введены данные");
        changeId();
        this.title = title;
        this.description = description;
        this.type = type;
        dateTime = LocalDateTime.now();
    }

    //----------Геттеры----------//
    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDateReplay() {
        return dateReplay;
    }

    //----------Сеттеры----------//

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //----------Стандартные методы----------//

    @Override
    public int hashCode() {
        return title.hashCode() + description.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        if (this.hashCode() == task.hashCode()) {
            return title.equals(task.getTitle()) && description.equals(task.getDescription())
                    && this.type == task.getType() && this.dateReplay.equals(task.dateReplay);
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "Индекс задачи:\t" + id + "\nНаименование:\t" + title + "\nДата создания:\t" + dateTime +
                "\nОписание задачи:\n\t" + description;
    }

    //----------Созданные методы----------//

    public abstract boolean appearsIn(LocalDate localDate);

    public void changeId() {
        this.id = ++idGenerator;
        if (idGenerator > 1_999_999)
            idGenerator = 0;
    }
}