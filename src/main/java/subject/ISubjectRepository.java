package subject;

import java.util.List;

public interface ISubjectRepository {
    Subject create(Subject subjectToCreate);
    Subject findSubject(int id);
    List<Subject> findAllSubjects();
}
