package taskList.repeatability;

import java.time.LocalDate;

public class YearlyTask implements Repeatability {

    @Override
    public boolean appearsIn(LocalDate localDate, LocalDate firstRepetitionDate) {
        while (firstRepetitionDate.isBefore(localDate)) {
            firstRepetitionDate = firstRepetitionDate.plusYears(1);
        }
        return firstRepetitionDate.isEqual(localDate);
    }

}
