package taskList.repeatability;

import taskList.Task;
import taskList.Type;
import taskList.exeptions.IncorrectArgumentException;

import java.time.LocalDate;

public class YearlyTask extends Task {

    public YearlyTask(String title, String description, Type type) throws IncorrectArgumentException {
        super(title, description, type);
        dateReplay = this.getDateTime().toLocalDate().plusYears(1);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        while (dateReplay.isBefore(localDate)) {
            dateReplay = dateReplay.plusYears(1);
        }
        return dateReplay.isEqual(localDate);
    }

}
