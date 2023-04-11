package taskList.repeatability;

import java.time.LocalDate;

public class WeeklyTask implements Repeatability {

    @Override
    public boolean appearsIn(LocalDate localDate, LocalDate firstRepetitionDate) {
        while (firstRepetitionDate.isBefore(localDate)) {
            firstRepetitionDate = firstRepetitionDate.plusWeeks(1);
        }
        return firstRepetitionDate.isEqual(localDate);
    }

}
