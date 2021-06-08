package subject;

import java.io.File;
import java.sql.PreparedStatement;
import java.util.List;

import data.DataBase;
import helper.FileOperation;
import student.Student;

public class SubjectRepo implements ISubjectRepository {

    private DataBase context;

    public SubjectRepo() {
        context = new DataBase();
    }

    @Override
    public Subject create(Subject subjectToCreate) {
        return null;
    }

    @Override
    public Subject findSubject(int id) {
        return null;
    }

    @Override
    public List<Subject> findAllSubjects() {
        return null;
    }

    @Override
    public Subject findSubjectByName(String subjectName) { // This method will return the subject, if it has not students then the option will be able to add them.
        Subject result = null;
        File file = context.loadFile(subjectName);
        if (file != null) {
            result = new Subject();
            result.setName(file.getName());
            result.setStudents(FileOperation.getStudentsWithGrades(file));
        }
        return result;
    }
}
