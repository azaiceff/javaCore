package lesson1.third_lesson1;

import lesson1.third_lesson1.actions.Intendant;
import lesson1.third_lesson1.actions.Organizer;
import lesson1.third_lesson1.course.Course;
import lesson1.third_lesson1.team.Team;

public class Main {
    public static void main(String[] args) {
        // Создаю массив полос препятствий.
        Organizer.creatingCourses();
        //создаю один большой массив всех участников
        Organizer.searchMembers();
        // создаю массив команд
        Organizer.creatingTeams();
        // Все созданные команды отдаю на "съедение" Интенданту. В свою очередь Органайзер, случайным образом выбирает
        // полосу препятствий из массива препятствий, которую должна пройти команда. И также отдаем ее на "съедение"
        // Интенданту
        for (Team team: Organizer.getTeams()) {
            Course course = Organizer.getCourse();
            System.out.println(course);
            Intendant.start(team, course);
        }
        // Смотрим как изменился статус команд после прохождения испытаний
        for (Team team: Organizer.getTeams()) {
            team.resultStatusInfo();
           // System.out.println(team); так как я использовал переопределение метода toString, можно было сделать и так
        }
        // Интендант дает общую инф. о статусах по всем командам
        Intendant.resultInfo(Organizer.getTeams());
    }
}
