package dataBase;

import static dataBase.AssignmentDB.DB_URL;
import models.Course;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import models.StudentCourses;
import static tools.PrintMsg.printLine;

public class CourseDB {

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/schooly_v1?serverTimeZone=UTC";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    //*************************GET LIST OF COURSES********************************//
    public ArrayList<Course> getListOfCourses() {

        ArrayList<Course> courseList = new ArrayList();

        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;
        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String query = "SELECT * FROM COURSES;";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int course_id = resultSet.getInt("COURSE_ID");

                String title = resultSet.getString("TITLE");

                String stream = resultSet.getString("STREAM");

                String type = resultSet.getString("TYPE");

                LocalDate startDate = resultSet.getDate("START_DATE").toLocalDate();

                LocalDate endDate = resultSet.getDate("END_DATE").toLocalDate();

                Course course = new Course(course_id, title, stream, type, startDate, endDate);

                courseList.add(course);

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
        return courseList;

    }
//*********************************PRINT LIST OF COURSES****************************************
    public static void printCourses() {
        CourseDB cdb = new CourseDB();

        ArrayList<Course> a = cdb.getListOfCourses();
        for (Course course : a) {
            System.out.println(course);
        }

    }
    //*********************************CHECK IF COURSE EXISTS*************************************//

    public boolean checkIfCourseExists(String title, String stream, String type) {
        boolean check = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "  select count(*)\n"
                    + " from courses\n"
                    + " where TITLE  = ? \n"
                    + " AND STREAM = ? \n"
                    + " AND TYPE = ? ";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, stream);
            preparedStatement.setString(3, type);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);

                if (count > 0) {
                    System.out.println("******" + title.toUpperCase() + " " + stream.toUpperCase() + " "+type.toUpperCase() +  "  ALREADY EXISTS*******");
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

//******************************ADD A COURSE**********************************************************//
    public boolean insertCourse(String title, String stream, String type, Date startDate, Date endDate) {

        boolean check = false;

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "INSERT INTO COURSES (TITLE, STREAM, TYPE, START_DATE, END_DATE) VALUES(?,?,?,?,?)";

            statement = connection.prepareStatement(query);

            statement.setString(1, title);
            statement.setString(2, stream);
            statement.setString(3, type);
            statement.setDate(4, startDate);
            statement.setDate(5, endDate);

            int result = statement.executeUpdate();
            if (result > 0) {
                check = true;
            }

        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
              System.out.println("               _______________ERROR_____________");
              System.out.println("**************THE EXACT SAME COURSE ALREADY EXISTS***************");
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

    //***************************************GET COURSE FROM STUDENT ID ***********************************//
    public List<StudentCourses> getCourseFromStudentId(int stud_id) {
        List<StudentCourses> studcourseList = new ArrayList();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = " select STUDENTS.STUDENT_ID,Students.first_name, STUDENTS.LAST_NAME, course_id, courses.title,courses.type\n"
                    + " from students_courses\n"
                    + " join courses on courses.course_id = students_courses.cour_id\n"
                    + " join students on students_courses.stud_id = students.student_id\n"
                    + " where students.student_id = ? ;";

            statement = connection.prepareStatement(query);

            statement.setInt(1, stud_id);

            resultSet = statement.executeQuery();
            System.out.println("COURSES THAT THIS STUDENT HAS ARE : ");
            printLine();

            while (resultSet.next()) {

                int studId = resultSet.getInt("STUDENT_ID");
                String studFirstName = resultSet.getString("FIRST_NAME");
                String studLastName = resultSet.getString("LAST_NAME");
                int courseId = resultSet.getInt("COURSE_ID");
                String title = resultSet.getString("TITLE");
                String type = resultSet.getString("TYPE");

                StudentCourses studcour = new StudentCourses(studId, studFirstName, studLastName, courseId, title, type);
                studcourseList.add(studcour);

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
        return studcourseList;

    }

}
