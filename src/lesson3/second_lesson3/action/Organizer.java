package lesson3.second_lesson3.action;

import lesson3.second_lesson3.fruit.*;
import java.util.ArrayList;
import java.util.Random;

public class Organizer {
    private static final Random random = new Random();
    private static final ArrayList<Box> fruitBoxes = new ArrayList<>();
    private static String type;
    public static ArrayList<Box> getFruitBoxes(){
        return fruitBoxes;
    }
    public static void creatingListBox(){
        for (int i = 0; i < getRandomInt(15,5); i++) {
            fruitBoxes.add(getBox());
        }
    }
    private static Box<? extends Fruit> getBox(){
        return new Box<>(getListFruit(), type);
    }
    private static ArrayList<? extends Fruit> getListFruit(){
        if(getRandomBoolean()){
            type = "Яблочек";
            return getListFruit(new Apple());
        }else if(getRandomBoolean()){
            type = "Авокадо";
            return getListFruit(new Avocado());
        }else if (getRandomBoolean()){
            type = "Манго";
            return getListFruit(new Mango());
        }else {
            type = "Апельсинчиков";
            return getListFruit(new Orange());
        }
    }
    private static ArrayList<? extends Fruit> getListFruit(Fruit fruit){
        ArrayList<Fruit> list = new ArrayList<>();
        for (int i = 0; i < getRandomInt(5,5); i++) {
            list.add(fruit.getFruit());
        }return list;
    }
    //Здесь, для удаления пустых коробок из листа коробок, я не нашел какой-то стандартный способ.
    // Может по тому, что урока по листам у нас еще не было и сам пытался с этим разобраться.
    // У меня в листе коробок лежат лисы коробок разного типа, и плюс они имеют свой уникальный id.
    // В итоге решил сделать так. Чувствую, что здесь как-то по другому можно сделать и лучше, но пока оставил так.
    // Выглядит все равно вроде просто и понятно))
    public static void removeAllEmptyBox(){
        for (int i = 0; i < fruitBoxes.size(); i++) {
            if(fruitBoxes.get(i).getBoxFruit().size() == 0){
                fruitBoxes.remove(i--);
            }
        }
    }
    private static int getRandomInt(int a, int b){
        return random.nextInt(a) + b;
    }
    private static boolean getRandomBoolean(){
        return getRandomInt(4,0) == 0;
    }
    public static Float getRandomFloat(){
        return (float) getRandomInt(900,100) / 100;
    }
}
