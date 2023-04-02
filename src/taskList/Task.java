package taskList;

import taskList.repeatability.Repeatability;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private static int idGenerator = 0;
    private String title, description;
    private Type type;
    private LocalDateTime dateTime;
    private int id;
    private Repeatability repeatability;

    public Task(String title, String description, Type type, Repeatability repeatability) {
        this.id = ++idGenerator;
        if (idGenerator > 1_999_999)
            idGenerator = 0; // Добавить метод проверяющий повторяемость id
        this.title = title;
        this.description = description;
        this.type = type;
        this.repeatability = repeatability;
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

    public boolean equals(Task object) { // добавить для сравнения repeatability
        if (this.hashCode() == object.hashCode()) {
            return title.equals(object.getTitle()) && description.equals(object.getDescription())
                    && this.type == object.getType();
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "Id задачи:\t" + id + "\nНаименование:\t" + title + "\nДата создания:\t" + dateTime + "\nОписание задачи:\n\t" + description;
    }

    //----------Созданные методы----------//

    public boolean appearsIn(LocalDate ld) {
        return repeatability.appearsIn(ld);
    }

}