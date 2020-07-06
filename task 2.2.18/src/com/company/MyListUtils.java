package com.company;


import java.lang.reflect.Array;
import java.util.ArrayList;


public class MyListUtils {


    /**
     * Интерфейс для доступа к начальному элементу односвязного списка
     *
     * @param <T>
     */
    public interface HeadContainer<T> {

        public MyListNode<T> getHead();

        public void setHead(MyListNode<T> head);
    }


    /**
     * Подсчет кол-ва элементов в списке
     * ("тяжелая" операция - необходимо пройти весь список для вычисления)
     *
     * @param <T>
     * @param head Начальный элемент списка
     * @return Кол-во значение (элементов) в списке
     */
    public static <T> int getSize(MyListNode<T> head) {
        int count = 0;
        for (MyListNode<T> node = head; node != null; node = node.getNext()) {
            count++;
        }
        return count;
    }

    public static <T> int getSize(MyDoublyLinkedListNode<T> head) {
        int count = 0;
        for (MyDoublyLinkedListNode<T> node = head; node != null; node = node.getNext()) {
            count++;
        }
        return count;
    }

    /**
     * Получение элементов списка в виде массива
     *
     * @param <T>
     * @param head  Голова списка
     * @param dummy Нужен только для того, чтобы получить реальный тип T
     * @return Массив со значениями, которые содержатся в списке
     */
    public static <T> T[] toArrayInner(MyListNode<T> head, T... dummy) {
        Class runtimeType = dummy.getClass().getComponentType();
        T[] result = (T[]) Array.newInstance(runtimeType, getSize(head));

        MyListNode<T> node = head;
        for (int i = 0; i < result.length; i++) {
            result[i] = node.getValue();
            node = node.getNext();

        }
        return result;
    }

    public static <T> T[] toArrayInner(MyDoublyLinkedListNode<T> head, T... dummy) {
        Class runtimeType = dummy.getClass().getComponentType();
        T[] result = (T[]) Array.newInstance(runtimeType, getSize(head));

        MyDoublyLinkedListNode<T> node = head;
        for (int i = 0; i < result.length; i++) {
            result[i] = node.getValue();
            node = node.getNext();

        }
        return result;
    }

    /**
     * Получение элементов списка в виде массива
     * (вызывает {@link #toArrayInner(com.company.MyListNode, java.lang.Object...)})
     *
     * @param <T>
     * @param head Голова списка
     * @return Массив со значениями, которые содержатся в списке
     */
    public static <T> T[] toArray(MyListNode<T> head) {
        return toArrayInner(head);
    }

    public static <T> T[] toArray(MyDoublyLinkedListNode<T> head) {
        return toArrayInner(head);
    }

    /**
     * Функциональный интерфейс для перебора элементов списка
     *
     * @param <T>
     */
    public interface NodeAction<T> {
        public void exec(MyListNode<T> node);
    }

    /**
     * Последовательный проход по всем элементам списка
     * (вместо нового интерфейса NodeAction<T> можно было использовать
     * стандартный java.util.function.Consumer<MyListNode<T>>)
     *
     * @param <T>
     * @param head   Голова списка
     * @param action объект с Callback-функцией exec(MyListNode<T>),
     *               которая будет вызвана для каждого элемента списка
     */
    public static <T> void forEach(MyListNode<T> head, NodeAction<T> action) {
        for (MyListNode<T> curr = head; curr != null; curr = curr.getNext()) {
            action.exec(curr);
        }
    }

    /**
     * Создание копии списка
     * (сами значения не клонируются, только узлы MyListNode)
     *
     * @param <T>
     * @param head Голова списка
     * @return Первый элемент созданной копии списка
     */
    public static <T> MyListNode<T> clone(MyListNode<T> head) {
        MyListNode<T>
                clonedHead = null,
                clonedTail = null;
        for (MyListNode<T> node = head; node.getNext() != null; node = node.getNext()) {
            if (clonedHead == null) {
                clonedTail = clonedHead = new MyListNode<>(node.getValue());
            } else {
                MyListNode<T> newNode = new MyListNode<>(node.getValue());
                clonedTail.setNext(newNode);
                clonedTail = newNode;
            }
        }
        return clonedHead;
    }

    /**
     * Нахождение последнего элемента списка
     * ("тяжелая" операция - необходимо пройти весь список для получения последнего элемента)
     *
     * @param <T>
     * @param head Голова списка
     * @return Последний элемент списка
     */
    public static <T> MyListNode<T> getTail(MyListNode<T> head) {
        MyListNode<T> tail = head;
        for (MyListNode<T> node = head; node != null; node = node.getNext()) {
            tail = node;
        }
        return tail;
    }

    /**
     * Доступ к элементу по индексу
     * ("тяжелая" операция - необходимо перебрать элементы до нужного элемента)
     *
     * @param <T>
     * @param head  Голова списка
     * @param index Индекс элемента, который надо получить
     * @return Элемент по номеру index
     */
    public static <T> MyListNode<T> NodeByIndex(MyListNode<T> head, int index) {
        MyListNode<T> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.getNext();
        }
        return node;
    }

    public static <T> MyDoublyLinkedListNode<T> NodeByIndex(MyDoublyLinkedListNode<T> head, int index) {
        MyDoublyLinkedListNode<T> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * Поиск элемента по значению
     * ("тяжелая" операция - необходимо перебрать элементы до нужного элемента)
     *
     * @param <T>
     * @param head  Голова списка
     * @param value Значение элемента, который необходимо найти
     * @return Элемент со значением value
     */
    private static <T> MyListNode<T> getNodeByValue(MyListNode<T> head, T value) {
        for (MyListNode<T> node = head; node != null; node = node.getNext()) {
            if (node.getValue().equals(value)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Поиск элемента по значению
     * ("тяжелая" операция - необходимо перебрать элементы до нужного элемента)
     *
     * @param <T>
     * @param head  Голова списка
     * @param value Значение элемента, который необходимо найти
     * @return Предыдущий элемент перед элементом со значением value
     */
    private static <T> MyListNode<T> getPrevNodeByValue(MyListNode<T> head, T value) {
        if (head == null || head.getValue().equals(value)) {
            return null;
        }

        for (MyListNode<T> node = head; node.getNext() != null; node = node.getNext()) {
            if (node.getNext().getValue().equals(value)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Вставка элемента после данного
     *
     * @param <T>
     * @param node  Элемент
     * @param value Добавляемое значение
     * @return Добавленный элемент (узел) со значением value
     */
    public static <T> MyListNode<T> insertAfter(MyListNode<T> node, T value) {
        MyListNode<T> next = (node == null) ? null : node.getNext();
        MyListNode<T> newNode = new MyListNode<>(value, next);
        if (node != null) {
            node.setNext(newNode);
        }
        return newNode;
    }

    /**
     * Добавление элемента в начало списка
     * (меняет ссылку на первый элемент списка в контейнере)
     *
     * @param <T>
     * @param container Контайнер, содержащий ссылку на первый элемент списка
     * @param value     Добавляемое значение
     * @return Добавленный элемент (узел) со значением value
     */
    public static <T> MyListNode<T> addFirst(HeadContainer<T> container, T value) {
        container.setHead(new MyListNode<>(value, container.getHead()));
        return container.getHead();
    }


    /**
     * Добавление элемента в конец списка
     * (может меняет ссылку на первый элемент списка в контейнере;
     * "тяжелая" операция, т.к. надо найти последний элемент списка)
     *
     * @param <T>
     * @param container Контайнер, содержащий ссылку на первый элемент списка
     * @param value
     * @return Добавленный элемент (узел) со значением value
     */
    public static <T> MyListNode<T> addLast(HeadContainer<T> container, T value) {
        MyListNode<T> head = container.getHead();
        if (head == null) {
            head = new MyListNode<>(value);
            container.setHead(head);
            return head;
        } else {
            return insertAfter(getTail(container.getHead()), value);
        }
    }

    /**
     * Вставка элемента по индексу
     * (может меняет ссылку на первый элемент списка в контейнере;
     * "тяжелая" операция - надо найти нужный элемент списка)
     *
     * @param <T>
     * @param container Контайнер, содержащий ссылку на первый элемент списка
     * @param index     Индекс, по которому надо вставить новый элемент со значением value
     * @param value     Добавляемое значение
     * @return Добавленный элемент (узел) со значением value
     */
    public static <T> MyListNode<T> insert(HeadContainer<T> container, int index, T value) {
        if (index == 0) {
            return addFirst(container, value);
        } else {
            MyListNode<T> beforeNode = NodeByIndex(container.getHead(), index - 1);
            if (beforeNode == null) {
                return null;
            }
            return insertAfter(beforeNode, value);
        }
    }

    /**
     * Вставка элемента в упорядоченный список с сохранением порядка
     * (может меняет ссылку на первый элемент списка в контейнере;
     * "тяжелая" операция - надо найти нужный элемент списка)
     *
     * @param <T>
     * @param container Контайнер, содержащий ссылку на первый элемент списка
     * @param value     Добавляемое значение
     * @return Добавленный элемент (узел) со значением value
     */
    public static <T extends Comparable<T>> MyListNode<T> insertIntoOrdered(HeadContainer<T> container, T value) {
        if (container.getHead() == null || container.getHead().getValue().compareTo(value) >= 0) {
            return addFirst(container, value);
        } else {
            for (MyListNode<T> beforeNode = container.getHead(); ; beforeNode = beforeNode.getNext()) {
                if (beforeNode.getNext() == null || beforeNode.getNext().getValue().compareTo(value) >= 0) {
                    MyListNode<T> newNode = new MyListNode<>(value, beforeNode.getNext());
                    beforeNode.setNext(newNode);
                    return beforeNode;
                }
            }
        }
    }

    /**
     * Удаление элемента по индексу
     * (может меняет ссылку на первый элемент списка в контейнере;
     * "тяжелая" операция - надо найти нужный элемент списка)
     *
     * @param <T>
     * @param container Контайнер, содержащий ссылку на первый элемент списка
     * @param index     Индекс элемент, который надо удалить
     * @return Логическое значение, был ли удален элемент
     */
    public static <T> boolean removeAt(HeadContainer<T> container, int index) {
        if (container.getHead() == null) {
            return false;
        }

        if (index == 0) {
            // удаляем первый элемент
            container.setHead(container.getHead().getNext());
            return true;
        } else {
            // удалаем не первый элемент
            MyListNode<T> beforeNode = NodeByIndex(container.getHead(), index - 1);
            if (beforeNode != null && beforeNode.getNext() != null) {
                beforeNode.setNext(beforeNode.getNext().getNext());
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Удаление первого элемента по значению
     * (может меняет ссылку на первый элемент списка в контейнере;
     * "тяжелая" операция - надо найти нужный элемент списка)
     *
     * @param <T>
     * @param container Контайнер, содержащий ссылку на первый элемент списка
     * @param value     Значение, первый элемент с которым надо удалить
     * @return Логическое значение, был ли удален элемент
     */
    public static <T> boolean remove(HeadContainer<T> container, T value) {
        if (container.getHead() == null) {
            return false;
        }

        if (container.getHead().getValue().equals(value)) {
            // первый элемент содержит значение Value - удаляем
            container.setHead(container.getHead().getNext());
            return true;
        } else {
            // вероятно, какой-то другой (не первый) элемент содержит значение value
            MyListNode<T> beforeNode = getPrevNodeByValue(container.getHead(), value);
            if (beforeNode != null) {
                beforeNode.setNext(beforeNode.getNext().getNext());
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Удаление всех элементов со значением
     * (может меняет ссылку на первый элемент списка в контейнере;
     * "тяжелая" операция - надо просмотреть все элементы списка)
     *
     * @param <T>
     * @param container Контайнер, содержащий ссылку на первый элемент списка
     * @param value     Значение, элементы с которым надо удалить
     * @return Кол-во удаленных элементов
     */
    public static <T> int removeAll(HeadContainer<T> container, T value) {
        if (container.getHead() == null) {
            return 0;
        }

        int deletedCount = 0;

        while (container.getHead() != null && container.getHead().getValue().equals(value)) {
            container.setHead(container.getHead().getNext());
            deletedCount++;
        }

        MyListNode<T> beforeNode = getPrevNodeByValue(container.getHead(), value);
        while (beforeNode != null) {
            beforeNode.setNext(beforeNode.getNext().getNext());
            beforeNode = getPrevNodeByValue(beforeNode, value);
            deletedCount++;
        }

        return deletedCount;
    }

    /**
     * Удаление списка целиком (очиста списка)
     *
     * @param <T>
     * @param container Контайнер, содержащий ссылку на первый элемент списка
     */
    public static <T> void clear(HeadContainer<T> container) {
        container.setHead(null);
    }

    /**
     * Слияние двух упорядоченных списков в один упорядоченный
     * (исходные списки не модифицируются)
     *
     * @param <T>
     * @param head1 Первый элемент первого списка
     * @param head2 Первый элемент второго списка
     * @return Первый элемент созданного списка
     */
    public static <T extends Comparable<T>> MyListNode<T> mergeOrdered(MyListNode<T> head1, MyListNode<T> head2) {
        MyListNode<T> mergedList = null,
                mergedNode = null;

        MyListNode<T>
                node1 = head1,
                node2 = head2;
        while (node1 != null || node2 != null) {
            T value;
            if (node1 == null
                    || node2 != null && node1.getValue().compareTo(node2.getValue()) > 0) {
                value = node2.getValue();
                node2 = node2.getNext();
            } else {
                value = node1.getValue();
                node1 = node1.getNext();
            }

            if (mergedNode == null) {
                mergedList = mergedNode = new MyListNode<>(value);
            } else {
                MyListNode<T> newNode = new MyListNode<>(value);
                mergedNode.setNext(newNode);
                mergedNode = newNode;
            }
        }

        return mergedList;
    }

    /**
     * Вставка значения (узла) после данного элемента (узла) двусвязного списка
     * (если элемент будет последним, то tail не поменяется, надо отдельно обрабатывать)
     *
     * @param <T>
     * @param node  Элемент (узел) двусвязного списка, после которого необходимо добавить элемент
     * @param value Вставляемое значение
     * @return Добавленный элемент (узел) со значением value
     */
    public static <T> MyDoublyLinkedListNode<T> insertAfter(MyDoublyLinkedListNode<T> node, T value) {
        if (node == null) {
            return null;
        }

        MyDoublyLinkedListNode<T> temp = new MyDoublyLinkedListNode<>(value, node.getNext(), node);
        if (node.getNext() != null) {
            node.getNext().setPrev(temp);
        }
        node.setNext(temp);

        return node;
    }

    /**
     * Вставка значения (узла) перед данным элементом (узлом) двусвязного списка
     * (если элемент будет первым, то head не поменяется, надо отдельно обрабатывать)
     *
     * @param <T>
     * @param node  Элемент (узел) двусвязного списка, перед которым необходимо добавить элемент
     * @param value Вставляемое значение
     * @return Добавленный элемент (узел) со значением value
     */
    public static <T> MyDoublyLinkedListNode<T> insertBefore(MyDoublyLinkedListNode<T> node, T value) {
        if (node == null) {
            return null;
        }

        MyDoublyLinkedListNode<T> temp = new MyDoublyLinkedListNode<>(value, node, node.getPrev());
        if (node.getPrev() != null) {
            (node.getPrev()).setNext(temp);
        }
        node.setPrev(temp);
        (temp.getPrev()).setNext(temp);

        return node;
    }


    /**
     * Конвертер двусвязного списка в односвязный
     *
     * @param <T>
     */

    public static <T> MyListNode<T> converterMyDoublyLinkedListNodeToMyListNode(MyDoublyLinkedListNode<T> heat) {
        if (heat == null) {
            return null;
        }
        MyListNode<T>
                clonedHead = null,
                clonedTail = null;
        for (MyDoublyLinkedListNode<T> node = heat; node.getNext() != null; node = node.getNext()) {
            if (clonedHead == null) {
                clonedTail = clonedHead = new MyListNode<>(node.getValue());
            } else {
                MyListNode<T> newNode = new MyListNode<>(node.getValue());
                clonedTail.setNext(newNode);
                clonedTail = newNode;
            }
        }
        return clonedHead;


    }

    public static <T> void addItemEndList(MyDoublyLinkedListNode<T> tail, T value) {

        tail.setNext(new MyDoublyLinkedListNode<>(value, null, tail));

    }


}

