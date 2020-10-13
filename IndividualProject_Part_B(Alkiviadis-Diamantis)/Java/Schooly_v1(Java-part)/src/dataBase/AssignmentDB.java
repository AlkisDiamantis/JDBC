package dataBase;

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
import models.Assignment;
import models.AssignmentCourses;
import models.AssignmentStudent;
import static tools.PrintMsg.printLine;
import static tools.PrintMsg.warningMsg;

public class AssignmentDB {

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/schooly_v1?serverTimeZone=UTC";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    //****************************GET ASSIGNMENTS*************************************
    public ArrayList<Assignment> getListOfAssignments() {

        ArrayList<Assignment> AssignmentList = new ArrayList();

        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;
        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String query = "SELECT * FROM ASSIGNMENTS;";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int assignment_id = resultSet.getInt("ASSIGMENT_ID");

                String title = resultSet.getString("TITLE");

                String description = resultSet.getString("DESCRIPTION");

                Double oral_mark = resultSet.getDouble("ORAL_MARK");

                double total_mark = resultSet.getDouble("TOTAL_MARK");

                Assignment assignment = new Assignment(assignment_id, title, description, total_mark, oral_mark);

                AssignmentList.add(assignment);

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
        return AssignmentList;

    }
    //***********************************PRINT-ASSIGNMENTS*****************************************

    public static void printAssignments() {
        AssignmentDB adb = new AssignmentDB();
        ArrayList<Assignment> a = adb.getListOfAssignments();
        for (Assignment assi : a) {
            System.out.println(assi);
        }

    }
        //*********************************CHECK IF ASSIGNMENT ALREADY EXISTS*************************************//

    public boolean checkIfAssignmentExists(String title, String description) {
        boolean check = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "  select count(*)\n"
                    + " from assignments \n"
                    + " where TITLE  = ? \n"
                    + " AND DESCRIPTION = ? \n";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);

                if (count > 0) {
                    printLine();
                    System.out.println("*****************" + title.toUpperCase() + ", " + description.toUpperCase() + "  ALREADY EXISTS****************");
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
//********************************************ADD ASSIGNMENT**************************************************//

    public boolean insertAssignment(String title, String description, Double oralMark, double totalMark) {

        boolean check = false;

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "INSERT INTO ASSIGNMENTS (TITLE, DESCRIPTION, ORAL_MARK, TOTAL_MARK) VALUES(?,?,?,?)";

            statement = connection.prepareStatement(query);

            statement.setString(1, title);
            statement.setString(2, description);
            statement.setDouble(3, oralMark);
            statement.setDouble(4, totalMark);

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
    //*******************************GET ALL THE ASSIGNMENT PER COURSE********************************//

    public List<AssignmentCourses> getListOfAssignmentsPerCourse() {

        List<AssignmentCourses> assiCourseList = new ArrayList();

        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;
        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String query = " select assigments_courses.assi_course_id, courses.course_id, courses.title,courses.type,assignments.ASSIGMENT_ID, assignments.title AS A , assignments.DESCRIPTION , ASSIGMENTS_COURSES.SUBMISION_DATE\n"
                    + " from assignments\n"
                    + " join assigments_courses on assigments_courses.assignment_id = assignments.assigment_id\n"
                    + " join courses on courses.course_id = assigments_courses.cour_id\n"
                    + " ORDER BY courses.title;";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int assiCourseId = resultSet.getInt("ASSI_COURSE_ID");

                int courseId = resultSet.getInt("COURSE_ID");

                String courseTitle = resultSet.getString("TITLE");

                String courseType = resultSet.getString("TYPE");

                int assignmentId = resultSet.getInt("ASSIGMENT_ID");

                String assignmentTitle = resultSet.getString("A");

                String description = resultSet.getString("DESCRIPTION");

                LocalDate submisionDate = resultSet.getDate("SUBMISION_DATE").toLocalDate();

                AssignmentCourses assicourse = new AssignmentCourses(assiCourseId, courseId, courseTitle, courseType, assignmentId, assignmentTitle, description, submisionDate);
                assiCourseList.add(assicourse);

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
        return assiCourseList;

    }

    //********************************PRINT ASSIGNMENT PER COURSE *******************************//
    public static void printListOfAssignmentsPerCourse() {
        AssignmentDB adb = new AssignmentDB();
        List<AssignmentCourses> a = adb.getListOfAssignmentsPerCourse();
        for (AssignmentCourses assiCourse : a) {
            System.out.println(assiCourse);
        }

    }

    //***********************PRINT ASSIGNMENT  PER COURSE PER STUDENT*******************//
    public void printListOfAssignmentsPerCoursePerStudent() {

        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;
        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String query = " select students.first_name , students.last_name , courses.title AS COURSE ,courses.type, assignments.title AS ASSIGNMENT,student_assignment.MARK,student_assignment.oral_mark\n"
                    + " from students\n"
                    + " JOIN student_assignment ON student_assignment.student_id = students.STUDENT_ID\n"
                    + " join assigments_courses on assigments_courses.assi_course_id = student_assignment.assi_course_id\n"
                    + " join courses on courses.COURSE_ID = assigments_courses.COUR_ID\n"
                    + " join assignments on assignments.ASSIGMENT_ID = assigments_courses.ASSIGNMENT_ID\n"
                    + " ORDER BY students.first_name;";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                String firstName = resultSet.getString("FIRST_NAME");

                String lastName = resultSet.getString("LAST_NAME");

                String course = resultSet.getString("COURSE");

                String type = resultSet.getString("TYPE");

                String assgnment = resultSet.getString("ASSIGNMENT");

                double oralMark = resultSet.getDouble("ORAL_MARK");

                double totalMark = resultSet.getDouble("MARK");

                System.out.println("FIRST NAME:" + firstName + ", LAST NAME:" + lastName + ", COURSE:" + course + ", TYPE:" + type + ", ASSIGNMENT TITLE:" + assgnment + ", ORAL MARK:" + oralMark + ", TOTAL MARK:" + totalMark);

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

    //*********************************ADD ASSIGNMENT TO COURSE*****************************************************//

    public boolean insertAssignmentToCourse(int assignmentID, int courseId, Date submisionDate) {

        boolean check = false;

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "INSERT INTO ASSIGMENTS_COURSES (ASSIGNMENT_ID, COUR_ID,SUBMISION_DATE) VALUES(?,?,?)";

            statement = connection.prepareStatement(query);

            statement.setInt(1, assignmentID);
            statement.setInt(2, courseId);
            statement.setDate(3, submisionDate);

            int result = statement.executeUpdate();
            if (result > 0) {
                check = true;
            }

        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            System.out.println("");
            System.out.println("**********ASSIGNMENT ALREADY IN THAT COURSE!!******************");

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
 
       //*********************************CHECK IF ASSIGNMENT ALREADY HAS THE COURSE*************************************//

    public boolean checkAssignmentCourse(int assiId, int courseId) {
        boolean check = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "select count(*)\n"
                    + " from assigments_courses \n"
                    + " where ASSIGNMENT_ID = ? \n"
                    + " and cour_id = ? ;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, assiId);
            preparedStatement.setInt(2, courseId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);

                if (count > 0) {
                    printLine();
                    System.out.println("****************** THIS COURSE ALREADY HAS THIS ASSIGNMENT!!! ******************");
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
       //*******************************GET ASSIGNMENT FROM COURSE***********************************//

    public List<AssignmentCourses> getAssignmentsFromCourseId(int course_id) {
        List<AssignmentCourses> assicourseList = new ArrayList();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = " select assigments_courses.assi_course_id, courses.course_id, courses.title,courses.type,assignments.ASSIGMENT_ID, assignments.title AS A , assignments.DESCRIPTION, ASSIGMENTS_COURSES.SUBMISION_DATE\n"
                    + " from assignments\n"
                    + " join assigments_courses on assigments_courses.assignment_id = assignments.assigment_id\n"
                    + " join courses on courses.course_id = assigments_courses.cour_id\n"
                    + " WHERE COURSES.COURSE_ID = ?;";

            statement = connection.prepareStatement(query);

            statement.setInt(1, course_id);

            resultSet = statement.executeQuery();
            System.out.println("THE ASSIGNMENTS THAT THIS COURSE HAS ARE : ");
            printLine();

            while (resultSet.next()) {
                int assiCourseId = resultSet.getInt("ASSI_COURSE_ID");

                int courseId = resultSet.getInt("COURSE_ID");

                String courseTitle = resultSet.getString("TITLE");

                String courseType = resultSet.getString("TYPE");

                int assignmentId = resultSet.getInt("ASSIGMENT_ID");

                String assignmentTitle = resultSet.getString("A");

                String description = resultSet.getString("DESCRIPTION");

                LocalDate submisionDate = resultSet.getDate("SUBMISION_DATE").toLocalDate();

                AssignmentCourses assicourse = new AssignmentCourses(assiCourseId, courseId, courseTitle, courseType, assignmentId, assignmentTitle, description, submisionDate);
                assicourseList.add(assicourse);

            }
            if(assicourseList.isEmpty()){
                warningMsg();
                System.out.println("//--------------------- THIS COURSE HAS CURRENTLY NO ASSIGNMENTS --------------------//");
                System.out.println("--------------------------UPDATE THE COURSE TO ADD ASSIGNMENT---------------------------");
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
        return assicourseList;

    }
    //*********************************ADD ASSIGNMENT TO STUDENT*****************************************************//

    public boolean insertAssignmentToStudent(int student_id, int assi_course_id) {

        boolean check = false;

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "INSERT INTO STUDENT_ASSIGNMENT (STUDENT_ID, ASSI_COURSE_ID) VALUES(?,?)";

            statement = connection.prepareStatement(query);

            statement.setInt(1, student_id);
            statement.setInt(2, assi_course_id);

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
    //*******************************GET ASSIGNMENT FROM STUDENT***********************************//

    public List<AssignmentStudent> getAssignmentsFromStudentId(int student_id) {
        List<AssignmentStudent> assistudList = new ArrayList();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = " select assigments_courses.assi_course_id , ASSIGMENT_ID , assignments.TITLE, COURSE_ID , COURSES.TITLE AS COURSE_TITLE, student_assignment.oral_mark , student_assignment.MARK, SUBMISION_DATE\n"
                    + " from students\n"
                    + " JOIN student_assignment ON student_assignment.student_id = students.STUDENT_ID\n"
                    + " join assigments_courses on assigments_courses.assi_course_id = student_assignment.assi_course_id\n"
                    + " join courses on courses.COURSE_ID = assigments_courses.COUR_ID\n"
                    + " join assignments on assignments.ASSIGMENT_ID = assigments_courses.ASSIGNMENT_ID\n"
                    + " \n"
                    + " where students.student_id = ? ;";

            statement = connection.prepareStatement(query);

            statement.setInt(1, student_id);

            resultSet = statement.executeQuery();
            System.out.println("THE ASSIGNMENTS THAT THIS STUDENT HAS ARE : ");
            printLine();

            while (resultSet.next()) {

                int assiCourseId = resultSet.getInt("ASSI_COURSE_ID");

                int assigmentId = resultSet.getInt("ASSIGMENT_ID");

                String assignmentTitle = resultSet.getString("TITLE");

                int courseID = resultSet.getInt("COURSE_ID");

                String courseTitle = resultSet.getString("COURSE_TITLE");

                double oralMark = resultSet.getDouble("oral_mark");

                double mark = resultSet.getDouble("MARK");

                LocalDate submisionDate = resultSet.getDate("SUBMISION_DATE").toLocalDate();

                AssignmentStudent assistud = new AssignmentStudent(assiCourseId, assigmentId, assignmentTitle, courseID, courseTitle, oralMark, mark, submisionDate);

                assistudList.add(assistud);

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
        return assistudList;

    }
    //**************************ADD MARKS TO STUDENTS ASSIGNMETNT*******************************************

    public boolean insertMarkToStudentAssignment(int studentId, int assi_courseId, double mark, double oralmark) {

        boolean check = false;

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "UPDATE student_assignment\n"
                    + "SET Mark = ? , oral_mark = ? \n"
                    + "WHERE  student_id = ? \n"
                    + "and\n"
                    + "assi_course_id = ? ;";

            statement = connection.prepareStatement(query);

            statement.setDouble(1, mark);
            statement.setDouble(2, oralmark);
            statement.setInt(3, studentId);
            statement.setInt(4, assi_courseId);

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
 
}
