package ru.gb.alekseiterentev.arraylist;

import ru.gb.alekseiterentev.SimpleList;

public class TestSimpleArrayListApp {

    public static void main(String[] args) {
        SimpleList<Integer> arrayList = new SimpleArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        arrayList.add(11);
        arrayList.display();
        System.out.println();

        System.out.println(arrayList.contains(7));
        System.out.println();

        arrayList.remove(5);
        arrayList.display();
        System.out.println();

        System.out.println(arrayList.get(4));
    }
}
