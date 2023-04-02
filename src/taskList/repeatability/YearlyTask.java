package taskList.repeatability;

import java.time.LocalDate;

public class YearlyTask extends Repeatability {

    public YearlyTask(LocalDate dateCreation) {
        repeatDate = dateCreation.plusYears(1);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        while (repeatDate.isBefore(localDate)) {
            repeatDate = repeatDate.plusYears(1);
        }
        return repeatDate.isEqual(localDate);
    }
}
