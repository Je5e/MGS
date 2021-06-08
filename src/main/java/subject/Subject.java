package subject;

import student.Student;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private int id;
    private String name;
    private List<Student> students = new ArrayList<>();

    public Subject(){}

    public Subject(int id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setStudents(List<Student> students){
        this.students=students;
    }
    public List<Student> getStudents(){
        return students;
    }

    public void setId(int i) {
        this.id=i;
    }
public void setStudent(Student s){
        students.add(s);
}
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
