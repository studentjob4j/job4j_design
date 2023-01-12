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

    private Node<T> head;
    private int size = 0;
    private int modCount = 0;

    @Override

    public void add(T value) {
        if (size == 0) {
            this.head = new Node<>(value, null);
        } else {
            getNode(size - 1).next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }


    /**
     * Если голова пустая , то делаем новую ноду головой
     * Иначе создаем временую ноду которая ссылается на хед
     * в хед записываем новую ноду, и хед некст будет ссылаться на предыдущее значение хед
     * это нода темп
     */
    public void addFirst(T value) {
        Node<T> newHead = new Node(value, null);
        if (head == null) {
           this.head = newHead;
           size++;
           modCount++;
           return;
        }
        Node<T> temp = head;
        head = newHead;
        head.next = temp;
        size++;
        modCount++;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    private Node<T> getNode(int index) {
        Node<T> nodeTemp = this.head;
        if (Objects.checkIndex(index, size) == index) {
            for (int i = 0; i < index; i++) {
                nodeTemp = nodeTemp.next;
            }
        }
        return nodeTemp;
    }

    /**
     * Если голова пустая то кидаем исключение иначе
     * создаем еще одну ссылку на голову - темп, получаем данные из головы
     * голова передвигается на один элемент вперед, и зануляем ссылки и значения ноды,
     * которая была раньше головой
     */

    public T deleteFirst() {
        Node<T> temp = head;
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = temp.data;
        head = temp.next;
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
            private Node<T> current = head;
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
