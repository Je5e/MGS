package helper;

import student.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperation {

    public static List<Student> getStudentsWithGrades(File file){
        List<Student> result = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);

                while (reader.hasNext()) {
                    String line = reader.nextLine();
                    String[] values = line.split(",");
                    result.add(new Student(values[0],Integer.parseInt(values[1])) );
                }

        } catch (FileNotFoundException ex) {

        }

        return result;
    }
}
