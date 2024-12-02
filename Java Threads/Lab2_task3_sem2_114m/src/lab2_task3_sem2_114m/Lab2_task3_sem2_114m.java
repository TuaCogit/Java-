package lab2_task3_sem2_114m;

import java.util.Queue;
import java.util.Random;
import java.util.Vector;

public class Lab2_task3_sem2_114m {

    public static void main(String[] args) { 
        Supportive supr = new Supportive();//вспомогательный класс
        supr.setVectorZeros();//заполнение вектора нулями
        //создание экземпляров классов
        TheFirstStream firstStream = new TheFirstStream(supr);
        TheSecondStream secondStream = new TheSecondStream(supr);
        //запуск потоков
        new Thread(firstStream).start();
        new Thread(secondStream).start();
    }
//вспомогательный класс 
    static public class Supportive {
        int element = 0; //индекс текущий элемент вектора
        static int sizeVector; //размер вектора
        static Vector coolVector = new Vector(6); //вектор

        void setElem(int i) { //передача текущего индекса
            element = i;
        }
        //заполнение исходного вектора нулями
        public static void setVectorZeros() {
            for (int i = 0; i < coolVector.capacity(); i++) {
                coolVector.add(0);
            }
            sizeVector = coolVector.size();
        }
        //запись в вектор поэлементно случайных чисел
        public synchronized void setVectorInt() {
            if ((int) coolVector.get(element) != 0) {//текущая позиция вектора уже заполена
                try {//ждем чтения текущей позиции
                    wait();
                } catch (InterruptedException e) {}
            }
            //если текущая позиция содержит нуль, заполняем ее случ.значением
            final Random random = new Random();
            int rm = random.nextInt(99) + 1;
            coolVector.add(element, rm); //в позицию вектора element случ.число rm
            System.out.println("Write: " + rm + " to position " + element);
            notify(); //продолжает работу потока, у которого ранее был вызван метод wait()
        }
        //чтение вектора поэлементно
        public synchronized void getVectorInt() {
            if ((int) coolVector.get(element) == 0){//текущая позиция вектора еще не заполнена
                try {//ждем записи в текущаю позицию
                    wait();
                } catch (InterruptedException e) {}
            }
           //если текущая позиция сожержит ненулевое значение, считываем
            System.out.println("Read: " + coolVector.get(element) + " from position " + element);
            notify(); //продолжает работу потока
        }
    }
//класс первого потока реализует интерфейс Runnable
    static class TheFirstStream implements Runnable {
        Supportive supr;
        TheFirstStream(Supportive supr) {
            this.supr = supr;
        }
        @Override
        public void run() {
            //заполняет вектор случ.числами и выводит их
            for (int i = 1; i < supr.sizeVector; i++) {
                supr.setVectorInt();
            }
        }
    }
//класс второго потока реализует интерфейс Runnable
    static class TheSecondStream implements Runnable {
        Supportive supr;
        TheSecondStream(Supportive supr) {
            this.supr = supr;
        }
        @Override
        public void run() {
            for (int i = 1; i < supr.sizeVector; i++) {
                 //чтение вектора поэлементно и вывод
                supr.getVectorInt();
                supr.setElem(i);
            }
        }
    }
}
