package com.company;

import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private InputArray inputArray = new InputArray("window");
    private WorkWithFile workWithFile = new WorkWithFile();
    private MyDoublyLinkedListNode<Integer> head = new MyDoublyLinkedListNode<>(null, null, null);
    private MyDoublyLinkedListNode<Integer> tail = new MyDoublyLinkedListNode<>(null, null, null);

    private MyDoublyLinkedListNode<Integer> headConst;
    private MyDoublyLinkedListNode<Integer> tailConst;

    private JPanel Panel;
    private JPanel mainPanel;
    private JButton runButton;
    private JButton saveOutputButton;
    private JButton saveInputButton;
    private JButton loadButton;
    private JButton randomButton;
    private JTable inputTable;
    private JTable outputTable;
    private JTextField textField;

    public MainFrame() {
        this.setTitle("SearchPrimeNumber");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        JTableUtils.initJTableForArray(inputTable, 40, false, false, false, true);
        JTableUtils.initJTableForArray(outputTable, 40, false, false, false, false);

        inputTable.setRowHeight(25);
        outputTable.setRowHeight(25);

        JTableUtils.writeArrayToJTable(inputTable, new int[][]{
                {3, 4, 5, 6, 7, 9, 10, 11, 12, 13}

        });
        head = tail;
        headConst = head;
        tailConst = tail;
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("File(.txt)", "txt");
                fileOpen.addChoosableFileFilter(filter);
                int ret = fileOpen.showDialog(null, "Открыть файл");

                try {
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileOpen.getSelectedFile();
                        String nameFile = file.getPath();

                        List<List<String>> workingListString = inputArray.toTwoDimensionalListString(workWithFile.fileToString(nameFile));
                        List<List<Integer>> workingListInteger = new ArrayList<>(converterStringToInteger(workingListString));
                        int[] workingListIntegerArr = InputArray.separationPartArray(workingListInteger, 0);
                        tail = converterIntArrToDoubleLinkedList(workingListIntegerArr);

                        JTableUtils.writeArrayToJTable(inputTable, InputArray.converterDoubleLinkedListToIntArr(head));

                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                /*} catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);*/
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                }
            }

        });
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] arr = ArrayUtils.createRandomIntArray(inputTable.getColumnCount(), 0, 10000);
                    JTableUtils.writeArrayToJTable(inputTable, arr);

                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        saveInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooserSave = new JFileChooser();
                if (fileChooserSave.showSaveDialog(Panel) == JFileChooser.APPROVE_OPTION) {
                    try {
                        int[] arr = JTableUtils.readIntArrayFromJTable(inputTable);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }

                        workWithFile.saveArrayToFile(arr, file);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        saveOutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooserSave = new JFileChooser();
                if (fileChooserSave.showSaveDialog(Panel) == JFileChooser.APPROVE_OPTION) {
                    try {
                        int[] arr = JTableUtils.readIntArrayFromJTable(outputTable);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        workWithFile.saveArrayToFile(arr, file);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] workingArr = JTableUtils.readIntArrayFromJTable(inputTable);
                    tail = converterIntArrToDoubleLinkedList(workingArr);
                    int count = 0;

                    head = head.getNext();
                    for (MyDoublyLinkedListNode<Integer> curr = head; curr != null; curr = curr.getNext()) {
                        if (LogicWork.simple(head.getValue())) {
                            count++;
                            head = LogicWork.insertItemBeforeAndAfter(head, 0);
                        }
                        head = head.getNext();
                    }

                    if (count > 0) {
                        textField.setText("Количество простых чисел: " + count);
                    } else {
                        textField.setText("Простых чисел в списке нет");
                    }
                    tail = tailConst;
                    head = headConst;
                    JTableUtils.writeArrayToJTable(outputTable, InputArray.converterDoubleLinkedListToIntArr(head));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                }
                /*} catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);*/
            }
        });

    }

    public List<List<Integer>> converterStringToInteger(List<List<String>> list) {
        List<Integer> lineInteger = new ArrayList<>();
        List<List<Integer>> newList = new ArrayList<>();
        for (List<String> strings : list) {
            for (String string : strings) {
                lineInteger.add(inputArray.checkInt(string));
            }
            newList.add(lineInteger);
            lineInteger = new ArrayList<>();
        }
        return newList;
    }


    public MyDoublyLinkedListNode<Integer> converterIntArrToDoubleLinkedList(int[] ints) {
        for (int i = 0; i < ints.length; i++) {

            MyListUtils.addItemEndList(tail, ints[i]);
            tail = tail.getNext();
        }
        tail = tailConst;
        return tail;
    }


}


