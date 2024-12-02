
package address;

import java.util.StringTokenizer;


public class Address {
    
    private String country, region, city, street, house, building, flat;
    
    public static void main(String[] args) {
        
        //Первый и четвертый адреса тестируют наличие разделителей ",.;"
        Address first = new Address("Бразилия,    Северный регион; Бразилиа.. Улица Камней  , 56  ; 5 .; 12", true);
        //Второй и третий адреса тестируют строку при наличии только запятой ,
        Address second = new Address("  Россия ,    Самарская область, Самара  , Свердлова  , 1  , 6    , 89", false);
        Address third = new Address(" France  , Grand Est       , Paris, Rishelie  , 4  , 2, 1", false);
        Address fourth = new Address("   Japan  ;   Kansai ; Tokyo . Takeshita Dori  , 12  ; 1; 100", true);
        
        
        System.out.println(first);
        System.out.println(second);
        System.out.println(third);
        System.out.println(fourth);
    }
    
    public Address(String stringOfAddress, boolean divider){
        if(stringOfAddress == null) throw new NullPointerException();
        String[] s;
        if(divider){
            //разделитель – любой из символов ,.;- (класс StringTokenizer) 
            StringTokenizer st = new StringTokenizer(stringOfAddress, ",.;");
            //Текстовый массив строк, используемый для разложения на слова
            s = new String[st.countTokens()];//подсчет элементов, которые осталось разобрать и возвратить в качестве результата
            int i = 0;
            //После окончания элементов в текущей строке переходит к следующей
            while(st.hasMoreTokens()) s[i++] = st.nextToken();
        }else{
            //разделитель – только запятая (используемый метод split())
            s = stringOfAddress.split(",");
        }

        if(s.length < 7) throw new IllegalArgumentException("Введен неполный адрес!");
        //trim() удаляет все начальные и конечные символы пробела из текущей строки
        country = s[0].trim();
        region = s[1].trim();
        city = s[2].trim();
        street = s[3].trim();
        house = s[4].trim();
        building = s[5].trim();
        flat = s[6].trim();
    }

    @Override
    public String toString() {
        return "Адрес = " + "Страна: " + country + "\n" + " | Регион: " + region + "\n" +
                " | Город: " + city + "\n" + " | Улица: " + street + "\n" + " | Дом: " + 
                house + "\n" + " | Корпус: " + building + "\n" + " | Квартира: " + flat + ';';
    }
}
