package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/
/*РЕШЕНИЕ ПРИНЯТО СЕРВЕРОМ 21.10.2015 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        List<String> words = new ArrayList<String>(); //Список хранения слов, длиной > 6 символов.
        while(reader.ready()){
            String[] tokens = reader.readLine().split(" ");
            for(String s : tokens)
                if(s.length() > 6)
                    words.add(s);
        }
        for (int i = 0; i < words.size()-1; i++)
            writer.write(words.get(i) + ",");
        writer.write(words.get(words.size()-1));
        reader.close();
        writer.close();
    }
}

/* Evgeny JustEM */
