package option;

public abstract class Option {
    protected int code;
    protected String description;

    public abstract void executeAction();
}
