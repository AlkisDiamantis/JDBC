package actions;

import dataBase.AssignmentDB;
import static dataBase.AssignmentDB.printAssignments;
import dataBase.CourseDB;
import static dataBase.CourseDB.printCourses;
import dataBase.StudentDB;
import static dataBase.StudentDB.printStudents;
import dataBase.TrainerDB;
import static dataBase.TrainerDB.printTrainers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import models.AssignmentCourses;
import models.AssignmentStudent;
import models.Course;
import models.Student;
import models.StudentCourses;
import static tools.PrintMsg.printLine;
import validation.ValidInput;
import static validation.ValidInput.validDouble;

public class Menu {

    public static int mainMenu(Scanner input) {
        System.out.println("***********************************************");
        System.out.println("******************-MAIN MENU-******************");
        System.out.println("***********************************************");
        System.out.println("Press :");
        System.out.println("1. -ENROLL- (a new Student)");
        System.out.println("2. --ADD-- ");
        System.out.println("3. -UPDATE- Course");
        System.out.println("4. -UPDATE- Student");
        System.out.println("5. -PRINT- Details ");
        System.out.println("6. To EXIT ");
        int choice = ValidInput.validInteger(input, 1, 6);
        return choice;
    }

    //********************************----LISTS MENU-------***********************************************//
    public static void printListOfChoice(Scanner input) {

        boolean start = true;
        while (start) {
            System.out.println("***************************************************************************");
            System.out.println("*******************************-DETAILS MENU-******************************");
            System.out.println("Press :");
            System.out.println("1. To see list of all the students ");
            System.out.println("2. To see list of all the courses ");
            System.out.println("3. To see list of all the trainers ");
            System.out.println("4. To see list of all the assignments ");
            System.out.println("5. To see list of all  the students per course ");
            System.out.println("6. To see list of all  the trainers per course ");
            System.out.println("7. To see list of all  the assignments per course ");
            System.out.println("8. To see list of all the assignments per course per student ");
            System.out.println("9. To see list of students that belong to more than one courses  ");
            System.out.println("10. To go back to MAIN MENU ");
            int choice = ValidInput.validInteger(input, 1, 10);
            switch (choice) {
                case 1:

                    System.out.println("----------------------------------------- THE LIST OF STUDENTS -----------------------------------------");
                    printLine();

                    printStudents();
                    printLine();
                    break;
                case 2:
                    System.out.println("----------------------------------------- THE LIST OF COURSES ------------------------------------------");
                    printLine();
                    printCourses();
                    printLine();
                    break;
                case 3:
                    System.out.println("---------------------------------------- THE LIST OF TRAINERS ------------------------------------------");
                    printLine();
                    printTrainers();
                    printLine();
                    break;
                case 4:
                    System.out.println("----------------------------------------- THE LIST OF ASSIGMENTS --------------------------------------");
                    printLine();
                    printAssignments();
                    printLine();
                    break;
                case 5:
                    System.out.println("----------------------------------------- THE LIST OF STUDENTS PER COURSE --------------------------------------");
                    StudentDB stdb = new StudentDB();
                    printLine();
                    stdb.printListOfStudentsPerCourse();
                    printLine();
                    break;
                case 6:
                    System.out.println("----------------------------------------- THE LIST OF TRAINERS PER COURSE --------------------------------------");
                    TrainerDB tdb = new TrainerDB();
                    printLine();
                    tdb.printListOfTrainersPerCourse();
                    printLine();
                    break;

                case 7:
                    System.out.println("----------------------------------------- THE LIST OF ASSIGNMENTS PER COURSE --------------------------------------");
                    AssignmentDB adb = new AssignmentDB();
                    printLine();
                    adb.printListOfAssignmentsPerCourse();
                    printLine();
                    break;
                case 8:
                    System.out.println("----------------------------------------- THE LIST OF ASSIGNMENTS PER COURSE PER STUDENT --------------------------------------");
                    AssignmentDB adb2 = new AssignmentDB();
                    printLine();
                    adb2.printListOfAssignmentsPerCoursePerStudent();
                    printLine();
                    break;
                case 9:
                    System.out.println("----------------------------------------- THE LIST OF STUDENTS WITH MORE THAN ONE COURSES  --------------------------------------");
                    StudentDB stdb2 = new StudentDB();
                    Map<Student, Integer> studentMap = stdb2.getListOfStudentsToMoreThanOneCourse();
                
                    printLine();
                   studentMap.forEach((student, numOfCourses)
                          -> System.out.println("First Name: " + student.getFirstName()+", Last Name: "+student.getLastName()+", Number of Courses: "+ numOfCourses)
                   );
                    printLine();
                    break;
                case 10:
                    start = false;
                    break;
            }
        }

    }
    //*******************************--ADD--************************************************//

    public static int menuChoice(Scanner input) {

        System.out.println("************************************************");
        System.out.println("********************-ADD MENU-******************");
        System.out.println("************************************************");

        System.out.println("Press :");
        System.out.println("1. To add students");
        System.out.println("2. To add courses");
        System.out.println("3. To add trainers");
        System.out.println("4. To add Assigments");
        System.out.println("5. To go back to MAIN MENU ");

        return ValidInput.validInteger(input, 1, 5);
    }

    public static void menuAddElements(Scanner input) {

        boolean start = true;
        while (start) {
            int choice = menuChoice(input);
            switch (choice) {
                case 1:
                    //***********STUDENT LOOP*******//
                    System.out.println("*****************************************************");
                    System.out.println("****************** ADD STUDENTS *********************");

                    System.out.println("How Many Students do you want to add ? ");
                    int count = ValidInput.validInteger(input, 0, 10);
                    for (int i = 0; i < count; i++) {
                        Create.createStudent(input);

                    }
                    break;
                case 2:
                    //************COURSE LOOP********//
                    System.out.println("*****************************************************");
                    System.out.println("******************* ADD COURSES *********************");

                    System.out.println("How many Courses do you want to add?  ");
                    count = ValidInput.validInteger(input, 0, 5);
                    for (int i = 0; i < count; i++) {
                        Create.createCourse(input);

                    }
                    break;
                case 3:
                    //***********TRAINER LOOP***********//
                    System.out.println("*****************************************************");
                    System.out.println("******************* ADD TRAINERS ********************");

                    System.out.println("How many Trainers do you want to add? ");
                    count = ValidInput.validInteger(input, 0, 5);
                    for (int i = 0; i < count; i++) {
                        Create.createTrainer(input);

                    }
                    break;
                case 4:
                    //*****************ASSIGMENT LOOP************//
                    System.out.println("*****************************************************");
                    System.out.println("****************** ADD ASSIGMENTS *******************");

                    System.out.println("How many Assigments  do you want to add?  ");
                    count = ValidInput.validInteger(input, 0, 5);
                    for (int i = 0; i < count; i++) {
                        Create.createAssignment(input);

                    }
                    break;
                case 5:
                    start = false;

            }
        }

    }
//********************************************UPDATE A COURSE****************************************************************//

    public static void menuConstractACourse(Scanner input) {

        boolean loopa = true;
        while (loopa) {
            System.out.println("************************ UPDATE COURSES MENU *************************");

            System.out.println("----------------------------- COURSE LIST -----------------------------");
            System.out.println("");

            CourseDB cdb = new CourseDB();
            ArrayList<Course> courseList = cdb.getListOfCourses();
            for (int i = 0; i < courseList.size(); i++) {
                System.out.println((i + 1) + "." + courseList.get(i));
            }
            System.out.println("Choose a course to Edit : ");

            if (courseList.isEmpty()) {
                System.out.println("*********The Course List is Empty***********");
                System.out.println("--Add a Course Manually Or add a Course from Synthetic data--");

                break;
            }

            int num = ValidInput.validInteger(input, 1, courseList.size());

            boolean loop = true;

            while (loop) {
                System.out.println("**********************************************************************");
                System.out.println("************************-" + courseList.get(num - 1).getTitle().toUpperCase() + " " + courseList.get(num - 1).getType() + "-********************************");

                System.out.println("Press :");
                System.out.println("1. Add students to " + courseList.get(num - 1).getTitle());
                System.out.println("2. Add trainers to " + courseList.get(num - 1).getTitle());
                System.out.println("3. Add Assigments to  " + courseList.get(num - 1).getTitle());
                System.out.println("4. Go back to Course List ");

                System.out.println("5. Exit Constraction ");
                int choose = ValidInput.validInteger(input, 1, 5);
                switch (choose) {
                    case 1: {
                        Constraction.studensPerCourse(input, courseList.get(num - 1));
                    }
                    break;
                    case 2: {
                        Constraction.trainerPerCourse(input, courseList.get(num - 1));
                    }
                    break;
                    case 3: {
                        Constraction.assigmentPerCourse(input, courseList.get(num - 1));
                    }
                    break;
                    case 4: {
                        loop = false;
                    }
                    break;
                    case 5: {
                        loop = false;
                        loopa = false;
                    }
                    break;

                }
            }
        }
    }
    //*****************************************************UPDATE A  STUDENT***************************************//

    public static void menuConstractAStudent(Scanner input) {

        boolean loopa = true;
        while (loopa) {
            System.out.println("**********************-EDIT STUDENTS MENU-***********************");

            System.out.println("------------------------- STUDENTS LIST --------------------------");
            System.out.println("");
            System.out.println("Choose a student to Edit : ");
            StudentDB sdb = new StudentDB();
            ArrayList<Student> studentList = sdb.getListOfStudents();
            for (int i = 0; i < studentList.size(); i++) {
                System.out.println((i + 1) + "." + studentList.get(i));
            }

            if (studentList.isEmpty()) {
                System.out.println("*********The Student List is Empty***********");
                System.out.println("--Add a Student Manually Or add a Student from Synthetic data--");

                break;
            }

            int num = ValidInput.validInteger(input, 1, studentList.size());

            boolean loop = true;

            while (loop) {
                System.out.println("**********************************************************************");
                System.out.println("************************-" + studentList.get(num - 1).getFirstName().toUpperCase() + " " + studentList.get(num - 1).getLastName().toUpperCase() + "-********************************");

                System.out.println("Press :");
                System.out.println("1. Add Courses to " + studentList.get(num - 1).getFirstName() + " " + studentList.get(num - 1).getLastName());
                System.out.println("2. Add Assigments to  " + studentList.get(num - 1).getFirstName() + " " + studentList.get(num - 1).getLastName());
                System.out.println("3. Add Marks to  " + studentList.get(num - 1).getFirstName() + " " + studentList.get(num - 1).getLastName());
                System.out.println("4. Go back to Student List ");
                System.out.println("5. Exit Constraction ");
                int choose = ValidInput.validInteger(input, 1, 5);
                switch (choose) {
                    case 1: {
                        Constraction.coursePerStudent(input, studentList.get(num - 1));
                    }
                    break;
                    case 2: {

                        CourseDB cdb = new CourseDB();

                        List<StudentCourses> studcoursList = new ArrayList();
                        studcoursList = cdb.getCourseFromStudentId(studentList.get(num - 1).getStud_id());
                        int i = 1;
                        for (StudentCourses sclist : studcoursList) {
                            System.out.println(i + "." + sclist);
                            i++;
                        }
                        printLine();
                        System.out.println("PLEASE CHOOSE A COURSE TO SEE COURSE'S ASSIGNMENTS : ");
                        int choice = ValidInput.validInteger(input, 1, studcoursList.size());
                        AssignmentDB adb = new AssignmentDB();
                        List<AssignmentCourses> aclist = new ArrayList();
                        aclist = adb.getAssignmentsFromCourseId(studcoursList.get(choice - 1).getCourseId());
                        int j = 1;
                        for (AssignmentCourses assicourses : aclist) {
                            System.out.println(j + "." + assicourses);
                            j++;
                        }
                        if (aclist.isEmpty()) {
                            System.out.println("---------------THIS COURSE HAS NO ASSIGNMENTS----------------");
                            System.out.println("-----UPDATE THE COURSE TO ADD ASSIGMENT TO THE COURSE--------");
                            break;

                        }
                        printLine();
                        System.out.println("Please choose assignment to add : ");
                        int choice2 = ValidInput.validInteger(input, 1, aclist.size());
                        int assi_course_id = aclist.get(choice2 - 1).getAssiCourseId();
                        boolean check;
                        check = adb.insertAssignmentToStudent(studentList.get(num - 1).getStud_id(), assi_course_id);
                        if (check) {
                            System.out.println("---------------------------- ASSIGNMENT SUCCEFULLY ADDED TO STUDENT --------------------------------");

                        }

                    }
                    break;

                    case 3: {
                        AssignmentDB adb = new AssignmentDB();
                        List<AssignmentStudent> assistud = new ArrayList();
                        assistud = adb.getAssignmentsFromStudentId(studentList.get(num - 1).getStud_id());
                        int i = 1;
                        for (AssignmentStudent as : assistud) {
                            System.out.println(i + "." + " " + as);
                            i++;
                        }
                        if (assistud.isEmpty()) {
                            System.out.println("-------------- THIS STUDENT HAS NO ASSIGNMENTS ----------------");
                            System.out.println("-------------- UPDATE STUDENT TO ADD ASSIGMENT ----------------");
                            break;

                        }
                        printLine();
                        System.out.println("Choose an assignment to add marks : ");
                        int choice = ValidInput.validInteger(input, 1, assistud.size());
                        printLine();
                        System.out.println("ENTER MARK TO: " + studentList.get(num - 1).getFirstName() + "  " + studentList.get(num - 1).getLastName() + ", COURSE: " + assistud.get(choice - 1).getCourseTitle() + ", ASSIGNMENT: " + assistud.get(choice - 1).getAssiTitle());

                        double mark = validDouble(input, 0, 100);
                        System.out.println("ENTER ORAL MARK TO : " + studentList.get(num - 1).getFirstName() + "  " + studentList.get(num - 1).getLastName() + ", COURSE: " + assistud.get(choice - 1).getCourseTitle() + ", ASSIGNMENT : " + assistud.get(choice - 1).getAssiTitle());

                        double oralMark = validDouble(input, 0, 100);
                        adb.insertMarkToStudentAssignment(studentList.get(num - 1).getStud_id(), assistud.get(choice - 1).getAssi_courseId(), mark, oralMark);
                        System.out.println("*********************************** GREAT MARKS ARE NOW SAVED!! *******************************");

                    }
                    break;
                    case 4: {
                        loop = false;
                    }
                    break;
                    case 5: {
                        loop = false;
                        loopa = false;
                    }
                    break;

                }
            }
        }
    }
}
