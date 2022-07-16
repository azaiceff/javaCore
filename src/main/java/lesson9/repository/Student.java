package lesson9.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private final int id;
    private static int countId = 0;
    private final String name;
    private final String surname;
    private final List<Course> courses;

    public Student(String name, String surname, List<Course> courses) {
        this.id = ++countId;
        this.name = name;
        this.surname = surname;
        this.courses = courses;
    }
    public static List<Student> getNewStudentList() throws SQLException {
        List<Student> students = new ArrayList<>();
        DatabaseRepositorySQLite DB = new DatabaseRepositorySQLite();
        int count =  DB.getCountFromTableDB("students");
        for (int i = 0; i < count; i++) {
            String[] nameStudentFromDB = DB.getNameStudentFromDB(i + 1).split(" ");
            List<Course> courses = DB.getListCoursesFromDB(i + 1);
            students.add(new Student(nameStudentFromDB[1],nameStudentFromDB[0], courses));
        }
        return students;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return id + ". Студент: " + surname + " " + name + "\n" + courses.toString();
    }
}

