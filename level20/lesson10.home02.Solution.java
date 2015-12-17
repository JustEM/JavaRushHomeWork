package com.javarush.test.level20.lesson10.home02;

import java.io.*;
/// JustEM - Задача решена 14.12.2015
/* Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуйте объект в методе getOriginalObject предварительно определив, какого именно типа там объект.
Реализуйте интерфейс Serializable где необходимо.
*/
public class Solution implements Serializable {
    public A getOriginalObject(ObjectInputStream objectStream) throws IOException, ClassNotFoundException {
        A a = null;
        Object o = (A)objectStream.readObject();
        B b = null;
        if(o instanceof B)
            return (B)o;
        else if (o instanceof A)
            return (A)o;
        else throw new ClassNotFoundException();
    }

    public class A implements Serializable{
        public A(){
            super();
        }
    }

    public class B extends A {

        public B() {
            System.out.println("inside B");
        }
    }
}
