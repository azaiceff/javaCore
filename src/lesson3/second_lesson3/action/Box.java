package lesson3.second_lesson3.action;

import lesson3.second_lesson3.exception.MyException;
import lesson3.second_lesson3.fruit.Fruit;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private final int id;
    private static int idCounter = 0;
    private final String type;
    private final ArrayList<T> boxFruit;
    public Box(ArrayList<T> boxFruit, String type) {
        this.id = ++idCounter;
        this.type = type;
        this.boxFruit = boxFruit;
    }
    public String getType() {
        return type;
    }
    public int getId() {
        return id;
    }
    public ArrayList<T> getBoxFruit(){return boxFruit;}
    public float getWeightBox(){
        float weightBox = 0;
        for (T box: boxFruit) {
            weightBox += box.getWeight();
        }
        return weightBox;
    }
    //Метод, который позволяет пересыпать фрукты из текущей коробки в другую.
    //Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
    //Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются
    //объекты, которые были в первой
    // так как я использовал коробки типа Fruit, а в такие коробки я могу положить любой фрукт. Поэтому, для
    // соблюдения условия задачи, добавил защиту в метод, что бы коробки не смешивались.
    public void pourOver(Box box) throws MyException {
        if(this == box){
            throw new MyException("Нельзя пересыпать коробку саму в себя!");
        }else if(!this.type.equals(box.type)){
            throw new MyException("Коробки нельзя смешивать!");
        }else if(box.getWeightBox() == 0 || this.getWeightBox() == 0){
            throw new MyException("Пустые коробки нет смысла пересыпать!");
        }else {
            System.out.println("До");
            System.out.println(this);
            System.out.println(box);
            box.boxFruit.addAll(this.boxFruit);
            this.boxFruit.clear();
            System.out.println("После");
            System.out.println(this);
            System.out.println(box);
       }
    }
    @Override
    public boolean equals(Object obj) {
        float fallibility = 1f;
        if(this == obj)return true;
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return Math.abs (this.getWeightBox() - ((Box<?>) obj).getWeightBox()) < fallibility;
    }
    @Override
    public String toString() {
        return "Коробка № " + id + " для " + type + " " + boxFruit.toString();
    }
}
