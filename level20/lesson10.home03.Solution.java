package com.javarush.test.level20.lesson10.home03;

import java.io.*;
/// JustEM - задача решена 14.12.2015
/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution  implements Serializable{
    public static class A {
        protected String name = "A";

        public A(){}
        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {
        public B(){
            super();
        }
        public B(String name) {
            super(name);
            this.name += name;
        }
        private void writeObject(ObjectOutputStream out) throws IOException{
            out.writeObject(this.name);
        }
        private void readObject(ObjectInputStream inp) throws IOException, ClassNotFoundException{
            this.name = (String)inp.readObject();
        }
    }


    public static void main(String[] args) throws IOException
    {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("c:/intel/222.txt"));
        Solution s = new Solution();
        Solution.B b = s.new B("1234554321");
        out.writeObject(b);
        out.close();

    }
}
