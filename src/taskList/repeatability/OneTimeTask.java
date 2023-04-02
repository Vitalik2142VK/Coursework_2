package taskList.repeatability;

import java.time.LocalDate;

public class OneTimeTask extends Repeatability {

    public OneTimeTask(LocalDate dateCreation) {}

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return false;
    }
}
