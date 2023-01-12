package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Удалить head в односвязном списк
 * @author Shegai Evgenii
 * @version 1.0
 * @since 12.01.2023
 */

public class ForwardLinked<T> implements Iterable<T>, LinkedList<T> {

    private Node<T> first;
    private int size = 0;
    private int modCount = 0;

    @Override
    public void add(T value) {
        if (size == 0) {
            this.first = new Node<>(value, null);
        } else {
            getNode(size - 1).next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    private Node<T> getNode(int index) {
        Node<T> nodeTemp = this.first;
        if (Objects.checkIndex(index, size) == index) {
            for (int i = 0; i < index; i++) {
                nodeTemp = nodeTemp.next;
            }
        }
        return nodeTemp;
    }

    public T deleteFirst() {
        Node<T> temp = first;
        if (first == null) {
            throw new NoSuchElementException();
        }
        T result = temp.data;
        first = temp.next;
        temp.next = null;
        temp.data = null;
        modCount++;
        size--;
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedmodCount = modCount;
            private Node<T> current = first;
            private T result;

            @Override
            public boolean hasNext() {
                if (this.expectedmodCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                result = current.data;
                current = current.next;

                return result;
            }
        };
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
