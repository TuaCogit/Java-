package lab3_task4_sem2_114m;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lab3_task4_sem2_114m {

    public static void main(String[] args) {
        int size = 10; //размер буффера
        Random r = new Random();
        int rand =r.nextInt(1000);//случайная частота
        Buffer buff = new Buffer(size);//буффер
        //создание и запуск потоков производителя и покупателя
        Thread producer = new Thread(new Producer(buff, size, rand));
        Thread consumer = new Thread(new Consumer(buff, size, rand));
        producer.start();
        consumer.start();
    }
    //реализация кольцевого буффера
    public static class Buffer {
        String a[];
        int first, last;
        public Buffer(int size) {
            a = new String[size];
            first = last = -1;
        }
        //метод добавление в буффер
        public synchronized boolean add(String element) {
            int pop;
            pop = (last + 1) % a.length;
            //буффер заполнен
            if (pop == first) {
                return false;
            } else {
                last = pop;
                a[last] = element;
                if (first == -1) {
                    first = 0;}
                return true;
            }
        }
        //буффер пустой
        public boolean empty() {
            return first == -1;
        }
        //удаление элемента
        public synchronized String delete() {
            String result = a[first];
            if (first == last) {
                first = last = -1;
            } else {
                first = (first + 1) % a.length;}
            return result;
        }
    }
    //поток производителя
    static class Producer extends Thread {
        Buffer buff;
        int size;
        long slep;
        public Producer(Buffer buff, int size, long slep) {
            this.buff = buff;
            this.size = size;
            this.slep = slep;
        }
        //добавление элемента
        public void run() {
            int i = 0;
            while (true) {
                try {
                    String element = "элемента" + "_" + ++i;
                    boolean bool = buff.add(element);
                    if (bool) {
                        System.out.println("Производство " + element);
                        Thread.sleep(this.slep);
                    }
                    synchronized (buff) {
                        buff.notifyAll();}
                } catch (Exception e) { e.printStackTrace();}
            }
        }
    }
    //поток потребителя
    static class Consumer extends Thread {
        Buffer buff;
        int size;
        long slep;
        public Consumer(Buffer buff, int size, long slep) {
            this.buff = buff;
            this.size = size;
            this.slep = slep;
        }
        //потребление элемента
        public void run() {
            while (true) {
                while (buff.empty()) {
                    synchronized (buff) {
                        try {
                            buff.wait();
                        } catch (Exception e) { e.printStackTrace();}
                    }
                }
                String element = null;
                synchronized (buff) {
                    element = buff.delete();
                    try {
                        Thread.sleep(this.slep);
                    } catch (InterruptedException ex) {}}
                System.out.println("Потребление " + element);
            }
        }
    }
}
