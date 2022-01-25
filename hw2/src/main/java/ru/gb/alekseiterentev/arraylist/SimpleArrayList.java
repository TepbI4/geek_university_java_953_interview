package ru.gb.alekseiterentev.arraylist;

import ru.gb.alekseiterentev.SimpleList;

import java.util.Arrays;

public class SimpleArrayList<E> implements SimpleList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    protected E[] data;
    protected int size;

    public SimpleArrayList() {
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(E value) {
        if (isFull()) {
            data = Arrays.copyOf(data, 2 * size);
        }

        data[size++] = value;
    }

    @Override
    public boolean remove(E value) {
        if (isEmpty()) {
            return false;
        }

        if (value == null) {
            return false;
        }

        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (!found) {
                if (value.equals(data[i])) {
                    found = true;
                } else {
                    continue;
                }
            } else {
                data[i-1] = data[i];
            }
        }
        size--;
        return true;
    }

    @Override
    public boolean contains(E value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return data[i];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    private boolean isFull() {
        return size == data.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
