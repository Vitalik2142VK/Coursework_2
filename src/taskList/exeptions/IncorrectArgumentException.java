package taskList.exeptions;

public class IncorrectArgumentException extends Exception{
    public IncorrectArgumentException(String argument) {
        this.argument = argument;
    }

    String argument;

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "IncorrectArgumentException{" +
                "argument='" + argument + '\'' +
                '}';
    }
}
