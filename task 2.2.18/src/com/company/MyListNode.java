package com.company;

public class MyListNode<T> {
    /**
     * свойство для хранения значения узла
     */
    private T value;
    /**
     * ссылка на следующий узел
     */
    private MyListNode<T> next;

    /**
     * Конструктор
     * @param value Значение элемента
     * @param next Ссылка на следущий элемент
     */
    public MyListNode(T value, MyListNode<T> next)
    {
        this.value = value;
        this.next = next;
    }

    /**
     * Конструктор
     * @param value Значение элемента
     */
    public MyListNode(T value)
    {
        this(value, null);
    }

    /**
     * Конструктор по умолчанию
     */
    public MyListNode()
    {
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
    public MyListNode<T> getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(MyListNode<T> next) {
        this.next = next;
    }

}
