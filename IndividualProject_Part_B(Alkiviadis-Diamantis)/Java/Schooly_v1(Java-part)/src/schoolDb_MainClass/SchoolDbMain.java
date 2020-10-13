package schoolDb_MainClass;

import actions.Constraction;
import actions.Menu;
import java.util.Scanner;
import static tools.PrintMsg.*;

public class SchoolDbMain {

    public static final String DB_URL = "jdbc:mysql://localhost/schooly_v1?serverTimeZone=UTC";
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        introMsg();
        
        boolean loop = true;
        while (loop) {

            int choice = Menu.mainMenu(input);

            switch (choice) {
                case 1:
                    Constraction.enrollaStudent(input);
                    break;
                case 2:
                    Menu.menuAddElements(input);
                    break;

                case 3:
                    Menu.menuConstractACourse(input);
                    break;
                case 4:
                    Menu.menuConstractAStudent(input);
                    break;
                case 5:
                    Menu.printListOfChoice(input);
                    break;
                case 6:
                    loop = false;
                    break;

            }
            

        }
        outroMsg();
        input.close();
    }
}
