package lesson3.second_lesson3.fruit;

import static lesson3.second_lesson3.action.Organizer.getRandomFloat;

public class Orange extends Fruit{
    public Orange(Float weight) {
        super("Апельсин", weight);
    }
    public Orange() {
    }
    @Override
    public Fruit getFruit() {
        return new Orange(getRandomFloat());
    }
}
