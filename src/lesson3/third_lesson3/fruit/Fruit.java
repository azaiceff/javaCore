package lesson3.third_lesson3.fruit;

public abstract class Fruit {
    private final String type;

    private final float weight;

    Fruit(String type, float weight) {
        this.type = type;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public float getWeightFruit() {
        return weight;
    }

    @Override
    public String toString() {
        return type;
    }
}
