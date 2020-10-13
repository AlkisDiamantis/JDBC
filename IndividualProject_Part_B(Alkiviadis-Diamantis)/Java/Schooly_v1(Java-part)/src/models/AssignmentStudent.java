
package models;
import java.sql.Date;
import java.time.LocalDate;
public class AssignmentStudent {
    
    private int assi_courseId;
    
    private int assigmentId ;
    
    private String assiTitle;
    
    private int courseId ;
    
    private String courseTitle;
    
    private double oralMark;
    
    private double mark;
    
   private LocalDate submisionDate;

    public AssignmentStudent(int assi_courseId, int assigmentId, String assiTitle, int courseId, String courseTitle, double oralMark, double mark, LocalDate submisionDate) {
        this.assi_courseId = assi_courseId;
        this.assigmentId = assigmentId;
        this.assiTitle = assiTitle;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.oralMark = oralMark;
        this.mark = mark;
        this.submisionDate = submisionDate;
    }

    public int getAssi_courseId() {
        return assi_courseId;
    }

    public void setAssi_courseId(int assi_courseId) {
        this.assi_courseId = assi_courseId;
    }

    public int getAssigmentId() {
        return assigmentId;
    }

    public void setAssigmentId(int assigmentId) {
        this.assigmentId = assigmentId;
    }

    public String getAssiTitle() {
        return assiTitle;
    }

    public void setAssiTitle(String assiTitle) {
        this.assiTitle = assiTitle;
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

    public double getOralMark() {
        return oralMark;
    }

    public void setOralMark(double oralMark) {
        this.oralMark = oralMark;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public LocalDate getSubmisionDate() {
        return submisionDate;
    }

    public void setSubmisionDate(LocalDate submisionDate) {
        this.submisionDate = submisionDate;
    }

    @Override
    public String toString() {
        return "ASSIGNMENT TITLE: " + assiTitle + ", COURSE TITLE: " + courseTitle + ", ORAL_MARK: " + oralMark + ", MARK: " + mark + ", SUBMISION DATE " + submisionDate + '}';
    }
    
   
}
