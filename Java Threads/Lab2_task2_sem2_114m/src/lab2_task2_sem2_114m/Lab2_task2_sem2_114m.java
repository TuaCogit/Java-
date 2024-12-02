package lab2_task2_sem2_114m;

import java.util.Random;
import java.util.Vector;

public class Lab2_task2_sem2_114m {
    static int sizeVector; //размер вектора
    static Vector coolVector = new Vector(5); //вектор

    //запись в вектор поэлементно случайных чисел
    public static void setVectorInt(int i) {
        final Random random = new Random();
        int rm = random.nextInt(99) + 1;
        coolVector.add(i, rm); //в позицию вектора i случ.число rm
        System.out.println("Write: " + rm + " to position " + i);
    }

    //чтение вектора поэлементно
    public static void getVectorInt(int i) {
        System.out.println(" Read: " + coolVector.get(i) + " from position " + i);
    }

    //заполнение исходного вектора нулями
    public static void setVectorZeros() {
        for (int i = 0; i < coolVector.capacity(); i++)coolVector.add(0);
        sizeVector = coolVector.size();
    }

    public static void main(String[] args) {
        setVectorZeros();//заполнение вектора нулями
        //создание экземпляров классов
        TheFirstStream first = new TheFirstStream();
        TheSecondStream second = new TheSecondStream();
        //расставление приоритетов
        first.setPriority(Thread.MAX_PRIORITY);
        second.setPriority(Thread.MIN_PRIORITY);
        first.start(); // Запуск первого потока
        second.start(); //запуск второго потока
        try {
            first.join(); //ждем завершения 1го потока
            System.out.println("Первый поток завершился");
            second.join();//ждем завершения 2го потока
            System.out.println("Второй поток завершился");
        } catch (InterruptedException e) {
        }
        
    }
}
//класс первого потока наследует от класса Thread
class TheFirstStream extends Thread {
    @Override
    public void run() {
        //заполняет вектор случ.числами и выводит их
        for (int i = 0; i < Lab2_task2_sem2_114m.sizeVector; i++) {
            Lab2_task2_sem2_114m.setVectorInt(i);
            //приостановка потока
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
//класс второго потока наследует от класса Thread
class TheSecondStream extends Thread {
    @Override
    public void run() {
        //чтение вектора поэлементно и вывод
        for (int i = 0; i < Lab2_task2_sem2_114m.sizeVector; i++) {
             Lab2_task2_sem2_114m.getVectorInt(i);
             //приостановка потока
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
