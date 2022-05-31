package third_lesson1.team;

import third_lesson1.actions.Organizer;
import third_lesson1.course.Obstacle;
import third_lesson1.course.Obstacle_Enum;
//Класс участника команды
public final class Member {
    private final int id;
    private static int countId = 0;
    private final String name;
    private final int run;
    private final int swim;
    private final int jump;
    private final int bicycling;
    private boolean statusAction;
// в конструкторе, каждому участнику автоматом и случайным образом, присваиваются индивидуальные
// характеристики по бегу, прыжкам и тд
    public Member(String name) {
        this.id = ++countId;
        this.name = name;
        this.run = Organizer.getRandomInt(100, 70);
        this.swim = Organizer.getRandomInt(10, 15);
        this.jump = Organizer.getRandomInt(2,2);
        this.bicycling = Organizer.getRandomInt(1000,500);
        this.statusAction = false;
    }
    //обычный геттер
    public boolean isStatusAction() {
        return statusAction;
    }
    //метод проверяющий прохождение участником указанного ему препятствия.
    public boolean isOvercoming(Obstacle obstacle){
        System.out.println("Испытание: " + obstacle.getType());
        //если беговая дорожка, то бежим
        if(obstacle.getType().equals(Obstacle_Enum.TREADMILL)){
            return isActionOvercoming(getRun(), obstacle.getObstacle());
        }
        //По остальным препятствиям логика та же
        //если стена или яма, то прыгаем
        if (obstacle.getType().equals(Obstacle_Enum.WALL) || obstacle.getType().equals(Obstacle_Enum.PIT)){
            return isActionOvercoming(getJump(), obstacle.getObstacle());
        }
        //если водная преграда - плывем
        if (obstacle.getType().equals(Obstacle_Enum.WATER_BARRIER)) {
            return isActionOvercoming(getSwim(), obstacle.getObstacle());
        }
        //если ни одно из выше перечисленных, осталась только велосипедная дорожка - едем на велосипеде
        return isActionOvercoming(getBicycling(), obstacle.getObstacle());
    }
    //boolean метод, проверяющий возможность участника преодолеть препятствие
    private boolean isActionOvercoming(int action, int obstacle){
        if (action == 0) {
            return false;// если участник отказался преодолевать препятствие - статус не меням и возвращаем false
        }else {
            System.out.println("из " + obstacle);
            this.statusAction = true;//если предпринял попытку преодоления препятствия - меняем статус на true
            return action >= obstacle;// возвращаем true или false в зависимости от результата
            //После прохождения препятствия с результатом false, Интендант проверяет статус участника.
            // Если статус false - дисквалифицирует команду, иначе присваивает команде статус LOST
        }
    }
    @Override
    public String toString() {
        return "Участник № " + id + " " + name;
    }
    public String getName() {
        return name;
    }
    //Геттеры с возможностью для участника отказаться от прохождения препятствия с выводом доп. инф. в консоль
    public int getRun() {
        if(Organizer.getRandomBoolean()){
            System.out.println(name + " Я не хочу бежать!");
            return 0;
        }
        System.out.print(name + " пробежал " + run + "м. ");
        return run;
    }
    public int getSwim() {
        if(Organizer.getRandomBoolean()){
            System.out.println(name + " Я не умею плавать!");
            return 0;
        }
        System.out.print(name + " проплыл " + swim + "м. ");
        return swim;
    }
    public int getJump() {
        if(Organizer.getRandomBoolean()){
            System.out.println(name + " Я подвернул ногу и не могу прыгать!");
            return 0;
        }
        System.out.print(name + " прыгнул на " + jump + "м. ");
        return jump;
    }
    public int getBicycling() {
        if(Organizer.getRandomBoolean()){
            System.out.println(name + " У меня сломался велосипед!");
            return 0;
        }
        System.out.print(name + " проехал " + bicycling + "м. ");
        return bicycling;
    }
}
