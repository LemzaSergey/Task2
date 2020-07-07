package com.company;
//18.	В двусвязном списке целых чисел перед и после каждого простого числа
// вставить новые элементы со значением 0.


public class Main {

    public static void main(String[] args) {

        if (args.length > 0) {
            switch (args[0]) {
                case "-window":
                    new MainFrame();
                    break;
                default:
                    System.exit(-13);
            }
        } else {
            System.exit(-15);
        }
    }

}
