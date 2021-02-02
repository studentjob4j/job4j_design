package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedlist<E> implements Iterable<E> {

    private Node<E> first;
    private int size = 0;
    private int modCount = 0;

    public void add(E value) {
        if (size == 0) {
            this.first = new Node<>(value);
        } else {
            getNode(size - 1).next = new Node<>(value);
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        return getNode(index).data;
    }

    private Node<E> getNode(int index) {
        Node<E> nodeTemp = this.first;
        try {
            if (Objects.checkIndex(index, size) == index) {
              for (int i = 0; i < index; i++) {
                nodeTemp = nodeTemp.next;
              }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return nodeTemp;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            private int expectedmodCount = modCount;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (this.expectedmodCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(this.index++);
            }
        };
    }

    private class Node<E> {
        E data;
        Node<E> next;
        Node(E data) {
            this.data = data;
        }
    }
}

