package taskList.repeatability;

import taskList.Task;
import taskList.Type;
import taskList.exeptions.IncorrectArgumentException;

import java.time.LocalDate;

public class DailyTask extends Task {

    public DailyTask(String title, String description, Type type) throws IncorrectArgumentException {
        super(title, description, type);
        dateReplay = this.getDateTime().toLocalDate().plusDays(1);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        while (dateReplay.isBefore(localDate)) {
            dateReplay = dateReplay.plusDays(1);
        }
        return dateReplay.isEqual(localDate);
    }

}
