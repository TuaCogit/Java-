package lab1;
import java.util.Scanner;

public class QuadraticEquation {
    double a, b, c;
        
    public  void scan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значение a: ");
        a = scanner.nextDouble();
        System.out.print("Введите значение b: ");
        b = scanner.nextDouble();
        System.out.print("Введите значение c: ");
        c = scanner.nextDouble();
       }
        
    public class Discriminant {
        
        public void calc() {
           System.out.println("Вычисляем дискриминант по формуле: d = " + b * b + " - 4 * " + a + " * " + c);
           double d = b * b - 4 * a * c;
           System.out.println("Дискриминант равен: "+ d);
        if (d > 0) {
            double x1 = (-b - Math.sqrt(d)) / (2 * a);
            double x2 = (-b + Math.sqrt(d)) / (2 * a);
            System.out.println("Корни уравнения: x1 = " + x1 + ", x2 = " + x2);
        } else if (d == 0) {
            double x;
            x = -b / (2 * a);
            System.out.println("Уравнение имеет единственный корень: x = " + x);
        } else {
            System.out.println("Уравнение не имеет действительных корней");
        }
       }
    }
}
