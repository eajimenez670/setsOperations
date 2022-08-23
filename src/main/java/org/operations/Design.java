package org.operations;

import javax.swing.*;

public class Design extends JFrame {
    private static JFrame frame;
    private static JTextField textField;
    private static JLabel label;

    Design() {
        setTitle("Propiedades de las relaciones entre conjuntos");
        InitializeLabel(50, 30, 460, 30);
        InitializeTextField(50, 60, 460, 30);
        add(label);
        add(textField);
        add(new Button(50, 120, 100, 30).getButton());
        setSize(550, 240);
        setLayout(null);
        setVisible(true);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static JTextField getTextField() {
        return textField;
    }

    public void InitializeTextField(int x, int y, int width, int height) {

        textField = new JTextField();
        textField.setBounds(x, y, width, height);
    }

    public void InitializeLabel(int x, int y, int width, int height) {
        label = new JLabel("Ingrese el conjunto a validar. (EJ: (1,3) | (2, 3) | ...)");
        label.setBounds(x, y, width, height);
    }
}
