package observer;

import entities.Enrollment;

public interface ProgressObserver {
    void onCourseCompleted(Enrollment enrollment);
}