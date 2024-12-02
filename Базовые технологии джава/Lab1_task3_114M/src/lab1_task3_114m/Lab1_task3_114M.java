package lab1_task3_114m;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Lab1_task3_114M {

    static SimpleDateFormat formater;
    static Scanner sc;

    public static void main(String[] args) {
        formater = new SimpleDateFormat("dd.MM.yyyy"); //представление даты в удобном формате
        sc = new Scanner(System.in, "windows-1251"); //сканер для консольного ввода

        ArrayList<Address> addressList = new ArrayList<Address>(3); //массив адрессов
        ArrayList<Humans> humansList = new ArrayList<Humans>(3); //массив жителей
        //заполеннение массива адресов
        addressList.add(new Address("Самара", "Полевая", 1));
        addressList.add(new Address("Самара", "Дачная", 2));
        addressList.add(new Address("Самара", "Гагарина", 3));
        addressList.add(new Address("Самара", "Полевая", 4));
        addressList.add(new Address("Самара", "Дачная", 5));
        //заполеннение массива людей
        humansList.add(new Humans("Николай", "Петров", 0, new GregorianCalendar(1983, 3, 23)));
        humansList.add(new Humans("Людмила", "Зайцева", 1, new GregorianCalendar(1986, 6, 26)));
        humansList.add(new Humans("Татьяна", "Николаева", 2, new GregorianCalendar(1989, 9, 29)));
        humansList.add(new Humans("Петр", "Крышев", 3, new GregorianCalendar(1997, 7, 27)));
        humansList.add(new Humans("Валентина", "Кошкина", 4, new GregorianCalendar(1987, 7, 7)));
        //вывод возможных действий
        System.out.println(" Выберите действие из списка: \n"
                + "1 - Вывод всех жителей;\n"
                + "2 - Поиск человека по фамилии;\n"
                + "3 - Поиск человека по адресу;\n"
                + "4 - Вывод людей, родившихся между определенными датами;\n"
                + "5 - Вывод самого молодого жителя;\n"
                + "6 - Вывод людей, проживающих на одной улице;\n"
                + "--введите любое целое число для завершения;");
        //выбор действия в консоли
        boolean goOn = true; //продолжать предлагать действия
        while (goOn) {
            System.out.print("\nВведите номер выбранного действия: ");
            try {
                int numAction = sc.nextInt(); // ввод с консоли номера действия 
                sc.nextLine();
                switch (numAction) {
                    case 1: //Вывод всех жителей
                        printHumansAll(humansList, addressList);
                        break;
                    case 2: //Поиск человека по фамилии
                        System.out.print("Введите фамилию: ");
                        String surname = sc.nextLine(); //ввод фамилии с консоли
                        searcByLastname(humansList, addressList, surname);
                        break;
                    case 3: //Поиск человека по адресу
                        System.out.print("Введите индекс адреса: ");
                        try { //перехват ошибки ввода не целого числа
                            int indxAddress = sc.nextInt(); //ввод с консоли
                            printHumanAtAddress(humansList, addressList, indxAddress);
                        } catch (InputMismatchException e) {
                            System.out.println("Вы ввели не число");
                        }
                        sc.nextLine();
                        break;
                    case 4: //Вывод людей, родившихся между определенными датами
                        GregorianCalendar startDate, stopDate; //начальная и конечная даты
                        try { //проверка на ввод целых чисел
                            System.out.println("Введите начальную дату в формате 01.01.1900: ");
                            startDate = inputDate();
                            System.out.println("Введите конечную дату в формате 01.01.1900: ");
                            stopDate = inputDate();
                            printBetweenDates(humansList, addressList, startDate, stopDate);
                        } catch (InputMismatchException e) {
                            System.out.println("Вы ввели не целое число");
                            sc.nextLine();
                            break;
                        }
                        break;
                    case 5: //Вывод самого молодого жителя
                        printOldest(humansList, addressList);
                        break;
                    case 6: //Вывод людей, проживающих на одной улице
                        System.out.print("Введите название улицы: ");
                        String streetName = sc.nextLine(); //ввод названия улицы
                        printLiveOnStreet(humansList, addressList, streetName);
                        break;
                    default: //введено любое целое число не из списка действий
                        System.out.println("Пока-пока.");
                        goOn = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели не целое число");
                sc.nextLine();
            }
        }
        //работа методов без консоли
        //printHumansAll(humansList,addressList); //вывод всех жителей
        //printHumanAtAddress(humansList, addressList, 0); //поиск человека по атрибуту адреса
        //searcByLastname(humansList, addressList, "Зайцева"); //поиск человека по фамилии
        //printBetweenDates( humansList,addressList, new GregorianCalendar(1981, 3, 23), new GregorianCalendar(1989, 3, 23));//вывод людей, родившихся между определенными датами
        //printOldest(humansList, addressList); //вывод самого молодого
        //printLiveOnStreet(humansList, addressList, "Полевая"); //вывод людей с одной улицы
    }

    //вывод всех жителей
    public static void printHumansAll(ArrayList<Humans> humansList, ArrayList<Address> addressList) {
        for (Humans human : humansList) {
            printHuman(human, addressList);
        }
    }

    //вывод одного человека
    static void printHuman(Humans human, ArrayList<Address> addressList) {
        System.out.println("По адресу:" + " ул." + addressList.get(human.address).streetName
                + ", д." + addressList.get(human.address).houseNumber + " проживает: "
                + human.surname + " " + human.name + ", " + formater.format(human.dateOfBirth.getTime()));
    }

    //поиск человека по фамилии
    static void searcByLastname(ArrayList<Humans> humansList, ArrayList<Address> addressList, String surname) {
        boolean found = false; //по-умолчанию человек не найден
        for (Humans human : humansList) {
            if (human.surname.toLowerCase().contains(surname.toLowerCase())) {
                printHuman(human, addressList); //вывод человека
                found = true; //человек найден
                break;
            }
        }
        if (!found) {
            System.out.println("Человек не найден");
        }
    }

    //вывод человека по адресу
    public static void printHumanAtAddress(ArrayList<Humans> humansList, ArrayList<Address> addressList, int indxAddress) {
        boolean found = false; //по-умолчанию человек не найден
        for (Humans human : humansList) {
            if (human.address == indxAddress) { //индекс адреса человека совпадает с заданным
                printHuman(human, addressList); //вывод человека
                found = true; //человек найден
                break;
            }
        }
        if (!found) {
            System.out.println("Адрес не найден");
        }
    }

//вывод людей, родившихся между определенными датами
    public static void printBetweenDates(ArrayList<Humans> humansList, ArrayList<Address> addressList, GregorianCalendar minDate, GregorianCalendar maxDate) {
        for (Humans human : humansList) {
            if (human.dateOfBirth.after(minDate) && human.dateOfBirth.before(maxDate) || human.dateOfBirth.before(minDate) && human.dateOfBirth.after(maxDate)) {
                printHuman(human, addressList); //вывод человека
            }
        }
    }

    //вывод самого молодого человека
    public static void printOldest(ArrayList<Humans> humansList, ArrayList<Address> addressList) {
        Calendar maxDate = humansList.get(0).dateOfBirth; //первый житель принимается за самого молодого
        int count = 0, i = 0;
        for (Humans human : humansList) { //проход по массиву всех жителей
            if (human.dateOfBirth.after(maxDate)) { //сравнение дат рождений жителей
                maxDate = human.dateOfBirth; 
                count = i;
            }
            i++;
        }
        System.out.print("Самый молодой житель: ");
        printHuman(humansList.get(count), addressList); //вывод самого молодого человека

    }
    //вывод людей, проживающих на одной улице
    public static void printLiveOnStreet(ArrayList<Humans> humansList, ArrayList<Address> addressList, String streetName) {
        int i = 0;
        boolean found = false; //люди не найдены
        for (Address adr : addressList) { //проход по массиву адресов
            if (adr.streetName.toLowerCase().contains(streetName.toLowerCase())) { //найдены адреса с заданной улицей
                for (int j = 0; j < humansList.size(); j++) { //проход по массиву людей
                    if (humansList.get(j).address == i) { //найден человек с индексом адреса заданной улицы
                        printHuman(humansList.get(j), addressList); //вывод человека
                        found = true; //найден человек
                        break;
                    }
                }
            }
            i++;
        }
        if (!found) {
            System.out.println("Жители не найдены");
        }
    }
    //консольный ввод даты 
    static GregorianCalendar inputDate() {
        int d = 0, m = 0, y = 0;
        //try{
        System.out.print("день: ");
        d = sc.nextInt();
        System.out.print("месяц: ");
        m = sc.nextInt();
        System.out.print("Год: ");
        y = sc.nextInt();
        return new GregorianCalendar(y, m, d);
    }
    //адрес
    public static class Address {
        String streetName; //название улицы
        int houseNumber; //номер дома
        private Address(String city, String streetName, int houseNumber) {
            this.streetName = streetName;
            this.houseNumber = houseNumber;
        }
    }
    //человек
    public static class Humans {
        String name; //имя
        String surname; //фамилия
        int address; //индекс адреса
        Calendar dateOfBirth; //дата рождения
        private Humans(String name, String surname, int address, Calendar dateOfBirth) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.dateOfBirth = dateOfBirth;
        }
    }

}
