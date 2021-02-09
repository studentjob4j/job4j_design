package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> result;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> temp = result.next;
            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = temp.value;
                temp = temp.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

    }

    public T deleteFirst() throws NoSuchElementException {
        Node<T> node = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
        result = head;
        head = node;
        size--;
        return result.value;
    }

    public T deleteLast() throws NoSuchElementException {
        int count = 0;
        T result = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> temp = head;
        while (count < size - 2) {
            temp = temp.next;
            count++;
        }
        if (size >= 2) {
            result = temp.next.value;
            temp.next = null;
            size--;
        } else {
            result = temp.value;
            head = null;
            size--;
        }
        return result;
    }
}
