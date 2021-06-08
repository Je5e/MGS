package option;

import start.MainProgram;

public class ExitOption extends Option{
    public ExitOption(int code){
        this.code=code;
        this.description="Exit.";
    }
    @Override
    public void executeAction() {
        MainProgram.Salir();
    }
}
