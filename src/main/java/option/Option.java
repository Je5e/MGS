package option;

public abstract class Option {
    protected int code;
    protected String description;

    public abstract void executeAction();

    public int getCode() {
       return code;
    }

    public String  getDescription() {
        return description;
    }
}
