package dataBase;

import models.Student;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.StudentCourses;
import static tools.PrintMsg.printLine;
import static tools.PrintMsg.warningMsg;

public class StudentDB {

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/schooly_v1?serverTimeZone=UTC";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

//*******************************GET LIST OF STUDENS*******************//
    public ArrayList<Student> getListOfStudents() {

        ArrayList<Student> studentList = new ArrayList();

        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;
        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String query = "SELECT * FROM STUDENTS;";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int student_id = resultSet.getInt("STUDENT_ID");

                String first_name = resultSet.getString("FIRST_NAME");

                String last_name = resultSet.getString("LAST_NAME");

                LocalDate date_birth = resultSet.getDate("DATE_OF_BIRTH").toLocalDate();

                double fees = resultSet.getDouble("TUITION_FEES");

                Student student = new Student(student_id, first_name, last_name, date_birth, fees);

                studentList.add(student);

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
        return studentList;

    }
//********************PRINT LIST OF STUDENTS*********************************//

    public static void printStudents() {
        StudentDB studDb = new StudentDB();
        ArrayList<Student> a = studDb.getListOfStudents();
        for (Student students : a) {
            System.out.println(students);
        }

    }
    //*********************************CHECK IF STUDENT EXISTS*************************************//

    public boolean checkIfStudentExists(String firstName, String lastName) {
        boolean check = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = " select count(*)\n"
                    + " from STUDENTS\n"
                    + " where FIRST_NAME  = ? \n"
                    + " and LAST_NAME = ? \n";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);

                if (count > 0) {
                    warningMsg();
                    System.out.println("************************ " + firstName.toUpperCase() + " " + lastName.toUpperCase() + " ALREADY EXISTS*******************");
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

    //*********************************ADD STUDENT *****************************************************//
    public boolean insertStudent(String name, String LastName, Date date, Double fees) {

        boolean check = false;

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "INSERT INTO STUDENTS (FIRST_NAME, LAST_NAME,DATE_OF_BIRTH,TUITION_FEES) VALUES(?,?,?,?)";

            statement = connection.prepareStatement(query);

            statement.setString(1, name);
            statement.setString(2, LastName);
            statement.setDate(3, date);
            statement.setDouble(4, fees);

            int result = statement.executeUpdate();
            if (result > 0) {
                check = true;
            }

        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            warningMsg();

            System.out.println("*******************The exact same student already exists!!!********************");
            //e.printStackTrace();

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
    //*********************************CHECK IF STUDENT ALREADY HAS THE COURSE*************************************//

    public boolean checkStudentCourse(int sudentId, int courseId) {
        boolean check = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "select count(*)\n"
                    + " from students_courses \n"
                    + " where stud_id = ? \n"
                    + " and cour_id = ? ;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, sudentId);
            preparedStatement.setInt(2, courseId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);

                if (count > 0) {
                    printLine();
                    System.out.println("********************Student already has the course!!!!!!!************************");
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
    //*********************************ADD STUDENT TO COURSE*****************************************************//

    public boolean insertStudentToCourse(int studentId, int courseId) {

        boolean check = false;

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "INSERT INTO students_courses (STUD_ID, COUR_ID) VALUES(?,?)";

            statement = connection.prepareStatement(query);

            statement.setInt(1, studentId);
            statement.setInt(2, courseId);

            int result = statement.executeUpdate();
            if (result > 0) {
                check = true;
            }

        } catch (ClassNotFoundException | SQLException | NullPointerException e) {

            e.printStackTrace();
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

    //******************GET LIST OF ALL THE STUDENTS PER COURSE*************************//
    public List<StudentCourses> getListOfStudentsPerCourse() {
        List<StudentCourses> studcoursList = new ArrayList();

        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;
        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String query = " select STUDENTS.STUDENT_ID,students.first_name,students.last_name,COURSES.COURSE_ID, courses.title ,courses.TYPE \n"
                    + " from students\n"
                    + " join students_courses on students.STUDENT_ID = students_courses.stud_id\n"
                    + " join courses on course_id = students_courses.COUR_ID;";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int stud_id = resultSet.getInt("STUDENT_ID");

                String firstName = resultSet.getString("FIRST_NAME");

                String lastName = resultSet.getString("LAST_NAME");

                int course_id = resultSet.getInt("COURSE_ID");

                String courseTitle = resultSet.getString("TITLE");

                String type = resultSet.getString("TYPE");

                StudentCourses studCourse = new StudentCourses(stud_id, firstName, lastName, course_id, courseTitle, type);
                studcoursList.add(studCourse);

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
        return studcoursList;

    }
    //********************PRINT LIST OF ALL THE STUDENTS PER COURSE*********************************//

    public static void printListOfStudentsPerCourse() {
        StudentDB studDb = new StudentDB();
        List<StudentCourses> a = studDb.getListOfStudentsPerCourse();
        for (StudentCourses studcourses : a) {
            System.out.println(studcourses);
        }

    }
//*****************LIST OF STUDENT TO MORE TAHAN ONE COURSE***********

    public Map<Student, Integer> getListOfStudentsToMoreThanOneCourse() {

        Map<Student, Integer> studentMap = new HashMap<>();

        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;
        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String query = " select  students.first_name , students.last_name,DATE_OF_BIRTH,TUITION_FEES,COUNT(*) AS NUMMBER_OF_COURSES\n"
                    + " from students\n"
                    + " join students_courses on students.STUDENT_ID = students_courses.stud_id\n"
                    + " join courses on course_id = students_courses.COUR_ID\n"
                    + " GROUP BY STUD_ID\n"
                    + " HAVING COUNT(*)>1";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                String firstName = resultSet.getString("FIRST_NAME");

                String lastName = resultSet.getString("LAST_NAME");

                LocalDate date_birth = resultSet.getDate("DATE_OF_BIRTH").toLocalDate();

                Double tuitionFees = resultSet.getDouble("TUITION_FEES");

                int numOfCourses = resultSet.getInt("NUMMBER_OF_COURSES");

                Student student = new Student(firstName, lastName, date_birth, tuitionFees);

                studentMap.put(student, numOfCourses);

                //System.out.println("FIRST NAME : " + firstName + ", LAST NAME  : " + lastName + ", NUMBER OF COURSES : " + numOfCourses);
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
        return studentMap;
    }

}
