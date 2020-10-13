package models;

import java.time.LocalDate;

public class AssignmentCourses {

    private int assiCourseId;
    private int courseId;
    private String courseTitle;
    private String courseType;
    private int assiID;
    private String assignmentTitle;
    private String assignmentDescription;
    private LocalDate submisionDate;

    public AssignmentCourses(int assiCourseId, int courseId, String courseTitle, String courseType, int assiID, String assignmentTitle, String assignmentDescription, LocalDate submisionDate) {
        this.assiCourseId = assiCourseId;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseType = courseType;
        this.assiID = assiID;
        this.assignmentTitle = assignmentTitle;
        this.assignmentDescription = assignmentDescription;
        this.submisionDate = submisionDate;
    }

    public LocalDate getSubmisionDate() {
        return submisionDate;
    }

    public void setSubmisionDate(LocalDate submisionDate) {
        this.submisionDate = submisionDate;
    }

    public int getAssiCourseId() {
        return assiCourseId;
    }

    public void setAssiCourseId(int assiCourseId) {
        this.assiCourseId = assiCourseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getAssiID() {
        return assiID;
    }

    public void setAssiID(int assiID) {
        this.assiID = assiID;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    @Override
    public String toString() {
        return "ASSIGNMENT TITLE: " + assignmentTitle + ", DESCRIPTION: " + assignmentDescription +"COURSE: " + courseTitle + ", COURSE TYPE: " + courseType +  ", SUBMISION: " + submisionDate + '}';
    }

}
