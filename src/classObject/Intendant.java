package classObject;

class Intendant {
    private static int teamCount = 0;
    private static int winCount = 0;
    private static int disqualificationCount = 0;
    private static int dropoutCount = 0;

    static void start(Team team, Course course){
        System.out.println();
        int teamMember = 0;
        if(!team.teamInfo()) return;
        System.out.println("Команда " +team.getName() + " допущена к соревнованиям!");
        teamCount++;
        if(!run(team.getEntrants()[teamMember++], course)) return;
        if(!swim(team.getEntrants()[teamMember++], course)) return;
        if(!jump(team.getEntrants()[teamMember++], course)) return;
        if(!bicycleCross(team.getEntrants()[teamMember], course)) return;
        winCount++;
        luck(team);
    }

    static void showResults(){
        System.out.println("\n\nИТОГО!\nВсего приняли участие: " + teamCount + " команд"
                + "\nИз них успешно прошли испытания " + winCount
                + "\nНе прошли испытания " + dropoutCount + " команд"
                + "\nДисквалифицировано " + disqualificationCount + " команд"

        );
    }
    private static boolean run(Entrant entrant, Course course){
        int maxRun = entrant.getMaxRun();
        int Treadmill = course.getTreadmill();
        if(maxRun == 0){
            disqualification();
            disqualificationCount++;
            return false;
        }
        if(maxRun < Treadmill){
            System.out.println(entrant.getName() + " смог пробежать только " + maxRun
            + "м. из " + Treadmill);
            outOf();
            dropoutCount++;
            return false;
        }else {
            System.out.println(entrant.getName() + " успешно пробежал дистанцию и передал эстафету другому");
            return true;
        }
    }
    private static boolean swim(Entrant entrant, Course course){
        int maxSwim = entrant.getMaxSwim();
        int waterBarrier = course.getWaterBarrier();
        if(maxSwim == 0){
            disqualification();
            disqualificationCount++;
            return false;
        }
        if(maxSwim < waterBarrier){
            System.out.println(entrant.getName() + " смог проплыть только " + maxSwim
                    + "м. из " + waterBarrier);
            outOf();
            dropoutCount++;
            return false;
        }else {
            System.out.println(entrant.getName() + " успешно преодолел водную преграду и передал эстафету другому");
            return true;
        }
    }
    private static boolean jump(Entrant entrant, Course course){
        int maxJump = entrant.getMaxJump();
        int wall = course.getWall();
        if(maxJump == 0){
            disqualification();
            disqualificationCount++;
            return false;
        }
        if(maxJump < wall){
            System.out.println(entrant.getName() + " смог прыгнуть только на " + maxJump
                    + "м. из " + wall);
            outOf();
            dropoutCount++;
            return false;
        }else {
            System.out.println(entrant.getName() + " успешно перепрыгнул и передал эстафету другому");
            return true;
        }
    }
    private static boolean bicycleCross(Entrant entrant, Course course){
        int maxBicycleCross = entrant.getMaxBicycleCross();
        int bikePath = course.getBikePath();
        if(maxBicycleCross == 0){
            disqualification();
            disqualificationCount++;
            return false;
        }
        if(maxBicycleCross < bikePath){
            System.out.println(entrant.getName() + " проехал только " + maxBicycleCross
                    + "м. из " + bikePath);
            outOf();
            dropoutCount++;
            return false;
        }else {
            System.out.println(entrant.getName() + " успешно доехал до финиша");
            return true;
        }
    }
    private static void disqualification(){
        System.out.println("Команда дисквалифицирована!");
    }
    private static void outOf(){
        System.out.println("Команда выбывает из соревнования!");
    }
    private static void luck(Team team){
        System.out.println("Все участники команды " + team + " получают памятные подарки");
    }
}
