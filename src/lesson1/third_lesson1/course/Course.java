package lesson1.third_lesson1.course;

import java.util.Arrays;
//Класс полоса препятствий. Полоса препятствий - неодушевленный объект и ему все равно кто по нему бегает.
// И кто что с ним делает. Поэтому не стал особо создавать здесь какие-либо методы
public final class Course {
    private final int id;
    private static int countId = 0;
    private final Obstacle[] obstacles;
    public Course(Obstacle[] obstacles) {
        this.id = ++countId;
        this.obstacles = obstacles;
    }
    //обычный геттер
    public Obstacle[] getObstacles() {
        return obstacles;
    }
    //переопределение метода toString
    @Override
    public String toString() {
        return "Полоса препятствий № " + id + " Всего препятствий " + obstacles.length
                + "\n" + Arrays.toString(obstacles);
    }
}
