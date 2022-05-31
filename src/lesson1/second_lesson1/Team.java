package lesson1.second_lesson1;

public class Team {
    String name;
    Member[] team = new Member[4];

    Team(String name) {
        this.name = name;
        team = new Member[]{
                team[0] = new Member("Алексей", 200),
                team[1] = new Member("Иван", 100),
                team[2] = new Member("Петя", 500),
                team[3] = new Member("Дима", 300)
        };
    }

    void teamInfo(){
        for (Member member : team) {
            System.out.println(member.name + " Имя участника\n" +
                    member.run + " Максимальная дистанция бега\n");
        }
        System.out.println();
    }

    void showResults(){
        System.out.println("\nРезультаты:\nСписок преодолевших дистанцию");
        for (Member member : team) {
            if (member.result) System.out.println(member.name + " Преодолел дистанцию");
        }
    }
}
