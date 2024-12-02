package interfacevoice;
public class InterfaceVoice {

    public static void main(String[] args) {
        //Создание объектов классов и вызов переопределенных методов voice()
        Cat cat = new Cat();
        cat.voice();
        Dog dog = new Dog();
        dog.voice();
        Cow cow = new Cow();
        cow.voice();
    } 
}

//Создание интерфейса 
interface Voice  {
    public void voice();
}

class Cat implements Voice {
    @Override //Переопределение метода voice()
    public void voice() {
        System.out.println("Мяу");
    }
 }
class Dog implements Voice {
    @Override //Переопределение метода voice()
    public void voice() {
        System.out.println("Гав");
    }
 }
class Cow implements Voice {
    @Override //Переопределение метода voice()
    public void voice() {
        System.out.println("Муу");
    }
 }
