package facade;

import composite.Course;
import composite.CourseComponent;
import entities.Enrollment;
import entities.Instructor;
import entities.Student;
import factory.ContentFactory;
import observer.ProgressObserver;
import repository.CourseRepository;
import repository.UserRepository;
import service.EnrollmentService;

public class LearningPlatformFacade {
    private final UserRepository userRepo = UserRepository.getInstance();
    private final CourseRepository courseRepo = CourseRepository.getInstance();
    private final EnrollmentService enrollmentService = new EnrollmentService();

    public void addProgressObserver(ProgressObserver observer) {
        enrollmentService.addObserver(observer);
    }

    public Student createStudent(String name, String email) {
        Student student = new Student(name, email);
        userRepo.save(student);
        return student;
    }

    public Instructor createInstructor(String name, String email) {
        Instructor instructor = new Instructor(name, email);
        userRepo.save(instructor);
        return instructor;
    }

    public Course createCourse(String courseId, String title, Instructor instructor) {
        Course course = new Course(courseId, title, instructor);
        courseRepo.save(course);
        return course;
    }

    public void addLectureToCourse(String courseId, String title, int duration) {
        Course course = courseRepo.findById(courseId);
        CourseComponent lecture = ContentFactory.createLecture(title, duration);
        course.addContent(lecture);
    }

    public void addQuizToCourse(String courseId, String title, int questions) {
        Course course = courseRepo.findById(courseId);
        CourseComponent quiz = ContentFactory.createQuiz(title, questions);
        course.addContent(quiz);
    }

    public Enrollment enrollStudent(String studentId, String courseId) {
        Student student = (Student) userRepo.findById(studentId);
        Course course = courseRepo.findById(courseId);
        return enrollmentService.enrollStudent(student, course);
    }

    public void completeComponent(String studentId, String courseId, String componentId) {
        enrollmentService.markComponentAsComplete(studentId, courseId, componentId);
    }
}