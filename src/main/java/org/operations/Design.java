package org.operations;

import javax.swing.*;

/*Clase destinada al diseño de la interfaz de la aplicación*/
public class Design extends JFrame {
    // Ventana principal de la aplicación
    private static JFrame frame;
    // Caja de texto principal
    private static JTextField textField;
    // Etiqueta que acompaña a la caja de texto principal
    private static JLabel label;
    // Etiqueta que acompaña al label de la caja de selección
    private static JLabel labelCB;
    // Caja de selección del tipo de dato
    private static JComboBox comboBox;

    Design() {
        // Se inicialazon los valores de las propiedades y se añaden a la ventana principal
        setTitle("Propiedades de las relaciones entre conjuntos");
        InitializeLabelCB(50, 20, 150, 20);
        InitializeComboBox(50, 40, 150, 20);
        InitializeLabel(50, 70, 460, 30);
        InitializeTextField(50, 95, 460, 30);
        add(labelCB);
        add(comboBox);
        add(label);
        add(textField);
        add(new Button(50, 140, 100, 30).getButton());
        setLayout(null);
        setSize(550, 260);
        setVisible(true);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static JTextField getTextField() {
        return textField;
    }

    public static JComboBox getComboBox() {
        return comboBox;
    }

    public void InitializeTextField(int x, int y, int width, int height) {

        textField = new JTextField();
        textField.setBounds(x, y, width, height);
    }

    public void InitializeComboBox(int x, int y, int width, int height) {
        String optionsCB[] = {"Letras", "Números"};
        comboBox = new JComboBox(optionsCB);
        comboBox.setBounds(x, y, width, height);
    }


    public void InitializeLabel(int x, int y, int width, int height) {
        label = new JLabel("Ingrese el conjunto a validar. (EJ: (1,3) | (2, 3) | ...)");
        label.setBounds(x, y, width, height);
    }

    public void InitializeLabelCB(int x, int y, int width, int height) {
        labelCB = new JLabel("Tipo de conjunto");
        labelCB.setBounds(x, y, width, height);
    }
}
