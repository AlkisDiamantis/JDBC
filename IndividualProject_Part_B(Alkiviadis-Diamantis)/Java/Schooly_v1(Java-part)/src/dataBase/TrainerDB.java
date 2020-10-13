package dataBase;

import static dataBase.CourseDB.DB_URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Trainer;
import static schoolDb_MainClass.SchoolDbMain.DB_URL;
import static tools.PrintMsg.printLine;

public class TrainerDB {

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/schooly_v1?serverTimeZone=UTC";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    //***************************GET LIST OF TRAINERS************************************//
    public ArrayList<Trainer> getListOfTrainers() {

        ArrayList<Trainer> trainerList = new ArrayList();

        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;
        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String query = "SELECT * FROM TRAINERS;";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int trainer_id = resultSet.getInt("TRAINER_ID");

                String first_name = resultSet.getString("FIRST_NAME");

                String last_name = resultSet.getString("LAST_NAME");

                String subject = resultSet.getString("SUBJECT");

                Trainer trainer = new Trainer(trainer_id, first_name, last_name, subject);

                trainerList.add(trainer);

            }

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();

            System.out.println("Something went wrong");

        } finally {
            if (resultSet != null) {
                try {

                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }

            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return trainerList;

    }
    //**********************************PRINT-TRAINERS*********************************************//

    public static void printTrainers() {
        TrainerDB traiDb = new TrainerDB();

        ArrayList<Trainer> a = traiDb.getListOfTrainers();
        for (Trainer trainer : a) {
            System.out.println(trainer);
        }

    }

    //*********************************CHECK IF TRAINER EXISTS*************************************//
    public boolean checkIfCourseExists(String firstName, String lastName) {
        boolean check = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "  select count(*)\n"
                    + " from trainers\n"
                    + " where FIRST_NAME  = ? \n"
                    + " AND LAST_NAME = ? \n";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);

                if (count > 0) {
                    System.out.println("******" + firstName.toUpperCase() + " " + lastName.toUpperCase() + "  ALREADY EXISTS*******");
                    check = false;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return check;
    }
    //**********************************ADD  TRAINERS***********************************//

    public boolean insertTrainer(String firstName, String lastName, String Subject) {

        boolean check = false;

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "INSERT INTO TRAINERS (FIRST_NAME, LAST_NAME, SUBJECT) VALUES(?,?,?)";

            statement = connection.prepareStatement(query);

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, Subject);

            int result = statement.executeUpdate();
            if (result > 0) {
                check = true;
            }

        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            System.out.println("                _______________ERROR!!___________");
            System.out.println("************THE EXACT SAME TRAINER ALREADY EXISTS****************");

            // e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return check;
    }
    //*********************************CHECK IF TRAINER ALREADY HAS THE COURSE*************************************//

    public boolean checkTrainerCourse(int trainerId, int courseId) {
        boolean check = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "select count(*)\n"
                    + " from trainer_courses \n"
                    + " where trai_id = ? \n"
                    + " and cour_id = ? ;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, trainerId);
            preparedStatement.setInt(2, courseId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);

                if (count > 0) {
                    printLine();
                    System.out.println("*************************Trainer already has the course!!!!!!!*************************");
                    printLine();
                    check = false;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return check;
    }

    //*********************************ADD TRAINER TO COURSE*****************************************************//
    public boolean insertTrainerToCourse(int trainerId, int courseId) {

        boolean check = false;

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "INSERT INTO trainer_courses (TRAI_ID, COUR_ID) VALUES(?,?)";

            statement = connection.prepareStatement(query);

            statement.setInt(1, trainerId);
            statement.setInt(2, courseId);

            int result = statement.executeUpdate();
            if (result > 0) {
                check = true;
            }

        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            System.out.println("");
            System.out.println("*********TRAINER ALREADY HAS THIS COURSE!!**************");

            //  e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return check;
    }
    //*******************************PRINT ALL THE TRAINERS PER COURSE********************************//

    public void printListOfTrainersPerCourse() {

        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;
        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String query = "  select courses.title, courses.type, trainers.first_name, trainers.Last_name\n"
                    + " from trainers\n"
                    + " join trainer_courses on trai_id = trainer_id\n"
                    + " join courses on course_id = cour_id;";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                String courseTitle = resultSet.getString("TITLE");

                String type = resultSet.getString("TYPE");

                String firstName = resultSet.getString("FIRST_NAME");

                String lastName = resultSet.getString("LAST_NAME");

                System.out.println("TITLE : " + courseTitle + ", TYPE : " + type + ", TRAINER FIRST_NAME : " + firstName + ", TRAINER LAST_NAME : " + lastName);

            }

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();

            System.out.println("Something went wrong");

        } finally {
            if (resultSet != null) {
                try {

                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }

            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
