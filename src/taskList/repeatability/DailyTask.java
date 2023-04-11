package taskList.repeatability;

import java.time.LocalDate;

public class DailyTask implements Repeatability {

    @Override
    public boolean appearsIn(LocalDate localDate, LocalDate firstRepetitionDate) {
        while (firstRepetitionDate.isBefore(localDate)) {
            firstRepetitionDate = firstRepetitionDate.plusDays(1);
        }
        return firstRepetitionDate.isEqual(localDate);
    }
}
