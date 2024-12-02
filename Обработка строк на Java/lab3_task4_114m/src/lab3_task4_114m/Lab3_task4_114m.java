package lab3_task4_114m;

public class Lab3_task4_114m {
    public static void main(String[] args) {
        //создание строкового массива
        String[] shirts = new String[11];
        shirts[0] = "S001,Black Polo Shirt,Black,XL";
        shirts[1] = "S002,Black Polo Shirt,Black,L";
        shirts[2] = "S003,Blue Polo Shirt,Blue,XL";
        shirts[3] = "S004,Blue Polo Shirt,Blue,M";
        shirts[4] = "S005,Tan Polo Shirt,Tan,XL";
        shirts[5] = "S006,Black T-Shirt,Black,XL";
        shirts[6] = "S007,White T-Shirt,White,XL";
        shirts[7] = "S008,White T-Shirt,White,L";
        shirts[8] = "S009,Green T-Shirt,Green,S";
        shirts[9] = "S010,Orange T-Shirt,Orange,S";
        shirts[10] = "S011,Maroon Polo Shirt,Maroon,S";
        //создание массива класса Shirt
        Shirt[] newShirts = new Shirt[11];
        //перобразование строковый массив в массив класса Shirt 
        //и вывыод его в консоль
        for (int i = 0; i < shirts.length; i++) {
            newShirts[i] = new Shirt(shirts[i]);
            //вывод строки Shirt переопределеным методом toString
            System.out.println(newShirts[i].toString());
        }
    }

    static public class Shirt {
        private String id, description, color, size;
   
        public Shirt(String shirt) {
            //разбиение строки на подстроки 
            String[] str = shirt.split(",");
            id = str[0];
            description = str[1];
            color = str[2];
            size = str[3];
        }
        //переопределение метода toString
        @Override
        public String toString() {
            return "id: " + id + ", description: " + description + ", color: " + color + ", size:" + size + ";";
        }
    }
}
