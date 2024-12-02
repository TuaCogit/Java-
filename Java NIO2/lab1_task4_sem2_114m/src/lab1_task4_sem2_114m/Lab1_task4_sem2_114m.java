package lab1_task4_sem2_114m;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Lab1_task4_sem2_114m {

    public static void main(String[] args) throws IOException {
        System.out.print("Введите символ: ");
        char ch = (char) System.in.read (); 
        Path path = Paths.get("src/doc.txt"); //получение файла
        //char ch = 'e';
        long countChar = counterChar(path, ch);
        System.out.println("Символ '" + ch + "' появляется в файле " + countChar + " раз");
    }

    //метод считает сколько в файле переданного символа 
    public static long counterChar(Path path, char ch) throws IOException {
        try (Stream<String> stream = Files.lines(path)) {//чтения всех строк из файла в виде потока
            return stream.flatMapToInt(String::chars).filter(c -> c == (int) ch).count(); //подсчет нужного символа
        }
    }
}
