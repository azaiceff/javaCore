package lesson3.first_lesson3;
import java.util.Arrays;

import static lesson3.first_lesson3.Organizer.getBoxes;

public class Main {

    public static void main(String[] args) {
        Organizer.swappingElementsArray();
        Organizer.creatingBoxes();
        for (Box box: getBoxes()) {
            System.out.println("Коробка №-" + box.getId() + " " + box.getType());
            System.out.println("Вес коробки: " + box.getWeight());
            for (Box boxSecond: getBoxes()) {
                if (box.equals(boxSecond)) continue;
                if(box.compare(boxSecond)){
                    System.out.println("Коробка c " + box.getType() + " №-" + box.getId()
                            + " равна по весу коробке " + boxSecond.getType() + " №-" + boxSecond.getId());
                    System.out.println("Ее вес: " + boxSecond.getWeight());
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        for(Box box: getBoxes()) {
            for (Box second: getBoxes()) {
                if (box.equals(second)) continue;
                if(box.getFruitBox().size() == 0 || second.getFruitBox().size() == 0) continue;
                if(!box.getType().equals(second.getType())) continue;
                box.pourOverFruit(second);
            }
        }
        System.out.println(Arrays.toString(getBoxes()));
        changeArray(getBoxes());
        for (Box box: getBoxes()) {
            System.out.println(box);
        }
    }
    private static void changeArray(Box[] obj) {
        int count = 0;
        for (Box box : obj) {
            if (box.getFruitBox().size() != 0) {
                count++;
            }
        }
        Box[] array = new Box[count];
        for (Box box : obj) {
            if (box.getFruitBox().size() != 0) {
                array[--count] = box;
            }
        }
        Organizer.setBOXES(array);
    }
}
