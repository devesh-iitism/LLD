package service;

import composite.Course;
import entities.Enrollment;
import entities.Student;
import observer.ProgressObserver;
import repository.EnrollmentRepository;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private final EnrollmentRepository enrollRepo = EnrollmentRepository.getInstance();
    private final List<ProgressObserver> observers = new ArrayList<>();

    public Enrollment enrollStudent(Student student, Course course) {
        String enrollmentId = getEnrollmentId(student.getId(), course.getId());
        Enrollment enrollment = new Enrollment(enrollmentId, student, course);
        enrollRepo.save(enrollment);
        return enrollment;
    }

    private String getEnrollmentId (String studentId, String courseId) {
        return  studentId + "|" + courseId;
    }

    public void markComponentAsComplete(String studentId, String courseId, String componentId) {
        Enrollment enrollment = enrollRepo.findById(getEnrollmentId(studentId, courseId));
        enrollment.markComponentComplete(componentId);
        System.out.println("Progress for " + enrollment.getStudent().getName() + " in '" + enrollment.getCourse().getTitle() + "': "
                + String.format("%.2f", enrollment.getProgressPercentage()) + "%");

        if (enrollment.isCourseCompleted()) {
            enrollment.setStatus(Enrollment.Status.COMPLETED);
            notifyCourseCompletion(enrollment);
        }

        enrollRepo.save(enrollment);
    }

    public void addObserver(ProgressObserver observer) { observers.add(observer); }

    private void notifyCourseCompletion(Enrollment enrollment) {
        observers.forEach(o -> o.onCourseCompleted(enrollment));
    }
}