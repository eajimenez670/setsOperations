package org.operations;

import javax.swing.*;

public class Design extends JFrame {
    private static JFrame frame;
    private static JTextField textField;

    Design() {
        InitializeTextField(50, 60, 460, 30);
        setTitle("Propiedades de las relaciones entre conjuntos");
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
}
