package validation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidInput {

    //****************************************INTEGER VALIDATION**********************************//
    public static int validInteger(Scanner input, int from, int to) {

        int x;
        do {
            System.out.print("Enter Number (between " + from + "-" + to + ") Here : ");

            while (!input.hasNextInt()) {
                System.out.print("Please enter a number between " + from + "-" + to + ": ");
                input.next();
                input.nextLine();
            }

            x = input.nextInt();
            input.nextLine();
        } while (x < from || x > to);

        return x;
    }
    //**************************************DOUBLE VALIDATION********************************//

    public static double validDouble(Scanner input, double from, double to) {

        double x;
        do {
            System.out.print("Enter Number (between " + from + "-" + to + ") Here : ");

            while (!input.hasNextDouble()) {
                System.out.print("Please enter a number between " + from + "-" + to + ": ");
                input.next();
                input.nextLine();

            }

            x = input.nextDouble();
            input.nextLine();
        } while (x < from || x > to);

        return x;
    }
//*****************************************NAME VALIDATION********************************************//

    public static String validName(Scanner input) {
        boolean check = false;
        String name = "";

        do {
            System.out.print("Enter : ");
            name = input.next();
            if (Pattern.matches("[a-zA-Z]+", name) == false) {
                System.out.println("-----------------Invalid input!!!---------------");
                System.out.println("****This field should only contain letters!!****");
                input.nextLine();

            }

            check = Pattern.matches("[a-zA-Z]+", name);

        } while (check == false);
        input.nextLine();

        return name;
    }
//*****************************************DATE VALIDATION*************************************************//

    public static Date validDate(Scanner input) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        boolean check = true;
        LocalDate localdate = null;
        do {
            System.out.print("enter date (yyyy/MM/dd) : ");
            String dateFromString = input.next();
            try {
                localdate = LocalDate.parse(dateFromString, dtf);
                check = false;
            } catch (Exception e) {
                System.out.println("----------------Invalid date Format!!------------------");
                System.out.println("*********Please insert Date in the right Format********");
                input.nextLine();
            }

        } while (check);
        Date sqldate = Date.valueOf(localdate);
        input.nextLine();
        return sqldate;

    }
}
