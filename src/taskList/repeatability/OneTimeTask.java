package taskList.repeatability;

import taskList.Task;
import taskList.Type;
import taskList.exeptions.IncorrectArgumentException;

import java.time.LocalDate;

public class OneTimeTask extends Task {

    public OneTimeTask(String title, String description, Type type) throws IncorrectArgumentException {
        super(title, description, type);
        dateReplay = this.getDateTime().toLocalDate();
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isEqual(dateReplay);
    }
}
