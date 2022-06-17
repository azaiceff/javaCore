package lesson1.third_lesson1.actions;
import lesson1.third_lesson1.course.Course;
import lesson1.third_lesson1.course.Obstacle;
import lesson1.third_lesson1.course.Obstacle_Enum;
import lesson1.third_lesson1.team.Member;
import lesson1.third_lesson1.team.Team;

import java.util.Random;
//Класс Органайзер создает препятствия и формирует команды. Идея о создании классов Органайзер и Интендант не моя.
// Немного успел ознакомиться с паттернами программирования. Мне реально понравилось, как подобные вещи облегчают
// программирование))
public final class Organizer {
    private static final Random random = new Random();
    //кол-во препятствий на полосе - выбираю случайным образом
    private static final int MiN_OBSTACLE = getRandomInt(3,4);
    //кол-во полос препятствий (длинна массива) - выбираю случайным образом
    private static final Course[] courses = new Course[getRandomInt(4,2)];
    //общее кол-во участников - выбираю случайным образом
    private static final Member[] members = new Member[getRandomInt(100,50)];
    // кол-во участников в команде напрямую зависит от кол-ва препятствий на полосе препятствий
    private static final int MIN_TEAM = MiN_OBSTACLE;
    //кол-во команд вычисляю с помощью метода getLengthTeam()
    private static final Team[] teams = new Team[getLengthTeam()];
    // Создаю массив полос препятствий. Здесь и далее пытаюсь применить принципы функционального программирования.
    // Конечно с этими принципами я ознакомился довольно поверхностно, но даже этого ознакомления мне достаточно,
    // что бы почувствовать как эти принципы облегчают процесс программирования. Вместо написания одного сложного метода,
    // разбиваю его не несколько максимально простых. К вспомогательным методам, благодаря инкапсуляции,
    // ограничиваю доступ. В случае необходимости к ним можно открыть доступ, что может быть полезным для
    // расширения функционала программы.
    public static void creatingCourses(){
        for (int i = 0; i < courses.length; i++) {
            courses[i] = new Course(creatingObstacles());
        }
    }
    //Для создания массива полос, создаю массив препятствий.
    //Количество препятствий в массиве генерируется случайным образом
    //(переменная MiN_OBSTACLE генерируется один раз и будет одинакова во всех массивах).
    private static Obstacle[] creatingObstacles(){
        Obstacle[] obstacles = new Obstacle[MiN_OBSTACLE];
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = creatingObstacle();
        }
        return obstacles;
    }
    //создаю и возвращаю препятствие для создания массива препятствий
    private static Obstacle creatingObstacle(){
        Obstacle_Enum type = getTypeObstacle();
        return new Obstacle(type, getIntObstacle(type));
    }
    //Случайным образом выбираю тип препятствия из Obstacle_Enum
    private static Obstacle_Enum getTypeObstacle(){
        return Obstacle_Enum.values()[getRandomInt(Obstacle_Enum.values().length)];
    }
    //Генерирую и возвращаю размер препятствия в зависимости от его типа
    private static int getIntObstacle(Obstacle_Enum type){
        int obstacle;
        if(type.equals(Obstacle_Enum.TREADMILL)){
            obstacle = Organizer.getRandomInt(100,50);
        } else if (type.equals(Obstacle_Enum.WALL)) {
            obstacle = Organizer.getRandomInt(2,1);
        } else if (type.equals(Obstacle_Enum.BIKE_PATH)) {
            obstacle = Organizer.getRandomInt(1000,500);
        }else if (type.equals(Obstacle_Enum.PIT)){
            obstacle = Organizer.getRandomInt(2,1);
        }else obstacle = Organizer.getRandomInt(10,10);
        return obstacle;
    }
    // Заполняем массив участников. Представил, что мы ходим по улице и ищем людей кто согласиться участвовать в
    //соревнованиях. Поэтому назвал метод searchMembers()
    public static void searchMembers(){
        for (int i = 0; i < members.length ; i++) {
            members[i] = new Member(getName());
        }
    }
    // Для метода searchMembers() генерирую имя участника с помощью класса Enum Name
    private static String getName() {
        return Name.values()[getRandomInt(Name.values().length)].toString() + "_"
                + getRandomChar();
    }
    //метод вычисляет длину массива команд, зависящий от общего кол-ва участников и от кол-ва участников в команде
    private static int getLengthTeam(){
        if (members.length % MIN_TEAM == 0){
            return members.length / MIN_TEAM;
        }else return members.length / MIN_TEAM + 1;// если общее количество участников не кратно кол-ву участников
        // в команде, добавляю еще одну команду и в итоге у меня получится одна не полностью укомплектованная команда))
        // не полностью укомплектованная команда у меня будет отсеиваться (тут у меня вариантов, как ее отсеивать
        // просто "миллион", есть идея как и не отсеивать ее, но при этом сделать так, что-бы она так же могла
        // пройти все испытания))
    }
    //Создаю массив команд из массива участников.
    //Количество участников в команде напрямую зависит от количества препятствий на полосах препятствий.
    public static void creatingTeams(){
        int count = 0;
        int indexTeamArr = 0;
        Member[] forTeam = new Member[MIN_TEAM];
        for (int i = 0; i < members.length; i++) {
            if(count < MIN_TEAM){
                forTeam[count++] = members[i];
            }
            if(count == MIN_TEAM || i == members.length - 1){
                teams[indexTeamArr++] = getCreatingTeam(forTeam);
                forTeam = new Member[MIN_TEAM];
                count = 0;
            }
        }
    }
    //Метод создает и возвращает команду. Принимает на вход массив участников,
    // а имя команды генерируется методом getNameTeam()
    private static Team getCreatingTeam(Member[] members){
        return new Team(getNameTeam(), members);
    }
    // метод генерирует и возвращает имя команды с помощью класса Enum Name_Team
    private static String getNameTeam(){
        return Name_Team.values()[getRandomInt(Name_Team.values().length)].toString() + "_"
                + getRandomChar() + getRandomChar();
    }
    // обычный геттер, возвращает массив команд
    public static Team[] getTeams(){
        return teams;
    }
    //Геттер возвращающий случайную полосу препятствий из массива полос препятствий. С его помощью, случайным
    //образом, подкидываю полосу препятствий команде
    public static Course getCourse(){
        return courses[getRandomInt(courses.length)];
    }
    //Для удобства создал отдельно геттеры возвращающие случайные инты, символы и boolean. Использую их на
    // протяжении всего кода, где требуются случайные значения
    public static int getRandomInt(int a, int b) {
        return random.nextInt(a) + b;
    }
    public static int getRandomInt(int a) {
        return getRandomInt(a, 0);
    }
    public static char getRandomChar(){
        return (char) Character.toUpperCase(getRandomInt(26) + 'a');
    }
    // сделал так, что бы уменьшить вероятность выпадения значения true
    public static boolean getRandomBoolean(){
        return getRandomInt (10) == 0;
    }
}
