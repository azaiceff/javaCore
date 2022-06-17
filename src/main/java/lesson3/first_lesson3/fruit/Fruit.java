package lesson3.first_lesson3.fruit;

public abstract class Fruit {
    private final Fruit_Enum type;
    private final Double weight;
    public Fruit(Fruit_Enum type, Double weight) {
        this.type = type;
        this.weight = weight;
    }
    public Fruit_Enum getType() {
        return type;
    }

    public Double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return type.toString() + " " + weight;
    }
}
