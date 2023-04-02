package taskList.repeatability;

import java.time.LocalDate;

public class DailyTask extends Repeatability {

    public DailyTask(LocalDate dateCreation) {
        repeatDate = dateCreation.plusDays(1);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        while (repeatDate.isBefore(localDate)) {
            repeatDate = repeatDate.plusDays(1);
        }
        return repeatDate.isEqual(localDate);
    }
}
