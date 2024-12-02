package lab2_task1_sem2_114m;

public class Lab2_task1_sem2_114m {

    public static void main(String[] args) {
        //создание экземпляров классов
        TheFirstStream first = new TheFirstStream(); 
        TheSecondStream Second= new TheSecondStream();
        //передача экземпляра класса конструктору Thread-объекта 
        Thread secondTread = new Thread(Second);
        first.start(); // Запуск первого потока
        secondTread.start(); //запуск второго потока
            try {
                first.join(); //ждем завершения 1го потока
                secondTread.join();//ждем завершения 2го потока
            } catch (InterruptedException e){}
            System.out.println("Потоки завершились");
       
    }
}
//класс TheFirstStream расширяет класс Thread
class TheFirstStream extends Thread {
    @Override
    //в цикле выводится строка и кол-во итераций через промежутки 1с.
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Я первый поток " + i + ";");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
//класс TheSecondStream реализует интерфейс Runnable
class TheSecondStream implements Runnable {
    @Override
    public void run() {
        //в цикле выводится строка и кол-во итераций через промежутки 1с.
        for (int i = 0; i < 3; i++) {
            System.out.println("Я второй поток " + i + ";");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
