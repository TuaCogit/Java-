package lab1_task3_sem2_114m;

import com.sun.xml.internal.ws.util.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.LinkedList;
import java.util.List;

public class Lab1_task3_sem2_114m {

    public static void main(String[] args) throws IOException {
        String str = "java"; //слово триггер
        Path path = Paths.get("src/doc.txt"); //получение файла
        //вызов метода и вывод прочитанного файла до 3го упоминания java
        for (String st : readfileCountStr(path, str)) {
            System.out.println(st);
        }
    }
    //чтение файла до 3го вхождения слова
    private static List<String> readfileCountStr(Path path, String str) {
        List<String> listStr = new LinkedList<>(); //здесь хранится прочитанный текст
        try (BufferedReader br = Files.newBufferedReader((path), StandardCharsets.UTF_8)) {
            int count = 0; //счетчик слова триггера
            String readStr; //считываемая строка
            //считывать пока не закончится файл и слово встречалось меньше 3х раз
            while (((readStr = br.readLine()) != null) && count != 3) {
                int index = readStr.indexOf(str);//индекс вхождения слова в строку
                while (index != -1) {
                    count++;//подсчет вхождения слова в текст
                    if ( count == 3) { //строка содержится
                        readStr = readStr.substring(0, index + str.length()); //до 3го вхождения слова
                    }
                    index = readStr.indexOf(str, index + 1);
                }
                listStr.add(readStr); //считанная строка
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listStr;
    }
}
