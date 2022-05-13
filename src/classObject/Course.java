package classObject;

class Course {
    private final int idCourse;
    private static int idCounter = 0;
    private final int treadmill;
    private final int waterBarrier;
    private final int wall;
    private final int bikePath;
    private final static int MIN_TEAM = 4;

    Course() {
        this.idCourse = ++idCounter;
        this.treadmill = Organizer.getRandomInt(80,50);
        this.waterBarrier = Organizer.getRandomInt(8,10);
        this.wall = Organizer.getRandomInt(2,1);
        this.bikePath = Organizer.getRandomInt(800,500);
    }
    int getTreadmill() {
        return treadmill;
    }
    int getWaterBarrier() {
        return waterBarrier;
    }
    int getWall() {
        return wall;
    }
    int getBikePath() {
        return bikePath;
    }
    static int getMinTeam(){
        return MIN_TEAM;
    }

    @Override
    public String toString() {
        return "\nПолоса препятствий №: " + idCourse;
    }
}
