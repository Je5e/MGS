package start;

import lombok.Data;
import option.MainMenuOptionsList;
import option.Option;
import subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainProgram {

    static boolean salir = false;
    static int counterSubject = 0;
    static List<Subject> subjects = new ArrayList<>();

    public static void addSubject(Subject newSubject) {
        newSubject.setId(++counterSubject);
        subjects.add(newSubject);
    }

    public static List<Subject> getSubjects() {
        return subjects;
    }

    public static void Salir() {
        salir = true;
    }

    public static void main(String[] args) {
        MainProgram p = new MainProgram();
        p.iniciar();
    }

    public static Subject getSubeject(int id) {
        Subject result = null;
        for (Subject subject : subjects) {
            if (subject.getId() == id) {
                result = subject;
                break;
            }
        }
        return result;
    }

    // Private methods.
    void iniciar() {
        while (!MainProgram.salir) {
            int opcionSeleccionada = mostrarMenu();
            try {
                Option o = MainMenuOptionsList.getOption(opcionSeleccionada);
                o.executeAction();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            iniciar();
        }
    }

    private int mostrarMenu() {
        int result;
        System.out.println("Opciones:");
        System.out.println("----------------------");

        List<Option> options = MainMenuOptionsList.getOptions();

        for (Option op : options) {
            System.out.println(op.getCode() + " - " + op.getDescription());
        }
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite: ");
        result = sc.nextInt();

        return result;
    }
}
