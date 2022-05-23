package second_lesson1;

class Course {

    int[] obstacles;

    Course(int[] obstacles) {
        this.obstacles = obstacles;
    }
    void doIt(Team team){
        for (int i = 0; i < team.team.length ; i++) {
            for (int obstacle : obstacles) {
                if (team.team[i].run < obstacle) {
                    System.out.println(team.team[i].name + " не преодолел препятствие " + obstacle);
                    team.team[i].result = false;
                    break;
                } else {
                    System.out.println(team.team[i].name + " преодолел препятствие " + obstacle);
                    team.team[i].result = true;
                }
            }
        }
    }

}
