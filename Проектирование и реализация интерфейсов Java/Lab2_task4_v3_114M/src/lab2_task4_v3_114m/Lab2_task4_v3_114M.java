
package lab2_task4_v3_114m;

public class Lab2_task4_v3_114M {

    public static void main(String[] args) {
       //создание экземпляра класса SleepyImpl,
       //который реализует методы интерфейса Sleepyс
       //по умолчанию флаг awake=true
       SleepyImpl sleep = new SleepyImpl(true); 
       //вызов метода вывода информации
       sleep.ask(); //вывод: "BOO!"
       sleep.sleep(); //вызов метода спать
       sleep.ask(); //вывод: "zzz..."
       sleep.wakeUp();//вызов метода проснуться
       sleep.ask(); //вывод: "BOO!"
    }
     //создан интерфес и методы в нем
    public interface Sleepyс{
    void sleep(); //метод спать
    void wakeUp(); //метод проснуться
    void ask(); //метод вывода информации
}
    
    //класс, реализующий методы интерфейса Sleepyс
    public static class SleepyImpl implements Sleepyс{
        boolean awake; //флаг: спит или нет
        
        SleepyImpl(boolean awake){ //конструктор класса
            this.awake=awake;
        }
        //реализация методов интерфейса Sleepyс
        //метод спать
        public void sleep() {
            this.awake=false;
        }
        //метод проснуться
        @Override
        public void wakeUp() {
            this.awake=true;
        }
        //метод вывода информации 
        @Override
        public void ask() {
           if (this.awake==true){ 
               System.out.println("BOO!");
           }else{
               System.out.println("zzz...");
           }
        } 
}
}
