package actions;

import dataBase.AssignmentDB;
import dataBase.CourseDB;
import dataBase.StudentDB;
import dataBase.TrainerDB;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Assignment;
import models.AssignmentCourses;
import models.Course;
import models.Student;
import models.Trainer;
import static tools.PrintMsg.printLine;
import validation.ValidInput;
import static validation.ValidInput.validDate;
import static validation.ValidInput.validDouble;
import static validation.ValidInput.validName;

public class Constraction {

    //******************************** STUDENTS PER COURSE METHOD ****************************************//
    public static void studensPerCourse(Scanner input, Course course) {
        StudentDB sdb = new StudentDB();
        ArrayList<Student> studentList = sdb.getListOfStudents();

        System.out.println("*******************************-ADD STUDENT TO " + course.getTitle().toUpperCase() + " " + course.getType().toUpperCase() + "-********************************");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println((i + 1) + "." + studentList.get(i));
        }
        boolean check = false;
        int num;
        do {
            System.out.println("Choose a Student to add");
            
            num = ValidInput.validInteger(input, 1, studentList.size());
            check = sdb.checkStudentCourse(studentList.get(num - 1).getStud_id(), course.getCourse_id());
        } while (check == false);
        sdb.insertStudentToCourse(studentList.get(num - 1).getStud_id(), course.getCourse_id());
        printLine();
        System.out.println("*********************** GREAT STUDENT SUCCEFULLY ADDED **************************");

    }
    //******************************** COURSE PER STUDENT METHOD ****************************************//

    public static int coursePerStudent(Scanner input, Student student) {
        CourseDB cdb = new CourseDB();
        StudentDB sdb = new StudentDB();
        ArrayList<Course> courseList = cdb.getListOfCourses();
        printLine();
        System.out.println("************************* ADD COURSE TO " + student.getFirstName().toUpperCase() + " " + student.getLastName().toUpperCase() + " ********************************");

        for (int i = 0; i < courseList.size(); i++) {
            System.out.println((i + 1) + "." + courseList.get(i));
        }
        int num;
        boolean check = false;
        do {
            System.out.println("(Choose a Course to add ) ");

            num = ValidInput.validInteger(input, 1, courseList.size());
            check = sdb.checkStudentCourse(student.getStud_id(), courseList.get(num - 1).getCourse_id());
        } while (check == false);
        sdb.insertStudentToCourse(student.getStud_id(), courseList.get(num - 1).getCourse_id());
        printLine();
        System.out.println("         ************************* COURSE SUCCEFULLY ADDED!! ********************");
        printLine();
        return courseList.get(num - 1).getCourse_id();
    }
    //************************************** TRAINER PER COURSE ****************************************************************//

    public static void trainerPerCourse(Scanner input, Course course) {
        TrainerDB tdb = new TrainerDB();
        ArrayList<Trainer> trainerList = tdb.getListOfTrainers();

        System.out.println("***********************************-ADD TRAINER TO " + course.getTitle().toUpperCase() + " " + course.getType().toUpperCase() + "-*********************************");
        System.out.println("Choose from the following tainer  List ");

        for (int i = 0; i < trainerList.size(); i++) {
            System.out.println((i + 1) + "." + trainerList.get(i));
        }
        int num;
        boolean check = false;
        do {
            
            num = ValidInput.validInteger(input, 1, trainerList.size());
            check = tdb.checkTrainerCourse(trainerList.get(num - 1).getTrainer_id(), course.getCourse_id());
        } while (check == false);
        tdb.insertTrainerToCourse(trainerList.get(num - 1).getTrainer_id(), course.getCourse_id());
        printLine();
        System.out.println("********************************* GREAT TRAINER SUCCEFULLY ADDED!!! **************************");

    }
    //************************************ASSIGMENT PER COURSE ******************************************************//

    public static void assigmentPerCourse(Scanner input, Course course) {
        AssignmentDB adb = new AssignmentDB();
        ArrayList<Assignment> assigmentList = adb.getListOfAssignments();
        System.out.println("********************-ADD ASSIGMENT TO " + course.getTitle().toUpperCase() + " " + course.getType().toUpperCase() + "-********************************");

        System.out.println("Choose from the following Assigment  List ");

        for (int i = 0; i < assigmentList.size(); i++) {
            System.out.println((i + 1) + "." + assigmentList.get(i));
        }
        boolean check = false;
        int num;
        do {
            System.out.print("Enter number : ");
            num = ValidInput.validInteger(input, 1, assigmentList.size());
            check = adb.checkAssignmentCourse(assigmentList.get(num - 1).getAssignment_id(), course.getCourse_id());
        } while (check == false);
        System.out.println("Enter Submision Date : ");
        Date subDate = validDate(input);

        adb.insertAssignmentToCourse(assigmentList.get(num - 1).getAssignment_id(), course.getCourse_id(), subDate);
        printLine();
        System.out.println("*************************** GREAT ASSIGNMENT SUCCEFULLY ADDED!!! **************************");

    }

//**************************************ENROLL A STUDENT***********************************************//
    public static boolean enrollaStudent(Scanner input) {

        boolean check;

        System.out.println("**********--CREATE A NEW STUDENT--*********");
        StudentDB stdb = new StudentDB();
        System.out.println("Please insert first name ");
        String name = validName(input);
        System.out.println("Please insert Last name");
        String lastname = validName(input);
        check = stdb.checkIfStudentExists(name, lastname);
        if (check == false) {
            System.out.println("you want to continue ?");
            System.out.println("1. Continue");
            System.out.println("2. Stop ");
            int choice = ValidInput.validInteger(input, 1, 2);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    return check;
            }
        }
        System.out.println("Please insert Birth Date ");
        Date dateFromString = validDate(input);
        System.out.println("Please insert Tuition Fees ");
        Double fees = validDouble(input, 0, 5000);

        check = stdb.insertStudent(name, lastname, dateFromString, fees);
        if (check == true) {
            System.out.println("//----------------------STUDENT SUCCESSFULLY ADDED!!-----------------------//");
        } else {
            return check;
        }
        ArrayList<Student> students = new ArrayList();
        students = stdb.getListOfStudents();
        int stud_id = students.get(students.size() - 1).getStud_id();

        AssignmentDB adb = new AssignmentDB();

        List<AssignmentCourses> assicourseList = new ArrayList();
        assicourseList = adb.getAssignmentsFromCourseId(coursePerStudent(input, students.get(students.size() - 1)));
        if (assicourseList.isEmpty()) {
            System.out.println(
                    "//----------------------------------STUDENT SUCCESSFULLY ENROLLED!!!!----------------------//");
            return check;

        }
        int i = 1;
        for (AssignmentCourses ac : assicourseList) {
            System.out.println(i + ":" + ac);
            i++;

        }

        System.out.println("Choose an assignment to add :  ");
        int num = ValidInput.validInteger(input, 1, assicourseList.size());

        assicourseList.get(num - 1).getAssiCourseId();

        check = adb.insertAssignmentToStudent(stud_id, assicourseList.get(num - 1).getAssiCourseId());
        printLine();
        System.out.println(
                "       //-------------------------------STUDENT SUCCESSFULLY ENROLLED!!!!----------------------//");
        printLine();
        return check;

    }

}
