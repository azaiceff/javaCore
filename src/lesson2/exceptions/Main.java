package lesson2.exceptions;

import java.util.Random;

public class Main {
    private final static Random random = new Random();
    private static final int SIZE_ARRAY = 4;
    public static void main(String[] args) {

        String[][][][] stringArray = getStrArr();
        //Вывожу в консоль общую инф. о кол-ве сгенерированных двухмерных массивов
        System.out.println(("Всего двумерных массивов: "
                + stringArray.length * stringArray[stringArray.length - 1].length + " шт.\n").toUpperCase());
        //Начинаю перебирать массивы)
        int count = 0; // посчитаю в ручную, сколько прошло массивов через наш метод.
        for (String[][][] arr: stringArray) {
            for (String[][] array: arr) {// Здесь пошли двумерные массивы, их сумму и будем считать
                System.out.println("Массив № " + ++count);
                try {// обрабатываю проброшенное исключение, если размер массива не соответствует заданному в ТЗ,
                    //сумму его уже не считаю, но двигаюсь дальше, что бы проверить остальные массивы
                    System.out.println("Сумма всех чисел массива: " + sumArray(array) + "\nПоехали дальше!");
                } catch (MyArraySizeException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Обработали - поехали дальше)");
                }
                System.out.println();
            }
        }
        System.out.println("А не, все - стоп! Массивы закончились))\nВсего проверили массивов: " + count + "шт. " +
                " (это был ручной подсчет) :)");
    }
    //Логика подсчета суммы будет такой: если размер массива не соот. ТЗ, то и рассчитывать его сумму не будем.
    // Если все хорошо, то рассчитаем его сумму. Но в массиве у меня могут появиться как пустые элементы,
    // так и элементы которые нельзя привести к числу. Здесь мне не хотелось бы прерывать расчет так как с
    // пробросом исключения, я уже потренировался в предыдущем пункте ДЗ. Если будут попадаться такие элементы,
    // буду перехватывать их прямо на месте и тут же обрабатывать (не буду включать их в расчет и пропускать),
    // выводя инф. об элементе который перехватил
    private static int sumArray(String[][] strArr) throws MyArraySizeException{
        checkSizeArray(strArr);// можно было прямо здесь сделать проверку, но все же вынес ее в отдельный метод
        // для соблюдения принципов функционального программирования).
        int sum = 0;
        for (int i = 0; i < strArr.length ; i++) {
            for (int j = 0; j < strArr[i].length; j++) {
                try {
                    // в массиве у меня может оказаться пустой элемент, но при попытке приведения его к числу
                    // вылетает исключение NumberFormatException, что-бы была возможность отследить, что здесь
                    // именно null, кидаю исключение NullPointerException и потом его же и обработаю. :)
                    checkNull(strArr[i][j]);
                    sum += Integer.parseInt(strArr[i][j]);
                } catch (NumberFormatException e) {
                    try {
                        throw new MyArrayDataException("Элемент массива [" + i + "][" + j + "] = "  + strArr[i][j] +
                                " невозможно привести к числу");
                    } catch (MyArrayDataException ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (NullPointerException e) {
                    System.out.println("NullPointerException. Элемент массива [" + i + "][" + j
                            + "] = " + strArr[i][j]);
                }
            }
        }
        return sum;
    }
    //Метод проверки соот. массива заданным параметрам.
    private static void checkSizeArray(String[][] array) throws MyArraySizeException{
        System.out.println("Размер: " + array.length + " х " + array[0].length);
        if (array.length != SIZE_ARRAY || array[0].length != SIZE_ARRAY){
            throw new MyArraySizeException("Массив не того размера [" + array.length + "][" + array[0].length + "]");
        }
    }
    private static void checkNull(String str){
        if(str == null){
            throw new NullPointerException();// Здесь можно не пробрасывать, т.к. это стандартный тип исключения
        }
    }
    //Здесь решил немного поиграться для тренировки. Хочу сгенерить несколько двумерных массивов для проверки.
    // Можно конечно для этой цели обойтись и трехмерным массивом, но тоже оч. уж просто. Решил сделать
    // двумерный массив двумерных массивов. Тоже не сложно, но хоть что-то))
    private static String[][][][] getStrArr(){
        String[][][][] arr = new String[getRandomInt()][getRandomInt()][][];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                //Здесь начинаю генерить двумерные массивы
                String [][] array = new String[getLen()][getLen()];
                arr[i][j] = array;
                for (int k = 0; k < array.length; k++) {
                    for (int l = 0; l < array[k].length; l++) {
                        if(random.nextInt(10) == 0){
                            arr[i][j][k][l] = String.valueOf(getRandomInt()) + getRandomChar() + getRandomInt();
                        }else if (random.nextInt(10) != 0) {
                            arr[i][j][k][l] = String.valueOf(random.nextInt(40));
                        }
                        //Для наглядности сначала сделал так:
                        //else if(random.nextInt(10) == 0){
                        // arr[i][j][k][l] = null;}
                        // Но мне, что-то такой вид не понравился, решил просто сделать комментарий.
                        // Этот элемент изначально уже имеет значение null.

                    }
                }
            }
        }
        return arr;
    }
    //Методы для генерации чисел, символов и длинны двумерного массива.
    private static char getRandomChar(){
        return  (char) Character.toUpperCase(random.nextInt(26) + 'a');
    }
    private static int getRandomInt(){
        return random.nextInt(10) + 2;
    }
    private static int getLen(){
        int len = 4;
        if(random.nextInt(6) == 0){
            len = 5;
        }
        return len;
    }
}
