package ru.gb.alekseiterentev.linkedlist;

import java.util.Iterator;

public class TestSimpleLinkedListApp {

    public static void main(String[] args) {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.display();
        System.out.println();

        for (Integer integer : linkedList) {
            System.out.println(integer);
        }

        System.out.println();

        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            System.out.println(integer);
        }

        System.out.println();

        System.out.println(linkedList.get(4));
    }
}
