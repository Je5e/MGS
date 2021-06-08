package option;

import start.MainProgram;
import student.Student;
import subject.Subject;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AddStudentToSubjectOption extends Option {
   private Scanner scanner =new Scanner(System.in);

    public AddStudentToSubjectOption(Integer code) {
        this.code = code;
        this.description = "Add student to a subject.";
    }

    @Override
    public void executeAction() {
// Add all the logic here.
        System.out.println("Select the subject to work with:");
        for(Subject subject: MainProgram.getSubjects()){
            System.out.println(MessageFormat.format("{0} - {1}", subject.getId(), subject.getName()));
        }
        System.out.print("Input: ");
        int subjectSelected = Integer.parseInt(scanner.next());

        Subject subject = MainProgram.getSubeject(subjectSelected);

        System.out.println("Working with "+subject.getName());

        System.out.println("Do you want to add a student? (q=exit / Yes=y):");
        String inpuntText= scanner.next();
        boolean moreStudents = inpuntText.equals("q");
        while (!moreStudents){
            System.out.print("Student name: ");
            String name = scanner.next();
            System.out.print("Student grade: ");
            int grade = Integer.parseInt(scanner.next());
            Student newStudent = new Student(name,grade);
            subject.setStudent(newStudent);

            System.out.println("Do you want to add a student?: (q=exit / Yes=y) ");
             inpuntText=scanner.next();
             moreStudents = inpuntText.equals("q");
        }


    }
}
