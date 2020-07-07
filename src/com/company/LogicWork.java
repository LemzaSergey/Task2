package com.company;


public class LogicWork {

    public static <T> MyDoublyLinkedListNode<T> insertItemBeforeAndAfter(MyDoublyLinkedListNode<T> node, T value) {
        //insertBefore перед
        MyDoublyLinkedListNode<T> temp = new MyDoublyLinkedListNode<>(value, node, node.getPrev());
        if (node.getPrev() != null) {
            (node.getPrev()).setNext(temp);
        }
        node.setPrev(temp);

        //insertAfter после
        if (node.getNext() == null) {
            MyDoublyLinkedListNode<T> temp2 = new MyDoublyLinkedListNode<>(value, null, node);
            node.setNext(temp2);

        } else {
            MyDoublyLinkedListNode<T> temp2 = new MyDoublyLinkedListNode<>(value, node.getNext(), node);

            if (node.getNext() != null) {
                (node.getNext()).setPrev(temp2);
            }
            node.setNext(temp2);
        }
        return node;
    }


    public static boolean simple(int a) {
        if (a <= 1) {
            return false;
        }
        if ((a == 2) || (a == 3) || (a == 5) || (a == 7)) {
            return true;
        }
        int p = 0;//переменная для определения результата
        if ((a % 2 == 0) || (a % 10 == 5)) {//исключаем числа, которые заканчиваются на 5 и четные
            return false;
        } else {
            for (int j = 3; j <= Math.sqrt(a); j += 2) {//делим на все нечетные числа до корня из i
                if (a % j == 0) {// если хотя бы на одно число делится, то остановка цикла, переход к следующему числу
                    p += 1;
                    break;
                }
            }
        }
        if (p > 0) {//если p = 0, то возвращаем true, число простое
            return false;
        } else return true;

    }

}
