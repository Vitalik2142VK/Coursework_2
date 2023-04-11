package taskList.repeatability;

import java.time.LocalDate;

public interface Repeatability {

    boolean appearsIn(LocalDate localDate, LocalDate firstRepetitionDate);
}
