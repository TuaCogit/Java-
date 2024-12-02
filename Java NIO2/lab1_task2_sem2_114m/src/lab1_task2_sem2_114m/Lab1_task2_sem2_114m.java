package lab1_task2_sem2_114m;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import static java.nio.file.AccessMode.WRITE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static sun.security.krb5.Confounder.bytes;

public class Lab1_task2_sem2_114m {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Path path = Paths.get("src/test.dat");
        //запись случайных целых чисел в файл
        int[] array = {1, 2, 3, 4, 5}; // Массив целых чисел
        try (FileWriter fw = new FileWriter(path.toFile())) {
            int strs[] = new int[20];
            for (int i = 0; i < strs.length; i++) {
                strs[i] = (int) (Math.random() * 100);
                fw.write(strs[i] + " ");
            }
        }

        //-------int----
        try {
            //получение массва чисел int
            int[] dataInt = Files.lines(path).flatMap(e -> Stream.of(e.split(" "))).mapToInt(num -> Integer.parseInt(num)).toArray();
            int average1 = 0, average2 = 0, average3 = 0; //средние
            //расчет среднего арифметического
            if (dataInt.length > 0) {//файл не пустой
                int sum1 = 0, sum2 = 0, sum3 = 0; //суммы всех чисел
                //среднее арифметическое всех чисел
                for (int j = 0; j < dataInt.length; j++) {
                    sum1 += dataInt[j];
                }
                average1 = (int) (sum1 / dataInt.length);
                //среднее арифметическое второй половины
                for (int j = dataInt.length / 2; j < dataInt.length; j++) {
                    sum2 += dataInt[j];
                }
                average2 = (int) (sum2 / dataInt.length);
                //среднее арифметическое третьей четверти
                for (int j = (dataInt.length * 3) / 4; j < dataInt.length; j++) {
                    sum3 += dataInt[j];
                }
                average3 = (int) (sum3 / dataInt.length);
            }
            System.out.println("Для int: кол-во чисел int = " + dataInt.length);
            System.out.println("среднее арифметическое: " + average1);
            System.out.println("cреднее арифметическое второй половины: " + average2);
            System.out.println("среднее арифметическое третьей четверти: " + average3);
            System.out.println(Arrays.toString(dataInt));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //-------float----
        try {
            //получение массва чисел float
            Float[] dataFloat = Files.lines(path).flatMap(e -> Stream.of(e.split(" "))).map(Float::valueOf).toArray(Float[]::new);
            float average1 = 0, average2 = 0, average3 = 0; //средние
            //расчет среднего арифметического
            if (dataFloat.length > 0) {//файл не пустой
                float sum1 = 0, sum2 = 0, sum3 = 0; //суммы всех чисел
                //среднее арифметическое всех чисел
                for (int j = 0; j < dataFloat.length; j++) {
                    sum1 += dataFloat[j];
                }
                average1 = (float) (sum1 / dataFloat.length);
                //среднее арифметическое второй половины
                for (int j = dataFloat.length / 2; j < dataFloat.length; j++) {
                    sum2 += dataFloat[j];
                }
                average2 = (float) (sum2 / dataFloat.length);
                //среднее арифметическое третьей четверти
                for (int j = (dataFloat.length * 3) / 4; j < dataFloat.length; j++) {
                    sum3 += dataFloat[j];
                }
                average3 = (float) (sum3 / dataFloat.length);
            }
            System.out.println("Для float:");
            System.out.println("среднее арифметическое: " + average1);
            System.out.println("cреднее арифметическое второй половины: " + average2);
            System.out.println("среднее арифметическое третьей четверти: " + average3);
            System.out.println(Arrays.toString(dataFloat));
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------byte----

        try {
            int[] dataInt = Files.lines(path).flatMap(e -> Stream.of(e.split(" "))).mapToInt(num -> Integer.parseInt(num)).toArray();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            for (int i = 0; i < dataInt.length; ++i) {
                dos.writeInt(dataInt[i]);
            }
            byte[] dataByte=baos.toByteArray();
            byte average1 = 0, average2 = 0, average3 = 0; //средние
            //расчет среднего арифметического
            if (dataByte.length > 0) {//файл не пустой
                byte sum1 = 0, sum2 = 0, sum3 = 0; //суммы всех чисел
                //среднее арифметическое всех чисел
                for (int j = 0; j < dataByte.length; j++) sum1 += (byte) dataByte[j];;
                average1 = (byte) (sum1 / dataByte.length);
                //среднее арифметическое второй половины
                for (int j = dataByte.length / 2; j < dataByte.length; j++) sum2 += dataByte[j];
                average2 = (byte) (sum2 / dataByte.length);
                //среднее арифметическое третьей четверти
                for (int j = (dataByte.length * 3) / 4; j < dataByte.length; j++) sum3 += dataByte[j];
                average3 = (byte) (sum3 /(byte) dataByte.length);
            }
            System.out.println("Для byte:");
            System.out.println("среднее арифметическое: " + average1);
            System.out.println("cреднее арифметическое второй половины: " + average2);
            System.out.println("среднее арифметическое третьей четверти: " + average3);
            System.out.println(Arrays.toString(dataByte));
        } catch (IOException e) {
            e.printStackTrace();
        }
   
        
    }
}
