
package models;

public class StudentCourses {
    private int studId;
    private String firstName ;
    private String lastName ;
    private int courseId;
    private String courseTitle;
    private String courseType;

    public StudentCourses(int studId, String firstName, String lastName, int courseId, String courseTitle, String courseType) {
        this.studId = studId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseType = courseType;
    }

    public int getStudId() {
        return studId;
    }

    public void setStudId(int studId) {
        this.studId = studId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "TITLE: " + courseTitle + ", COURSE TYPE: " + courseType + ", STUDENT FIRST NAME: " + firstName + ", STUDENT LAST NAME: " + lastName +  '}';
    }
    
    
    
}
