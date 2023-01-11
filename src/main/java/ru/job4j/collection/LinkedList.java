package ru.job4j.collection;

/**
 * SimpleLinkedList
 * @author Shegai Evgenii
 * @version 1.0
 * @since 12.01.2023
 */

public interface LinkedList<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}
