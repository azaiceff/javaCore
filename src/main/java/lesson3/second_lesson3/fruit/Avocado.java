package lesson3.second_lesson3.fruit;

import static lesson3.second_lesson3.action.Organizer.getRandomFloat;

public class Avocado extends Fruit{
    public Avocado(Float weight) {
        super("Авокадо", weight);
    }
    public Avocado() {
    }
    @Override
    public Fruit getFruit() {
        return new Avocado(getRandomFloat());
    }
}
