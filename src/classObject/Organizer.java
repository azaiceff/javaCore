package classObject;

import java.util.Random;
class Organizer {
    private static final Random random = new Random();
    private static final String[] name = {
            "Андрей", "Никита", "Лев", "Александра", "Юлия", "Мария",
            "Алина", "Юрий", "Агата", "Алла", "Владислав", "Полина", "Дмитрий", "Кирилл",
            "Артём", "Виктория", "Вероника", "Вера", "Денис", "Алиса", "Кира", "Камилла",
            "Николай", "Ника", "Екатерина", "Георгий", "Александр", "Максим", "Фатима",
            "Ярослав", "Арина", "Ярослава", "Данила", "Анастасия", "Василиса", "Степан",
            "София", "Всеволод", "Фёдор", "Алексей"};
    private static final String[] nameTeam = {
            "Библиографы", "Ботаники", "Медальеры", "Бульдозеристы", "Матросы",
            "Стилисты", "Цветоводы", "Официанты", "Сметчики", "Каменщики"
    };
    private static final Course[] course = new Course[getRandomInt(4,1)];
    private static final Entrant[] entrant = new Entrant[getRandomInt(50,40)];
    private final static int MIN_TEAM = Course.getMinTeam();
    private final static Team[] team = new Team[getLengthTeam()];

    static Team[] getArrTeam(){
        return team;
    }
    static void searchPeople(){
        for (int i = 0; i < entrant.length; i++) {
            entrant[i] = new Entrant(name[getRandomInt(name.length)]);
            /*System.out.println(entrant[i].getId() + " " + entrant[i].getName());*/
        }
    }
    static void creatingTeams() {
        int count = 0;
        int indexTeamArr = 0;
        Entrant[] forTeam = new Entrant[MIN_TEAM];
        for (int i = 0; i < entrant.length; i++) {
            if (count < MIN_TEAM) {
                forTeam[count++] = entrant[i];
            }
            if (count == MIN_TEAM || i == entrant.length -1) {
                team[indexTeamArr++] = creatingTeam(forTeam);
                forTeam = new Entrant[MIN_TEAM];
                count = 0;
            }
        }
    }
    private static Team creatingTeam(Entrant[] entrant){
        return new Team(getNameTeam(), entrant);
    }
    private static int getLengthTeam(){
        if (entrant.length % MIN_TEAM == 0){
            return entrant.length / MIN_TEAM;
        }else return entrant.length / MIN_TEAM + 1;
    }
    private static int countGetNameTeam = 0;
    private static String getNameTeam() {
        if(countGetNameTeam == nameTeam.length){
            countGetNameTeam = 0;
        }
        return nameTeam[countGetNameTeam++];
    }

    static void newCourses(){
        for (int i = 0; i < course.length; i++) {
            course[i] = new Course();
        }
    }
    static Course getCourse(){
        int numberCourse = Organizer.getRandomInt(course.length);
        System.out.print(course[numberCourse]);
        return course[numberCourse];
    }
    static int getRandomInt(int a, int b){
        return random.nextInt(a) + b;
    }
    static int getRandomInt(int a){
        return getRandomInt(a, 0);
    }
    static boolean getRandomBoolean(){
        return getRandomInt (8) == 0;
    }
}
