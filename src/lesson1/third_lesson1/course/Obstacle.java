package lesson1.third_lesson1.course;

//Препятствия
public final class Obstacle {
    private final int id;
    private static int countId = 0;
    private final Obstacle_Enum type;
    private final int obstacle;
// обычный конструктор
    public Obstacle(Obstacle_Enum type, int obstacle) {
        this.id = ++countId;
        this.type = type;
        this.obstacle = obstacle;
    }
    //геттеры
    public Obstacle_Enum getType() {
        return type;
    }
    public int getObstacle() {
        return obstacle;
    }
    //Переопределение метода toString
    @Override
    public String toString() {
        return "Препятствие № " + id + " " + type + " - норматив " + obstacle + "м.";
    }
}
