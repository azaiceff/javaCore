package lesson1.first_lesson1;

class Team{
    private final int id;
    private static int idCounter = 0;
    private final String name;
    private final Entrant[] entrants;

    Team(String name, Entrant[] entrants) {
        this.id = ++idCounter;
        this.name = name + "_" + getChar() + getChar();
        this.entrants = entrants;
    }

    String getName(){
        return name;
    }

    Entrant[] getEntrants() {
        return entrants;
    }

    @Override
    public String toString() {
        return "№ " + id + ". Название команды: " + name;
    }

    boolean teamInfo(){
        System.out.println("№ " + id + ". Название команды: " + name);
        for (Entrant team : entrants) {
            if (team == null){
                System.out.println("В команде мало человек и она не может участвовать в соревнованиях");
                return false;
            }else System.out.println("Участник № " + team.getId() + " " + team.getName());
        }
        if (Organizer.getRandomBoolean()){
            System.out.println("Команда решила выпить за знакомство и не участвовать в соревнованиях))");
            return false;
        }else {
            /*System.out.println("""
                    Мы — спортивная братва!
                    За нас болейте, детвора!
                    """);*/
            return true;
        }
    }
    private char getChar(){
        return (char) Character.toUpperCase(Organizer.getRandomInt(26) + 'a');
    }
}
