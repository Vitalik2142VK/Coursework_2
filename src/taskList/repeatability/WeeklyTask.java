package taskList.repeatability;

import java.time.LocalDate;

public class WeeklyTask extends Repeatability {

    public WeeklyTask(LocalDate dateCreation) {
        repeatDate = dateCreation.plusWeeks(1);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        while (repeatDate.isBefore(localDate)) {
            repeatDate = repeatDate.plusWeeks(1);
        }
        return repeatDate.isEqual(localDate);
    }
}
