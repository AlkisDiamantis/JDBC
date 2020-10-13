package models;

public class Assignment {

    private int assignment_id;
    private String title;
    private String description;
    private Double totalMark;
    private Double oralMark;

    public Assignment(int assignment_id, String title, String description, Double totalMark, Double oralMark) {
        this.assignment_id = assignment_id;
        this.title = title;
        this.description = description;
        this.totalMark = totalMark;
        this.oralMark = oralMark;
    }

    public Double getOralMark() {
        return oralMark;
    }

    public void setOralMark(Double oralMark) {
        this.oralMark = oralMark;
    }

    public int getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(Double totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public String toString() {
        return "TITLE: " + title + ", DESCRIPTION: " + description + ", ORAL MARK: " + oralMark + ", TOTAL MARK: "  + totalMark +'}';
    }

}
