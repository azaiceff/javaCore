package lesson3.third_lesson3;

import lesson3.third_lesson3.fruit.Fruit;

import java.util.List;

//Класс Box, в который можно складывать фрукты. Коробки условно
// сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
public record Box<T extends Fruit>(List<T> boxFruit) {
    //Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного
// фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
    public float getWeight() {
        return this.boxFruit.size() * this.boxFruit.get(0).getWeightFruit();
    }

    //Внутри класса Box сделать метод compare(), который позволяет сравнить
// текущую коробку с той, которую подадут в compare() в качестве
// параметра. true – если их массы равны, false в противоположном случае.
// Можно сравнивать коробки с яблоками и апельсинами;
    public boolean compare(Box<? extends Fruit> box) {
        return this.getWeight() == box.getWeight();
    }

    //Написать метод, который позволяет пересыпать фрукты из текущей
// коробки в другую. Помним про сортировку фруктов: нельзя яблоки
// высыпать в коробку с апельсинами. Соответственно, в текущей
// коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
    public void pourOver(Box<T> box) {
        box.boxFruit.addAll(this.boxFruit);
        this.boxFruit.clear();
    }

    @Override
    public String toString() {
        return boxFruit.toString();
    }

    public List<T> boxFruit() {
        return boxFruit;
    }
}
