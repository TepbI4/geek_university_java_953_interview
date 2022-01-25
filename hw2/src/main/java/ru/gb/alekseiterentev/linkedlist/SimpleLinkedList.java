package ru.gb.alekseiterentev.linkedlist;

import ru.gb.alekseiterentev.SimpleList;

import java.util.Iterator;

import static java.util.Objects.nonNull;

public class SimpleLinkedList<E> implements SimpleList<E>, Iterable<E> {

    protected Node<E> first;
    protected Node<E> last;
    protected int size;

    @Override
    public void add(E value) {
        if (isEmpty()) {
            Node<E> newNode = new Node<>(value, first, null);
            if (!isEmpty()) {
                first.prev = newNode;
            }
            first = newNode;
            last = first;
        } else {
            Node<E> newNode = new Node<>(value, null, last);
            last.next = newNode;
            last = newNode;
        }

        size++;
    }

    @Override
    public boolean remove(E value) {
        Node<E> current = first;
        Node<E> prev = null;
        Node<E> next = null;

        while (current != null) {
            if (current.item.equals(value)) {
                break;
            }
            prev = current;
            current = current.next;
            next = current.next;
        }

        if (current == null) {
            return false;
        } else if(current == first) {
            Node<E> removedNode = first;
            first = removedNode.next;
            first.prev = null;
            removedNode.next = null;
            size--;

            if (isEmpty()) {
                last = null;
            }

            return true;
        } else if(current == last) {
            last.prev = null;
            last = prev;
            last.next = null;
        }

        prev.next = current.next;
        next.prev = prev;
        current.next = null;
        current.prev = null;
        size--;

        return true;
    }

    @Override
    public boolean contains(E value) {
        if (isEmpty()) {
            return false;
        }

        Node<E> current = first;
        while (nonNull(current)) {
            if (current.item.equals(value)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int currentIndex = -1;
        E currentItem = null;
        for (E item : this) {
            currentItem = item;
            currentIndex++;
            if (currentIndex == i) break;
        }
        return currentItem;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        Node<E> current = first;
        while (nonNull(current)) {
            sb.append(current.item);
            if (nonNull(current.next)) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        sb.append("]");

        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkedListIterator<>();
    }

    private class SimpleLinkedListIterator<E> implements Iterator<E> {

        Node<E> currentNode;

        public SimpleLinkedListIterator() {
            currentNode = new Node(null, first, null);
        }

        @Override
        public boolean hasNext() {
            return nonNull(currentNode.next);
        }

        @Override
        public E next() {
            Node<E> value = currentNode.next;
            currentNode = currentNode.next;
            return value.item;
        }
    }

    class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
