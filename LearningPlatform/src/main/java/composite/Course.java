package composite;

import entities.Instructor;

import java.util.ArrayList;
import java.util.List;

public class Course implements CourseComponent {
    private final String id;
    private final String title;
    private final Instructor instructor;
    private final List<CourseComponent> content = new ArrayList<>();

    public Course(String id, String title, Instructor instructor) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
    }

    public void addContent(CourseComponent component) {
        content.add(component);
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getTitle() { return title; }

    public Instructor getInstructor() { return instructor; }

    public List<CourseComponent> getContent() { return content; }

    @Override
    public void display() {
        System.out.println("Course: " + title + " by " + instructor.getName());
        content.forEach(CourseComponent::display);
    }
}
