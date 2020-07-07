package com.company;

public class MyDoublyLinkedListNode<T> {
    /**
     * свойство для хранения значения узла
     */
    private T value;
    /**
     * ссылка на следующий узел
     */
    private MyDoublyLinkedListNode<T> next;
    /**
     * ссылка на предыдущий узел
     */
    private MyDoublyLinkedListNode<T> prev;

    /**
     * Конструктор
     *
     * @param value Значение элемента
     * @param next  Ссылка на следущий элемент
     * @param prev  Ссылка на предыдущий элемент
     */
    public MyDoublyLinkedListNode(T value, MyDoublyLinkedListNode<T> next,
                                  MyDoublyLinkedListNode<T> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    /**
     * Конструктор
     *
     * @param value Значение элемента
     */
    public MyDoublyLinkedListNode(T value) {
        this(value, null, null);
    }

    /**
     * Конструктор по умолчанию
     */
    public MyDoublyLinkedListNode() {
        this(null);
    }

    /**
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * @return the next
     */
    public MyDoublyLinkedListNode<T> getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(MyDoublyLinkedListNode<T> next) {
        this.next = next;
    }

    /**
     * @return the prev
     */
    public MyDoublyLinkedListNode<T> getPrev() {
        return prev;
    }

    /**
     * @param prev the prev to set
     */
    public void setPrev(MyDoublyLinkedListNode<T> prev) {
        this.prev = prev;
    }
}
