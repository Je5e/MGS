package option;

import start.MainProgram;
import subject.ISubjectRepository;
import subject.Subject;
import subject.SubjectRepo;

import java.util.Scanner;

public class LoadFileOption extends Option {
    ISubjectRepository repository;

    public LoadFileOption(Integer code) {
        this.code = code;
        this.description = "Load File.";
        this.repository = new SubjectRepo();
    }

    @Override
    public void executeAction() {
        Scanner scanner = new Scanner(System.in);
        String subjectInputName;
        System.out.print("Enter subject file name (e.g: math): ");
        subjectInputName = scanner.next();

        Subject subject = repository.findSubjectByName(subjectInputName);
        if (subject == null) { // the file not exists.
            // Allow the user create it.
        } else if (subject.getStudents().stream().count() == 0) {
            // File is empty, allow user add students.
            System.out.println("File is empty.");
        } else {
            System.out.println(subjectInputName+", file has been loaded successfully.");
            System.out.println("Students in file: " + subject.getStudents().stream().count());
            MainProgram.addSubject(subject);
        }

    }
}
