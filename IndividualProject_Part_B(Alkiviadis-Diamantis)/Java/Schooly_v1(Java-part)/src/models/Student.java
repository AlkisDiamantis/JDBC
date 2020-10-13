package models;

import java.time.LocalDate;

public class Student {

    private int stud_id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Double tuitionFees;

    public Student(int stud_id, String firstName, String lastName, LocalDate dateOfBirth, Double tuitionFees) {
        this.stud_id = stud_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, Double tuitionFees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;

    }

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(Double tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    @Override
    public String toString() {
        return "STUDENT: FIRST NAME: " + firstName + ", LAST NAME: " + lastName + ", DATE OF BIRTH: " + dateOfBirth + ", TUITION FEES: " + tuitionFees + '}';
    }

}
