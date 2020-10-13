package models;



public class Trainer {
    private int trainer_id;
    private String firstName;
    private  String lastName;
    private String subject;

    public Trainer(String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public Trainer(int trainer_id, String firstName, String lastName, String subject) {
        this.trainer_id = trainer_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return  "TRAINER: FIRST NAME: " + firstName + ", LAST NAME: " + lastName + ", SUBJECT: " + subject + '}';
    }

}
