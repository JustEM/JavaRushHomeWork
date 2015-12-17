package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.List;

/// JustEM - задача решени 16.12.2015
/* Алгоритмы-числа
Число S состоит из M цифр, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static List<Integer> resultList = new ArrayList<Integer>();
    public static void main(String[] args) throws InterruptedException
    {
        long startTime = System.currentTimeMillis();
        int[] res = Solution.getNumbers(20000000);
        long endTime = System.currentTimeMillis();
        System.out.println("Done in: " + (endTime - startTime)/1000d + "sec.");
        System.out.println("Memory occupied: " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/(1024*1024)) + "Mb.");
        for (int result : res)
            System.out.print(" " + result);

    }
    public static int[] getNumbers(int N) throws InterruptedException
    {
        List<Integer> tempList = new ArrayList<Integer>();
        for (int n = 1; n < N; n++){
            int sum = 0, tempN = n, digit;
            int exponent = String.valueOf(tempN).length();  //Получаем степень числа
            for (int i = 0; i< exponent; i++) {
                int currentX = 1;
                digit = tempN % 10;
                for(int j = 0; j< exponent; j++)
                    currentX *= digit;
                sum += currentX;
                tempN /= 10;
            }
            if (n == sum)
                tempList.add(n);
        }
        int[] result = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) //Копирование из списка в массив полученных значений
            result[i] = tempList.get(i);

        return result;
    }
}
