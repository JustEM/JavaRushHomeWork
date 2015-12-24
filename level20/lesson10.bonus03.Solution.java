package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/// JustEM - задача решена 24.12.2015
/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };


        List<Word> wordsList = detectAllWords(crossword, "home", "same");
        for(Word w : wordsList)
            System.out.println(w);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wList = new ArrayList<Word>();
        Word word;
        for(String w : words){
            for(int i = 0; i < crossword.length; i++)
                for(int j = 0; j < crossword[0].length; j++){
                    if(crossword[i][j] == w.charAt(0)){
                        word = new Word(w);
                        if (searchWord(crossword, word, w, i, j))
                            wList.add(word);
                    }
                }

        }
        return wList;
    }

    public static boolean searchWord(int[][] cross, Word word, String wStr, int i, int j){
        if(wStr.length() == 1){ //Проверка на слово длиной в 1 букву
            word.setStartPoint(j,i);
            word.setEndPoint(j,i);
            return true;
        }

        word.setStartPoint(j, i);
        int cnter = 1;  //Начнем со второй буквы
        boolean flag = true;
        int curI=i, curJ = j-1;
        if(checkGoLeft(cross, wStr, i, j)){
            while (cnter < wStr.length()-1)
                if (cross[curI][curJ--] != wStr.charAt(cnter++)){
                    flag = false;
                    break;
                }
            if(flag){
                word.setEndPoint(curJ,curI);
                return true;
            }
            else flag = true;
        }

        cnter = 1; curI = i; curJ = j+1;
        if(checkGoRight(cross, wStr, i, j)){
            while (cnter < wStr.length()-1)
                if (cross[curI][curJ++] != wStr.charAt(cnter++)){
                    flag = false;
                    break;
                }
            if(flag){
                word.setEndPoint(curJ,curI);
                return true;
            }
            else flag = true;
        }

        cnter = 1; curI = i-1; curJ = j;
        if(checkGoUp(cross, wStr, i, j)){
            while(cnter < wStr.length()-1)
                if(cross[curI--][curJ] != wStr.charAt(cnter++)){
                    flag = false;
                    break;
                }
            if(flag){
                word.setEndPoint(curJ,curI);
                return true;
            }
            else flag = true;

        }

        cnter = 1; curI = i+1; curJ = j;
        if(checkGoDown(cross, wStr, i, j)){
            while(cnter < wStr.length()-1)
                if(cross[curI++][curJ] != wStr.charAt(cnter++)){
                    flag = false;
                    break;
                }
            if(flag)
            {
                word.setEndPoint( curJ,curI);
                return true;
            }
            else flag = true;
        }

        cnter = 1; curI = i-1; curJ = j-1;
        if(checkGoLeftUp(cross, wStr, i, j)){
            while(cnter < wStr.length()-1)
                if(cross[curI--][curJ--] != wStr.charAt(cnter++)){
                    flag = false;
                    break;
                }
            if(flag)
            {
                word.setEndPoint( curJ,curI);
                return true;
            }
            else flag = true;
        }

        cnter = 1; curI = i-1; curJ = j+1;
        if(checkGoRightUp(cross, wStr, i, j)){
            while(cnter < wStr.length()-1)
                if(cross[curI--][curJ++] != wStr.charAt(cnter++)){
                    flag = false;
                    break;
                }
            if(flag)
            {
                word.setEndPoint( curJ,curI);
                return true;
            }
            else flag = true;
        }

        cnter = 1; curI = i+1; curJ = j-1;
        if(checkGoLeftDown(cross, wStr, i, j)){
            while(cnter < wStr.length()-1)
                if(cross[curI++][curJ--] != wStr.charAt(cnter++)){
                    flag = false;
                    break;
                }
            if(flag)
            {
                word.setEndPoint( curJ,curI);
                return true;
            }
            else flag = true;
        }

        cnter = 1; curI = i+1; curJ = j+1;
        if(checkGoRightDown(cross, wStr, i, j)){
            while(cnter < wStr.length()-1)
                if(cross[curI++][curJ++] != wStr.charAt(cnter++)){
                    flag = false;
                    break;
                }
            if(flag)
            {
                word.setEndPoint( curJ,curI);
                return true;
            }
            else flag = true;
        }
        return false;
    }

    //Функции проверки необходимости искать слово в определённом направлении
    private static boolean checkGoLeft(int[][] cross, String wStr, int i, int j){
        if(j-wStr.length()+1 >= 0)
            if (cross[i][j-wStr.length()+1] == wStr.charAt(wStr.length() - 1))
                return true;
        return false;
    }
    private static boolean checkGoRight(int[][] cross, String wStr, int i, int j){
        if(j+wStr.length()-1 < cross[i].length)
            if(cross[i][j+wStr.length()-1] == wStr.charAt(wStr.length()-1))
                return true;
        return false;
    }
    private static boolean checkGoUp(int[][] cross, String wStr, int i, int j){
        if(i-wStr.length()+1 >= 0)
            if(cross[i-wStr.length()+1][j] == wStr.charAt(wStr.length()-1))
                return true;
        return false;
    }
    private static boolean checkGoDown(int[][] cross, String wStr, int i, int j){
        if(i+wStr.length()-1 < cross.length)
            if(cross[i+wStr.length()-1][j] == wStr.charAt(wStr.length()-1))
                return true;
        return false;
    }
    private static boolean checkGoLeftUp(int[][] cross, String wStr, int i, int j){
        if(j-wStr.length()+1 >= 0 && i-wStr.length()+1 >= 0)
            if (cross[i-wStr.length()+1][j-wStr.length()+1] == wStr.charAt(wStr.length() - 1))
                return true;
        return false;
    }
    private static boolean checkGoLeftDown(int[][] cross, String wStr, int i, int j){
        if(j-wStr.length()+1 >= 0 && i+wStr.length()-1 < cross.length)
            if(cross[i+wStr.length()-1][j-wStr.length()+1] == wStr.charAt(wStr.length()-1))
                return true;
        return false;
    }
    private static boolean checkGoRightUp(int[][] cross, String wStr, int i, int j){
        if(j+wStr.length()-1 < cross[i].length && i-wStr.length()+1 >= 0)
            if(cross[i-wStr.length()+1][j+wStr.length()-1] == wStr.charAt(wStr.length()-1))
                return true;
        return false;
    }
    private static boolean checkGoRightDown(int[][] cross, String wStr, int i, int j){
        if(j+wStr.length()-1 < cross[i].length && i+wStr.length()-1 < cross.length)
            if(cross[i+wStr.length()-1][j+wStr.length()-1] == wStr.charAt(wStr.length()-1))
                return true;
        return false;
    }

    //Базовый класс, хранящий информацию о слове (дан по условию).
    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
