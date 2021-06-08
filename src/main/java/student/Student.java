package student;


import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private double grade;

    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;

        this.grade = grade;
    }
    public Student(String name,  double grade) {

        this.name = name;
        this.grade = grade;
    }

}
