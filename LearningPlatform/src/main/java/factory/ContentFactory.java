package factory;

import composite.CourseComponent;
import composite.Lecture;
import composite.Quiz;

import java.util.UUID;

public class ContentFactory {
    public static CourseComponent createLecture(String title, int duration) {
        return new Lecture(UUID.randomUUID().toString(), title, duration);
    }

    public static CourseComponent createQuiz(String title, int questionCount) {
        return new Quiz(UUID.randomUUID().toString(), title, questionCount);
    }
}
