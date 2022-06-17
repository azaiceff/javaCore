package lesson3.second_lesson3.fruit;

import static lesson3.second_lesson3.action.Organizer.getRandomFloat;

public class Mango extends Fruit{
    public Mango(Float weight) {
        super("Манго", weight);
    }
    public Mango() {
    }
    @Override
    public Fruit getFruit() {
        return new Mango(getRandomFloat());
    }
}
