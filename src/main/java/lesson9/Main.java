package lesson9;

import lesson9.repository.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static lesson9.repository.Student.*;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        /*DatabaseRepositorySQLite DB = new DatabaseRepositorySQLite();
        DB.fillingTablesDataBaseSQL();*/

        List<Student> studentList = getNewStudentList();
        printAllStudents(studentList);
        printUniqCourses(studentList);
        System.out.println();
        printCuriousStudents(studentList);
        System.out.println();
        searchPeopleByCourse(studentList, new Course("КасКадеР"));
        System.out.println();
        searchPeopleByName(studentList, "Ульяна");
    }
    public static void printAllStudents(List<Student> studentList) {
        System.out.println("******************");
        System.out.println(" Наши студенты и курсы которые они посещают");
        System.out.println("******************");
        studentList.forEach(System.out::println);
    }

    public static void printUniqCourses(List<Student> studentList) {
        System.out.println("******************");
        System.out.println(" Поиск профессий");
        System.out.println("******************");
        AtomicInteger count = new AtomicInteger();
        List<Course> courses = studentList.stream().flatMap(student -> student.getCourses().stream())
                .distinct()
                .sorted(Comparator.comparing(Course::toString))
                .toList();
        courses.forEach((course) -> System.out.println(count.incrementAndGet() + " " + course));
        System.out.println("Итого, у нас преподают по " + courses.size() + " профессиям.");
    }

    public static void printCuriousStudents(List<Student> studentList) {
        System.out.println("********************************");
        System.out.println(" Самые любознательные студенты");
        System.out.println("********************************");
        studentList.stream().sorted((o1, o2) -> o2.getCourses().size() - o1.getCourses().size())
                .limit(3).forEach(System.out::println);
    }

    public static void searchPeopleByCourse(List<Student> studentList, Course course) {
        System.out.println("****************");
        System.out.println(" Ищем " + course.getName());
        System.out.println("****************");
        studentList.stream()
                .filter((student) -> student.getCourses().stream().anyMatch(courseFromSteam -> courseFromSteam.equals(course)))
                .forEach(System.out::println);
    }
    public static void searchPeopleByName(List<Student> studentList, String name) {
        System.out.println("*************************");
        System.out.println(" Ищем по имени " + name);
        System.out.println("*************************");
        studentList.stream().filter(student -> student.getName().equals(name)).forEach(System.out::println);
    }
}
