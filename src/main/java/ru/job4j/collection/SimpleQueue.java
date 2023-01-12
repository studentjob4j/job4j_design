package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * SimpleQueue
 * @author Shegai Evgenii
 * @version 1.0
 * @since 13.01.2023
 */

public class SimpleQueue<T> {

   private SimpleStack<T> in = new SimpleStack<>();
   private  SimpleStack<T> out = new SimpleStack<>();
   private int inSize = 0;
   private int outSize = 0;

    /**
     *Добавляем элемент в начало по принципу стека и увеличиваем счетчик
     * так как у нас нет доступа к собственному счетчику класса ФорварЛинкидЛист
     */
   public void push(T value) {
       in.push(value);
       inSize++;
   }

    /**
     * Если два стека пусты то кидаем исключение
     * Иначе если отдающий стек пустой то пока принимающий не пуст , получаем
     * элементы из него и добавляем их в отдающий стек
     * иначе если отдающий стек не пустой просто отдаем из него элемент
     */
   public T poll() {
       if (inSize == 0 && outSize == 0) {
           throw new NoSuchElementException();
       }
       if (outSize == 0) {
           while (inSize != 0) {
               out.push(in.pop());
               inSize--;
               outSize++;
           }
       }
       outSize--;
       return out.pop();
   }
}
