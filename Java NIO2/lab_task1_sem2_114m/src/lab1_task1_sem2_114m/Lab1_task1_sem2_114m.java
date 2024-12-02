package lab1_task1_sem2_114m;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lab1_task1_sem2_114m {

    public static void main(String[] args) throws IOException {
        // текущий каталог;
        Path currentDir = Paths.get("."); 
        Path fullPath = currentDir.toAbsolutePath(); //полный путь
        System.out.println("Полный путь: "+ fullPath); //вывод полного пути
        // каталог scr проекта;
        Path srcDir = Paths.get("src/./Lab1_task1_sem2_114m.java").toAbsolutePath();
        System.out.println("Переход в каталог src: "+ srcDir); //вывод полного пути каталога src
        //имя файла с текстом программы 
        Path fileName = srcDir.getFileName(); 
        System.out.println("Имя файла: "+ fileName); //вывод имени файла
    }
}
