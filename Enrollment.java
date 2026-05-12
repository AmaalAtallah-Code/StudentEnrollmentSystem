package com.amaal.studentenrollmentsystem.models;
public class Enrollment {
    private String studentId;
    private String courseId;
    private String enrollmentDate;

    // Constructors
    public Enrollment(String studentId, String courseId, String enrollmentDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters (مهمة جداً ليعمل الجدول TableView)
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(String enrollmentDate) { this.enrollmentDate = enrollmentDate; }
}