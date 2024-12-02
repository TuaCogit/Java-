package lab3_task5_114m;

public class Lab3_task5_114m {

    public static void main(String[] args) {
        //вывод номеров, начинающихся с +
        System.out.println("before: +79175655655 after: "+new Phone("+79175655655").toString());
        System.out.println("before: +104289652211 after: "+new Phone("+104289652211").toString());
        //вывод номеров, начинающихся с 8
        System.out.println("before: 89175655655 after: "+new Phone("89175655655").toString());
    }
 
    public static class Phone {
        String countryCode, firstThreenumb, threenumb, fournumb;

        public Phone(String phoneNumber) {
            //если номер начинается на +
            if (phoneNumber.charAt(0) == '+') {
            //код страны первые цифры без учета 10ти цифр с конца
                countryCode = phoneNumber.substring(0, phoneNumber.length() - 10);
            //если номер не начинается с +
            } else {
                countryCode = "+7"; //то код россии
            }
            //первые три цифры после кода страны
            firstThreenumb = phoneNumber.substring(phoneNumber.length() - 10, phoneNumber.length() - 7);
            threenumb = phoneNumber.substring(phoneNumber.length() - 7, phoneNumber.length() - 4); //далее четыре цифры
            fournumb = phoneNumber.substring(phoneNumber.length() - 4); //последние три цифры  
        }
        //переоперделение метода преобразования строки
        @Override
        public String toString() {
            return countryCode+firstThreenumb+"-"+threenumb+"-"+fournumb;
        }
    }
}
