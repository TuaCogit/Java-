
package lab3_task3_sem2_114m;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Lab3_task3_sem2_114m {

    public static void main(String[] args) {
        Supportive supr = new Supportive();//вспомогательный класс
        //создание экземпляров классов
        TheFirstStream firstStream = new TheFirstStream(supr);
        TheSecondStream secondStream = new TheSecondStream(supr);
        //запуск потоков
        new Thread(firstStream).start();
        new Thread(secondStream).start();
    }
//вспомогательный класс 
    static public class Supportive {
        int element = 0; //индекс текущий элемент списка
        //список строк, куда читается файл
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        BufferedReader reader;
        boolean isNotEmpty = true;
        Supportive() {
            openFile();
        }
        //открытие файла текущей программы
        void openFile() {
            list.add(null);
            try { 
                reader = new BufferedReader(new FileReader(new File("D:/Users/Documents/NetBeansProjects/Magistratura/Lab3_task3_sem2_114m/src/lab3_task3_sem2_114m/Lab3_task3_sem2_114m.java")));
            } catch (FileNotFoundException ex) {}
        }
        void setElem(int i) { //передача текущего индекса
            element = i;
        }
        //чтение файла построчно и запись в список
        public synchronized void setVectorInt() throws IOException, InterruptedException {
            String line;//считанная строка
            //текущая позиция вектора уже заполена
            if (list.get(element) != null) {//ждем вывода строки
                wait();
            }
            //если файл не закончился, считываем строку и помещаем в список
            if ((line = reader.readLine()) != null) {
                list.add(element, line);
            } else { //иначе сигнализируем, что конец файла
                isNotEmpty = false;}
            notify(); //продолжает работу потока, у которого ранее был вызван метод wait()
        }
        //получение считанной строки, анализ вхождение образца и вывод строки на экран.
        public synchronized void getVectorInt() throws InterruptedException {
            if (list.get(element) == null) {//ждем чтения строки
                wait();
            }
            //если строка содержит заданное слово, она выводится
            if(isNotEmpty!=false && list.get(element).contains("java")){
            System.out.println(list.get(element)); 
            }
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

             while(supr.isNotEmpty!=false) {
                try { //чтение файла
                    supr.setVectorInt();
                } catch (IOException ex) {} 
                catch (InterruptedException ex) {}
            }
        }
    }
//класс второго потока выводит считанную строку с условием
    static class TheSecondStream implements Runnable {
        Supportive supr;
        TheSecondStream(Supportive supr) {
            this.supr = supr;
        }
        @Override
        public void run() {
            int index=1;//индекс текущей позиции
            while(supr.isNotEmpty!=false) {
                try {
                    supr.getVectorInt();//вывод считанной строки
                } catch (InterruptedException ex) {}
                supr.setElem(index);
                index++;
            }
        }
    }
}
