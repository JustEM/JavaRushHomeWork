package com.javarush.test.level22.lesson13.task03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*JustEM - задача решена 03.02.2016 */
/* Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true

+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/
public class Solution {
    public static void main(String[] args)
    {
        System.out.println(checkTelNumber("(123)4-1234-11"));
    }
    public static boolean checkTelNumber(String telNumber) {
        String justNums =telNumber.replaceAll("\\D", ""); //Убираем из строки нОмера все кроме цифр
        if(telNumber.charAt(0) == '+' && justNums.length() != 12)
                return false;
        else if (telNumber.charAt(0) != '+' && justNums.length() != 10)
            return false;

        Pattern pt = Pattern.compile("(\\+\\d+?\\(\\d{3}\\)\\d+$)|" +       //+38(050)1234567
                                        "(\\+\\d{2}\\d{3}\\d{7}$)|" +       //+380501234567
                                        "(\\+\\d+?\\-?\\d+?\\-?\\d+$)|" +   //+3-8050123456-7
                                        "(\\+\\d+?\\(\\d{3}\\)\\d*?\\-?\\d+?\\-?\\d+$)|" + //+3(805)-012345-67
                                        "(\\d{10}$)|" +                         //1234567892
                                        "(\\d*?\\(\\d{3}\\)\\d+$)|" +           //(123)4567890
                                        "(\\d+?\\-?\\d+?\\-?\\d+$)|" +          //1234-1234-11
                                        "(\\d*?\\(\\d{3}\\)\\d*?\\-?\\d+?\\-?\\d+$)");  //(123)4-1234-11
        Matcher mc = pt.matcher(telNumber);
        if(mc.matches())
            return true;
        return false;
    }
}
