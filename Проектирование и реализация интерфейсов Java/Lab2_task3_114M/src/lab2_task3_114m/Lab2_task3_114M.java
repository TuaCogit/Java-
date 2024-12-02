package lab2_task3_114m;

import java.util.Objects;

public class Lab2_task3_114M {

    public static void main(String[] args) {
        ExtendedClass ex1 = new ExtendedClass((byte)3, 303, 33.33, "stroka");
        ExtendedClass ex2 = new ExtendedClass((byte)5, 505, 55.55, "stroka");
        ExtendedClass ex3 = new ExtendedClass((byte)3, 303, 33.33, "stroka");

        //сравнение экземпляров класса
        System.out.println(ex1.equals(ex3)); //true
        System.out.println(ex1.equals(ex2)); //false
        System.out.println(ex2.equals(ex3)); //false
        //вывод хэш-кодов
        System.out.println(ex1.hashCode());
        System.out.println(ex2.hashCode());
        System.out.println(ex3.hashCode());
        //сравнение хэш-кодов
        System.out.println(ex1.hashCode() == ex3.hashCode()); //true
        System.out.println(ex1.hashCode() == ex2.hashCode());//false
        System.out.println(ex2.hashCode() == ex3.hashCode()); //false
        //вывод объектов
        System.out.println(ex1.toString()); //3, 303, 33.33, stroka
        System.out.println(ex2.toString()); //5, 505, 55.55, stroka
        System.out.println(ex3.toString()); //3, 303, 33.33, stroka

    }
    //класс с переопределеными методами equals, hashCode и toString
    public static class ExtendedClass {
        byte b;
        int i;
        double d;
        String s;

        public ExtendedClass(byte b, int i, double d, String s) {
            this.b=b;
            this.i = i;
            this.d = d;
            this.s = s;
        }

        //переопределение метода сравнения
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            ExtendedClass ex = (ExtendedClass) obj;
            return ex.b==b
                    && ex.i == i
                    && ex.d == d
                    && ex.s.equals(s);
        }

        //переопределение метода получения хэш-кода
        @Override
        public int hashCode() {
            int result = 17;
            // 31 используется, поскольку это простое число, а также обеспечивает более высокую производительность, т.к:
            // 31 * result == (result << 5) - result
            result = 31 * result + b;
            result = 31 * result + i;
            result = (int) (31 * result + d);
            result = 31 * result + (s == null ? 0 : s.hashCode());
            return result;
        }
        //переопределение метода преобразования в строку
        @Override
        public String toString()  {
            return this.b+", "+this.i+", "+this.d+", "+s;
        }
        }
    }
