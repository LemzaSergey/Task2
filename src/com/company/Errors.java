package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Errors {
    private JFrame jFrame;
    private String version;


    public Errors(String version) {
        this.version = version;
    }

    public String checkIntError(String line) {
        while (!checkString(line)) {
            if ("window".equals(version)) {
                line = panelError("Ошибка! Введите коректное значение элемента.", line);
            }
        }
        return line;
    }

    public boolean checkString(String string) {
        if (string == null) {
            return false;
        }
        return string.matches("^\\d+$");
    }


    public String panelError(String message, String errorLine) {

        final String[] text = new String[1];

        JDialog dialog = new JDialog(jFrame, "Errors", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setBounds(jFrame.getX(), jFrame.getY(), 450, 300);

        JPanel panelError = new JPanel();
        panelError.setLayout(new BoxLayout(panelError, BoxLayout.Y_AXIS));

        panelError.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel label = new JLabel(message);
        panelError.add(label);

        panelError.add(Box.createRigidArea(new Dimension(110, 10)));

        JTextArea textArea = new JTextArea(1, 20);
        textArea.setText(errorLine);
        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setMaximumSize(new Dimension(400, 30));
        panelError.add(scrollPane, BorderLayout.CENTER);

        panelError.add(Box.createRigidArea(new Dimension(0, 100)));

        JButton inputButton = new JButton("Ок");
        inputButton.setMaximumSize(new Dimension(70, 30));
        inputButton.addActionListener(e -> {
            text[0] = textArea.getText();
            dialog.dispose();
        });
        panelError.add(inputButton);

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        dialog.add(panelError);
        dialog.setVisible(true);
        return text[0];
    }

    public static boolean workingListIntegerRedundant(List<List<Integer>> list) {
        if (list.size() >= 2) {
            JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        return false;
    }

}
