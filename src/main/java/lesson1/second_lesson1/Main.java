package lesson1.second_lesson1;


public class Main {
    public static void main(String[] args) {
        Course c = new Course(new int[]{50, 100, 200, 300});
        Team team = new Team("Команда");
        //team.teamInfo(); // В задании о вызове этого метода не сказано, поэтому закомментировал ))
        c.doIt(team);
        team.showResults();
    }
}
