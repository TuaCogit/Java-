package person;

public class Person {
    //Создание переменных класса
    private String name, surname, patronymic;
    //конструктор для фамилии, если имя и отчество отсутвуют
    public Person(String surname) {
        this.surname = surname;
    }
    //конструктор ФИО
    public Person(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }
    //Метод для учета возможности отсутствия значений в полях Имя и Отчество
    public String getFio(){
        //создаем объект класса StringBuilder для реализации изменяемых строк
        StringBuilder sb = new StringBuilder(surname);
        if(name != null && ! name.equals(""))
            sb.append(" ").append(name);
        if(patronymic != null && ! patronymic.equals(""))
            sb.append(" ").append(patronymic);
        return sb.toString();
    }

    public static void main(String[] args) {
        Person p1 = new Person("Петров");//Только фамилия
        Person p2 = new Person("", "Иванов", ""); //Отсутствует Имя и Отчество
        Person p3 = new Person("Иван", "Иванов", "Иванович");
        //Вывод данных на экран
        System.out.println(p1.getFio());
        System.out.println(p2.getFio());
        System.out.println(p3.getFio());
    }
}
