package third_lesson1.actions;

import third_lesson1.course.Course;
import third_lesson1.team.Status;
import third_lesson1.team.Team;
//Класс Интендант следит за ходом соревнований и управляет ими
public final class Intendant {
    //метод принимает на вход команду и полосу препятствий, которую должна пройти команда
    public static void start(Team team, Course course){
        if(!team.teamInfo()) return;//спрашивает у команды готовность к испытаниям.
        // Если команда готова, допускает к соревнованиям.
        System.out.println("Команда допущена к соревнованиям!");
        //Первый участник команды проходит первое препятствие на полосе. Если успешно, передает эстафетную палочку
        // след участнику для прохождения второго препятствия. И так далее, пока не закончатся препятствия
        for (int i = 0; i < course.getObstacles().length; i++) {
            //проверяю прохождение участником указанного ему препятствия
            if(!team.getMembers()[i].isOvercoming(course.getObstacles()[i])){
                if(!team.getMembers()[i].isStatusAction()){
                    //Участник может отказаться от прохождения препятствия. Интенданту не важна причина, если это так,
                    // он дисквалифицирует всу команду(проверяю статус участника).
                    System.out.println("Команда дисквалифицирована!\n");
                    team.setStatus(Status.DISQUALIFIED);
                }else { //Иначе если участник стал проходить препятствие, но не преодолел его
                    System.out.println("Команда выбывает из соревнования\n");
                    team.setStatus(Status.LOST);
                }
                return;
            }
            if(i == course.getObstacles().length - 1){
                System.out.println("Это был последний участник команды успешно прошедший свое испытание!");
            }else {
                System.out.println(team.getMembers()[i].getName()
                        + " передал эстафетную палочку следующему участнику команды.");
            }
        }
        team.setStatus(Status.WIN);//Команде присваивается статус WIN если все участники прошли всю полосу препятствий
        System.out.println("Поздравляем!!!\nКоманда получает памятные подарки!\n");
    }
    // Интендант может дать общую инф. о статусах по всем командам
    public static void resultInfo(Team[] teams){
        int countWin = 0;
        int countNotP = 0;
        int countDis = 0;
        int countLost = 0;
        for (Team team: teams) {
            if(team.getStatus().equals(Status.WIN)) countWin++;
            if(team.getStatus().equals(Status.NOT_PARTICIPATE)) countNotP++;
            if(team.getStatus().equals(Status.DISQUALIFIED)) countDis++;
            if(team.getStatus().equals(Status.LOST)) countLost++;
        }
        System.out.println("\nИТОГО:");
        System.out.println("Всего " + teams.length + " команд." );
        System.out.println("Из них, не приняли участие: " + countNotP + "\nДисквалифицировано: " + countDis
        + "\nНе прошли испытания: " + countLost + "\nУспешно прошли испытания и получили призы: " + countWin);
    }
}
