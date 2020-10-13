package actions;

import dataBase.AssignmentDB;
import dataBase.CourseDB;
import dataBase.StudentDB;
import dataBase.TrainerDB;
import java.sql.Date;
import java.util.Scanner;
import validation.ValidInput;
import static validation.ValidInput.validDate;
import static validation.ValidInput.validDouble;
import static validation.ValidInput.validName;

public class Create {

    //*********************  CREATE STUDENT  *****************************//
    public static boolean createStudent(Scanner input) {
        boolean check;
        String firstname;
        String lastname;
        Date dateFromString;
        Double fees;
        StudentDB stdb = new StudentDB();
        System.out.println("********** Create Student *********");

        System.out.println("Please insert First name ");

        firstname = validName(input);

        System.out.println("Please insert Last name : ");
        lastname = validName(input);

        check = stdb.checkIfStudentExists(firstname, lastname);
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

        System.out.print("Please insert Birth Date ");
        dateFromString = validDate(input);

        System.out.print("Please insert Tuition Fees ");
        fees = validDouble(input, 0, 5000);

        boolean a;
        a = stdb.insertStudent(firstname, lastname, dateFromString, fees);
        if (a == true) {
            System.out.println("//-------------STUDENT SUCCESSFULLY ADDED --------------//");
        }
        return check;

    }
    //**********************CREATE COURSE LIST  METHOD******************************// 

    public static boolean createCourse(Scanner input) {
        boolean checker;
        CourseDB cdb = new CourseDB();
        System.out.println("********** Create Course *********");
        String title;
        do {
            System.out.print("Please insert Title : ");
            title = input.nextLine().trim();
        } while (title.trim().isEmpty());
        String stream;
        do{
        System.out.print("Please insert Stream : ");
        stream = input.nextLine().trim();
        }while(stream.trim().isEmpty());
        String type;
        do{
        System.out.print("Please insert Type : ");
        type = input.nextLine().trim();
        }while(type.trim().isEmpty());
        
        boolean check;
        check = cdb.checkIfCourseExists(title, stream, type);
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

        System.out.println("Please insert Start Date ");
        Date startingDate = validDate(input);

        System.out.println("Please insert End Date  ");
        Date endingDate = validDate(input);

        checker = cdb.insertCourse(title, stream, type, startingDate, endingDate);
        if (checker == true) {
            System.out.println("//--------------COURSE SUCCESSFULLY ADDED-------------//");
        }
        return checker;
    }
//    //************************** CREATE TRAINER LIST METHOD *****************************//

    public static boolean createTrainer(Scanner input) {
        boolean checker;
        TrainerDB tdb = new TrainerDB();
        System.out.println("********** Create Trainer *********");
        System.out.println("Please insert First Name ");
        String firstName = validName(input);
        System.out.println("Please insert Last Name ");
        String lastName = validName(input);
        boolean check;
        check = tdb.checkIfCourseExists(firstName, lastName);
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
        String subject;
        do{
        System.out.print("Please insert Subject : ");
        subject = input.nextLine().trim();
        }while(subject.trim().isEmpty());

        checker = tdb.insertTrainer(firstName, lastName, subject);
        if (checker == true) {
            System.out.println("//-----------------TRAINER SUCCESSFULLY ADDED-------------//");
        }
        return checker;

    }
//************************* CREATE ASSIGMENT LIST METHOD ******************************//

    public static boolean createAssignment(Scanner input) {
        boolean checker;
        AssignmentDB adb = new AssignmentDB();
        System.out.println("********** Create Assigment *********");
        String title;
        do{
        System.out.print("Please insert Title : ");
        title = input.nextLine().trim();
        }while(title.trim().isEmpty());
        String description;
        do{
        System.out.print("Please insert Description : ");
        description = input.nextLine().trim();
        }while(description.trim().isEmpty());
        boolean check;
        check = adb.checkIfAssignmentExists(title, description);
        if (check == false) {
            System.out.println("----------------------You cannot enter the same assignment Twice----------------");
            return check;
        }

        System.out.print("Please insert Oral Mark ");
        double oralMark = validDouble(input, 0, 50);
        System.out.print("Please insert Total Mark ");
        double totalMark = validDouble(input, 0, 100);

        checker = adb.insertAssignment(title, description, oralMark, totalMark);
        if (checker == true) {
            System.out.println("//-----------------ASSIGNMENT SUCCESSFULLY ADDED-------------//");
        }
        return checker;
    }

}
