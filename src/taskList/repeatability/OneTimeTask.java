package taskList.repeatability;

import java.time.LocalDate;

public class OneTimeTask implements Repeatability {

    @Override
    public boolean appearsIn(LocalDate localDate, LocalDate firstRepetitionDate) {
        return firstRepetitionDate.isEqual(localDate);
    }

}
