package ru.gb.alekseiterentev;

public interface SimpleList<E> {

    void add(E value);

    boolean remove(E value);

    boolean contains(E value);

    int size();

    boolean isEmpty();

    void display();

    E get(int i);
}
