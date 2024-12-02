package lab3_task2_sem2_114m;

public class Lab3_task2_sem2_114m {
    public static void main(String[] args) {
        //создание запуск и ожидание завершения потоков
       Thread first = new Thread(new TheFirstStream());
        Thread second = new Thread(new TheSecondStream());
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {}
    }
}
//общий класс с общей переменной
class Supportive {
    private static Supportive sup = new Supportive();//экземпляр класса
    private int count = 0; //общая переменная
    //получение экзепляра класса
    public static Supportive getInstance() {
        return sup;
    }
    //получение значения общей переменной
    public int getCount() {
        return count;
    }
    //увеличение переменной и вывод
    public void increase(String nameThread) {
        synchronized (Supportive.class) {
            System.out.println(nameThread + " count = " + count++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//первый поток
class TheFirstStream implements Runnable {
    @Override
    public void run() {
       Supportive sup = Supportive.getInstance();
       //пока значение общей переменной не достигнет 100
        while (sup.getCount()<100) {
            sup.increase("Первый поток: ");
        }
    }
}
//второй поток
class TheSecondStream implements Runnable {
    @Override
    public void run() {
       Supportive sup = Supportive.getInstance();
        //пока значение общей переменной не достигнет 100
        while (sup.getCount()<100) {
            sup.increase("Второй поток: ");
        }
    }
}