package taskList.repeatability;

import java.time.LocalDate;

public class MonthlyTask implements Repeatability {

    @Override
    public boolean appearsIn(LocalDate localDate, LocalDate firstRepetitionDate) {
        while (firstRepetitionDate.isBefore(localDate)) {
            firstRepetitionDate = firstRepetitionDate.plusMonths(1);
        }
        return firstRepetitionDate.isEqual(localDate);
    }

}
