package lesson3.second_lesson3.fruit;

import lesson3.second_lesson3.action.Organizer;

public class Apple extends Fruit{
    public Apple(Float weight) {
        super("Яблоко", weight);
    }
    public Apple() {
    }
    @Override
    public Fruit getFruit(){
        return new Apple(Organizer.getRandomFloat());
    }
}
