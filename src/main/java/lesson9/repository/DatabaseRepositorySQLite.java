package lesson9.repository;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class DatabaseRepositorySQLite {
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final Random random = new Random();
    String filePathSQL = "src/main/java/lesson9/files/students.db";
    String filePathSurnameName = "src/main/java/lesson9/files/surname-name.txt";
    String filePathCourses = "src/main/java/lesson9/files/courses.txt";
    String insertStudents = "INSERT INTO Students (name) VALUES (?)";
    String insertCourses = "INSERT INTO Courses (name) VALUES (?)";
    String insertSummaryTable = "INSERT INTO summary_table (student_id, course_id) VALUES (?,?)";
    String getCountFromTableDB = "SELECT count(*) FROM ";
    String getListCoursesFromDB = "SELECT " +
            "courses.name " +
            "FROM courses " +
            "JOIN summary_table on courses.id = summary_table.course_id " +
            "WHERE summary_table.student_id =";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + filePathSQL);
    }

    private List<String> getListFromFile(String filePath) throws IOException {
        List<String> nameList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                nameList.add(line);
            }
        }
        return nameList;
    }

    public void fillingTablesDataBaseSQL() throws SQLException, IOException {
        newFileBD(filePathSQL);
        fillingTableDataBase(filePathSurnameName, insertStudents);
        fillingTableDataBase(filePathCourses, insertCourses);
        fillingTableSummarySQL();
    }

    private void fillingTableSummarySQL() throws SQLException {
        int countStudents = getCountFromTableDB("students");
        int countCourses = getCountFromTableDB("courses");
        for (int i = 0; i < countStudents; i++) {
            HashSet<Integer> intListCourses = new HashSet<>();
            for (int j = 0; j < random.nextInt(10) + 1; j++) {
                intListCourses.add(random.nextInt(countCourses) + 1);
            }
            for (int course_id : intListCourses) {
                addToTableSummary(i + 1, course_id);
            }
        }
    }

    private void newFileBD(String oldFilePath) throws IOException, SQLException {
        File oldFileDB = new File(oldFilePath);
        File newFileDB = new File("src/main/java/lesson9/files/!students.db");
        if (oldFileDB.delete()) {
            Files.copy(newFileDB.toPath(), oldFileDB.toPath());
        } else {
            throw new SQLException("failed to delete old DataBase");
        }
    }

    private void addToTableSummary(int student_id, int course) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement saveStudent = connection.prepareStatement(insertSummaryTable)) {
            saveStudent.setInt(1, student_id);
            saveStudent.setInt(2, course);
            saveStudent.execute();
            return;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new SQLException("Failure on saving file object");
    }

    int getCountFromTableDB(String tableName) throws SQLException {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getCountFromTableDB + tableName + ";");
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new SQLException("Failure on reading count from file object");
    }

    String getNameStudentFromDB(int id) throws SQLException {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM students WHERE id = " + id + ";");
            return resultSet.getString(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new SQLException("Failure on reading Name from file object");
    }

    List<Course> getListCoursesFromDB(int id) throws SQLException {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getListCoursesFromDB + id + ";");
            List<Course> listCourses = new ArrayList<>();
            while (resultSet.next()) {
                listCourses.add(new Course(resultSet.getString(1)));
            }
            return listCourses;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new SQLException("Failure on reading List from file object");
    }

    private void fillingTableDataBase(String filePath, String insert) throws SQLException, IOException {
        List<String> nameList = getListFromFile(filePath);
        for (String name : nameList) {
            setValueToTable(insert, name);
        }
    }

    private void setValueToTable(String insert, String value) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement saveStudent = connection.prepareStatement(insert)) {
            saveStudent.setString(1, value);
            saveStudent.execute();
            return;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new SQLException("Failure on saving file object");
    }
}
