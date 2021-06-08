package option;

import java.util.ArrayList;
import java.util.List;

public class MainMenuOptionsList {
    static List<Option> options;

    static {
        options = new ArrayList<>();
        options.add(new LoadFileOption(1));
        options.add(new AddStudentToSubjectOption(2));
        options.add(new GenerateAndSendReports(3));
        options.add(new ExitOption(4));
    }

    public static Option getOption(int codigo) {
        Option result = null;
        for (Option op : options) {
            if (op.code == codigo) {
                result = op;
                break;
            }
        }
        return result;
    }

    public static List<Option> getOptions() {
        return options;
    }
}
