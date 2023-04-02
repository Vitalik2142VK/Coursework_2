package taskList.repeatability;

import java.time.LocalDate;

public class MonthlyTask implements Repeatability {

    @Override
    public boolean appearsIn(LocalDate ld) {
        return false;
    }
}
