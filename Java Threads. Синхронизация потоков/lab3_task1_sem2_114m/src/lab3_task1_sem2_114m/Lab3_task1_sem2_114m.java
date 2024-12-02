package lab3_task1_sem2_114m;

public class Lab3_task1_sem2_114m {

    public static void main(String[] args) {
        Common     com    = new Common();
        //создание и запуск потоков А и В
        Thread_A  threadA = new Thread_A(com);
        Thread_B  threadB = new Thread_B(com);
        new Thread(threadA).start();
        new Thread(threadB).start();   
    } 
    //класс Common содержит два синхронизированных метода для увеличения счетчика add() и его уменьшения cut()
    public static class Common{
    private int count = 0; //глобальная переменная
    //Уменьшает значение глобальной переменной count, которую ожидает Thread_A.
    public synchronized void cut(){
        while (count < 1) {
            try {wait();
            } catch (InterruptedException e) {}
        }
        count--;//уменьшает глобальную переменную
        System.out.println("Thread_B: count -1");
        System.out.println("\tcount= " + count);
        notify();
    }
    //увеличивает значение счетчика count до заданного
    public synchronized void add() {
        while (count >= 2) {
            //wait()автоматически и атомарно разблокирует связанную переменную мьютекса, чтобы она могла использоваться Thread_B.
            try {wait(); 
            }catch (InterruptedException e) {} 
        }
        count++; //увеличивает глобальную переменную
        System.out.println("Thread_A: count +1");
        System.out.println("\tcount= " + count);
        //
        notify();
    }
    }
    //класс потока А реализуют интерфейс Runnable, методы run()переопределен
    public static class Thread_A implements Runnable{
    Common com;    
    Thread_A(Common com) {
        this.com=com;}
    @Override
    public void run() {
        //количество вызова метода увеличения ограничено
        for (int i = 1; i < 4; i++) 
        {com.add();
        }
    }}
//класс потока А реализуют интерфейс Runnable, метод run()переопределен
public static class Thread_B implements Runnable{
    Common com;
    Thread_B(Common com) {
        this.com=com; }
    @Override
    public void run(){
        //количество вызова метода уменьшения ограничено
        for (int i = 1; i < 4; i++) {
            com.cut();
        }
    }}
    
}
