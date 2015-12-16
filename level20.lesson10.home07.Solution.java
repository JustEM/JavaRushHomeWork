package com.javarush.test.level20.lesson10.home07;

import java.io.*;
/// JustEM - Задание решено 16.12.2015
/* Переопределение сериализации в потоке
Сериализация/десериализация Solution не работает.
Исправьте ошибки не меняя сигнатуры методов и класса.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать экземпляр класса Solution
2) записать в него данные  - writeObject
3) сериализовать класс Solution  - writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные - writeObject
6) проверить, что в файле есть данные из п.2 и п.5
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    String fileName;    //Запоминаем имя файла, в который нужно сохранить данные после десериализации
    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);  //Создаем поток, т.к. он не сериализовался.
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        String fName = "c:/intel/ser.txt";
        ObjectOutput objOut = new ObjectOutputStream(new FileOutputStream(fName));
        Solution sol1 = new Solution(fName+"innercopy.txt");
        sol1.writeObject("Hello, I am test string");
        objOut.writeObject(sol1);

        Solution sol2;
        ObjectInput objInp = new ObjectInputStream(new FileInputStream(fName));
        sol2 = (Solution) objInp.readObject();
        sol2.writeObject("Hello, I am test string");
        objOut.close();
        objInp.close();
    }
}
