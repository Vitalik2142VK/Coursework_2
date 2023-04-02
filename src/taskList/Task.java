package taskList;

import taskList.exeptions.IncorrectArgumentException;
import taskList.repeatability.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private static int idGenerator = 0;
    private String title, description, repeatString;
    private Type type;
    private final LocalDateTime dateTime;
    private int id;
    private final Repeatability repeatability;

    public Task(String title, String description, Type type, TypeRepeatability typeRep) throws IncorrectArgumentException {
        this.id = ++idGenerator;
        if (idGenerator > 1_999_999)
            idGenerator = 0; // Добавить метод проверяющий повторяемость id
        this.title = title;
        this.description = description;
        this.type = type;
        dateTime = LocalDateTime.now();
        repeatability = creatRepeatability(typeRep, dateTime.toLocalDate());
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
        return "Индекс задачи:\t" + id + "\nНаименование:\t" + title + "\nДата создания:\t" + dateTime + "\nПовторяемость:\t" + repeatString +
                "\nОписание задачи:\n\t" + description;
    }

    //----------Созданные методы----------//

    private Repeatability creatRepeatability(TypeRepeatability typeRep, LocalDate dateCreation) throws IncorrectArgumentException {
        switch (typeRep) {
            case ONETIME:
                repeatString = "Один раз";
                return new OneTimeTask(dateCreation);
            case DAILY:
                repeatString = "Каждый день";
                return new DailyTask(dateCreation);
            case WEEKLY:
                repeatString = "Каждую неделю";
                return new WeeklyTask(dateCreation);
            case MONTHLY:
                repeatString = "Каждый месяц";
                return new MonthlyTask(dateCreation);
            case YEARLY:
                repeatString = "Каждый год";
                return new YearlyTask(dateCreation);
            default: throw new IncorrectArgumentException("Ошибка при выборе повторяемости.");
        }
    }

    public boolean appearsIn(LocalDate ld) {
        return repeatability.appearsIn(ld);
    }

}