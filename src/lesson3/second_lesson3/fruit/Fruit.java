package lesson3.second_lesson3.fruit;

public abstract class Fruit {
    private String type;
    private Float weight;

    public Fruit(String type, Float weight) {
        this.type = type;
        this.weight = weight;
    }
    Fruit() {
    }
    public Float getWeight() {
        return weight;
    }
    public abstract Fruit getFruit();
    @Override
    public String toString() {
        return type + " вес: " + weight;
    }
}
