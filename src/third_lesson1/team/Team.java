package third_lesson1.team;

import third_lesson1.actions.Organizer;

//import static classObject.Organizer.nameTeam;

//Класс команды
public final class Team {
    private final int id;
    private static int countId = 0;
    private final String name;
    private final Member[] members;
    //Немного ознакомился с принципами динамического программирования. Ввод переменной status, позволяет сохранить
    // промежуточное состояние команды. В случае необходимости, уже не нужно повторное вычисление состояния
    // команды, а можно просто обратиться к данной переменной. Это экономит ресурсы вычислительной техники. Также
    // идею инициализации id(команды, участника и тд) в конструкторе, мне подкинуло понимание этих принципов.
    // Добавление статической переменной, которая уже содержит в себе последний номер id позволяет мне не выполнять
    // какие-то дополнительные вычисления.
    private Status status;
    public Team(String name, Member[] members) {
        this.id = ++countId;
        this.name = name;
        this.members = members;
        this.status = Status.NOT_PARTICIPATE;
    }
    //сеттер, через него интендант меняет статус команды
    public void setStatus(Status status) {
        this.status = status;
    }
    //обычные геттеры
    public String getName() {
        return name;
    }
    public Member[] getMembers() {
        return members;
    }
    public Status getStatus() {
        return status;
    }
    // переопределение метода toString
    @Override
    public String toString() {
        return "Команда № " + id + " " + name + "\nСтатус: " + status;
    }
    //boolean метод, дает возможность команде по тем или иным причинам отказаться или принять участие в
    // прохождении полосы препятствий
    public boolean teamInfo(){
        System.out.println("Команда: " + getName());
        for (Member member: members) {
            if(member == null){
                System.out.println("В команде мало человек и мы не можем принять участие в испытаниях\n");
                return false;
            }else System.out.println(member);
        }
        if (Organizer.getRandomBoolean()){
            System.out.println("Команда № " + id + " " + name
                    + " решили отметить знакомство и не участвовать в испытаниях\n");
            return false;
        }else {
            System.out.print("""
                    Мы — спортивная братва!
                    За нас болейте, детвора!
                    """);
            return true;
        }
    }
    //Метод выводящий инф. о команде. Можно было и не делать, так как использовал переопределение метода toString
    public void resultStatusInfo(){
        System.out.println("Команда № " + id + " " + name + " Статус: " + status);
        if(getStatus().equals(Status.WIN)){
            System.out.println("Поздравляем победителей!");
        }
    }
}
