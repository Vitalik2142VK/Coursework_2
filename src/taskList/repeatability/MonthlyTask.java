package taskList.repeatability;

import java.time.LocalDate;

public class MonthlyTask extends Repeatability {

    public MonthlyTask(LocalDate dateCreation) {
        repeatDate = repeatDate.plusMonths(1);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        while (repeatDate.isBefore(localDate)) {
            repeatDate = repeatDate.plusMonths(1);
        }
        return repeatDate.isEqual(localDate);
    }
}
