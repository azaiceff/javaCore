package lesson3.first_lesson3;

import lesson3.first_lesson3.fruit.Fruit;
import java.util.ArrayList;

public class Box<T extends Fruit> {
    private final int id;
    private static int countId = 0;
    private final ArrayList<T> fruitBox;

    Box(ArrayList<T> fruitBox) {
        this.id = ++countId;
        this.fruitBox = fruitBox;
    }
    ArrayList<T> getFruitBox() {
        return fruitBox;
    }
    void addFruit(T fruit){
        this.fruitBox.add(fruit);
    }
    void pourOverFruit(Box another){
        if(this.equals(another)) {
            System.out.println("Невозможно пересыпать коробку саму в себя)");
            return;
        }
        if(this.getType().equals(another.getType())) {
            for (int i = 0; i < another.fruitBox.size(); i++) {
                this.addFruit((T) another.fruitBox.get(i));
            }
            another.fruitBox.clear();
        }else System.out.println("В коробках разные продукты - смешивать нельзя!");
    }
    boolean compare(Box another) {
        int minDifference = 1;
        return Math.abs(this.getWeight() - another.getWeight()) < minDifference;
    }
    Double getWeight(){
        Double weight = 0.0;
        for (T box : fruitBox) {
            weight += box.getWeight();
        }
        return Math.floor(weight * 100)/100;
    }
    int getId() {
        return id;
    }
    String getType(){
        if(fruitBox.size() == 0){
            return "Коробка пуста!";
        }
        return fruitBox.get(0).getType().toString();
    }
    @Override
    public String toString() {
        return fruitBox.toString();
    }
}
