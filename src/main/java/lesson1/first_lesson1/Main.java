package lesson1.first_lesson1;

public class Main {
    public static void main(String[] args){

        Organizer.newCourses();
        Organizer.searchPeople();
        Organizer.creatingTeams();
        for (Team team: Organizer.getArrTeam()) {
            Intendant.start(team,Organizer.getCourse());
        }
        Intendant.showResults();
    }
}
