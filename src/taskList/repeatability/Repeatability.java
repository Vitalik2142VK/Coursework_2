package taskList.repeatability;

import java.time.LocalDate;

public abstract class Repeatability {
    protected LocalDate repeatDate;

    public abstract boolean appearsIn(LocalDate localDate);
}
