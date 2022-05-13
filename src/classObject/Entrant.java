package classObject;

class Entrant{
    private final int id;
    private static int idCounter = 0;
    private final String name;
    private final int maxRun;
    private final int maxSwim;
    private final int maxJump;
    private final int maxBicycleCross;

    Entrant(String name) {
        this.id = ++idCounter;
        this.name = name;
        this.maxRun = Organizer.getRandomInt(100,50);
        this.maxSwim = Organizer.getRandomInt(10,10);
        this.maxJump = Organizer.getRandomInt(2,1);
        this.maxBicycleCross = Organizer.getRandomInt(1000,500);
    }
    int getId() {
        return id;
    }
    String getName() {
        return name;
    }
    int getMaxRun() {
        if(Organizer.getRandomBoolean()){
            System.out.println(name + " отказывается бежать");
            return 0;
        }else return maxRun;
    }
    int getMaxSwim() {
        if(Organizer.getRandomBoolean()){
            System.out.println(name + " отказывается плыть");
            return 0;
        }else return maxSwim;
    }
    int getMaxJump() {
        if(Organizer.getRandomBoolean()){
            System.out.println(name + " не хочет прыгать");
            return 0;
        }else return maxJump;
    }
    int getMaxBicycleCross() {
        if(Organizer.getRandomBoolean()){
            System.out.println("У " + name + " сломался велосипед");
            return 0;
        }else return maxBicycleCross;
    }
}

