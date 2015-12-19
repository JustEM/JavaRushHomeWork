package com.javarush.test.level20.lesson10.bonus02;

/// JustEM - задача решена 19.12.2015
/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    private static int counter = 0;
    public static int getRectangleCount(byte[][] a) {
        counter = 0;
        for (int i = 0; i < a[0].length; i++)
            for (int j = 0; j < a[0].length; j++)
                if(a[i][j] == 1)
                    searchRect(a, i, j);
        for (int i = 0; i < a[0].length; i++) //Возврат измененных значений
            for (int j = 0; j < a[0].length; j++)
                if(a[i][j] == 5)
                    a[i][j] = 1;
        return counter;
    }

    private static void searchRect(byte[][] arr, int I, int J){
        arr[I][J] = 5;
        int l = arr[I].length;
        if((J+1)< l && arr[I][J+1] == 1)
            searchRect(arr, I, J + 1);
        else if ((J-1) < l && (J-1)>-1 && arr[I][J-1] == 1)
            searchRect(arr, I, J-1);
        else if ((I+1) < l && arr[I+1][J] == 1)
            searchRect(arr, I+1, J);
        else
            counter++;
    }
}
