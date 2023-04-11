package taskList;

import taskList.exeptions.IncorrectArgumentException;
import taskList.repeatability.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    private static int idGenerator = 0;
    private String title, description, repeatString;
    private final Type type;
    private final LocalDateTime dateTime;
    private LocalDate firstRepetitionDate;
    private int id;
    private final Repeatability repeatability;

    public Task(String title, String description, Type type, TypeRepeatability typeRep) throws IncorrectArgumentException {
        changeId();
        this.title = title;
        this.description = description;
        this.type = type;
        dateTime = LocalDateTime.now();
        repeatability = creatRepeatability(typeRep);
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

    public LocalDate getFirstRepetitionDate() {
        return firstRepetitionDate;
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

    public boolean equals(Task object) {
        if (this.hashCode() == object.hashCode()) {
            return title.equals(object.getTitle()) && description.equals(object.getDescription())
                    && this.type == object.getType() && this.repeatability.getClass() == object.repeatability.getClass();
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

    private Repeatability creatRepeatability(TypeRepeatability typeRep) throws IncorrectArgumentException {
        switch (typeRep) {
            case ONETIME:
                repeatString = "Один раз";
                firstRepetitionDate = dateTime.toLocalDate();
                return new OneTimeTask();
            case DAILY:
                repeatString = "Каждый день";
                firstRepetitionDate = dateTime.toLocalDate().plusDays(1);
                return new DailyTask();
            case WEEKLY:
                repeatString = "Каждую неделю";
                firstRepetitionDate = dateTime.toLocalDate().plusWeeks(1);
                return new WeeklyTask();
            case MONTHLY:
                repeatString = "Каждый месяц";
                firstRepetitionDate = dateTime.toLocalDate().plusMonths(1);
                return new MonthlyTask();
            case YEARLY:
                repeatString = "Каждый год";
                firstRepetitionDate = dateTime.toLocalDate().plusYears(1);
                return new YearlyTask();
            default: throw new IncorrectArgumentException("Ошибка при выборе повторяемости.");
        }
    }

    public boolean appearsIn(LocalDate date) {
        return repeatability.appearsIn(date, firstRepetitionDate);
    }

    public void changeId() {
        this.id = ++idGenerator;
        if (idGenerator > 1_999_999)
            idGenerator = 0;
    }
}