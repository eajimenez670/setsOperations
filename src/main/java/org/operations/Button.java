package org.operations;

import org.javatuples.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Button implements ActionListener {
    private JButton button;

    Button(int x, int y, int width, int height) {
        button = new JButton("Validar");
        button.setBounds(x, y, width, height);
        button.addActionListener(this::actionPerformed);
    }

    public JButton getButton() {
        return button;
    }

    public ArrayList<Pair<Integer, Integer>> isReflexive(ArrayList<Pair<Integer, Integer>> list) {
        var setsReflexives = new ArrayList<Pair<Integer, Integer>>();

        for (Pair<Integer, Integer> tuple : list) {
            if (tuple.getValue0() == tuple.getValue1()) {
                setsReflexives.add(tuple);
            }
        }

        return setsReflexives;
    }

    public String getTextMessage(ArrayList<Pair<Integer, Integer>> list) {
        String s = "";

        for (Pair<Integer, Integer> pair : list) {
            s += String.format("[%s, %s] ", pair.getValue0(), pair.getValue1());
        }

        return s;
    }

    public boolean validateText(String nodeTrim) {
        boolean isValid = true;

        if (!nodeTrim.startsWith("(") || !nodeTrim.endsWith(")")) {
            JOptionPane.showMessageDialog(Design.getFrame(), String.format("Debe ingresar los valores entre parentesis y separados por coma"));
            isValid = false;
        }

        if (nodeTrim.length() != 5) {
            JOptionPane.showMessageDialog(Design.getFrame(), String.format("El nodo %s es incorrecto, solo se admiten 2 valores", nodeTrim));
            isValid = false;
        }

        return isValid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String text = Design.getTextField().getText();
            var sets = text.split(" ");
            var listTuples = new ArrayList<Pair<Integer, Integer>>();
            for (String node : sets) {
                String nodeTrim = node.trim();
                if (!validateText(nodeTrim)) {
                    return;
                }

                int number1 = Integer.parseInt(nodeTrim.substring(1, 2));
                int number2 = Integer.parseInt(nodeTrim.substring(nodeTrim.length() - 2, nodeTrim.length() - 1));
                listTuples.add(new Pair<Integer, Integer>(number1, number2));
            }

            String listMessages = "";

            var arrayReflexives = isReflexive(listTuples);
            if (arrayReflexives.size() > 0) {
                listMessages += "El conjunto es reflexivo: " + getTextMessage(arrayReflexives);
            }


            if (listMessages == "") {
                JOptionPane.showMessageDialog(Design.getFrame(), "El conjunto no cuenta con ninguna propiedad");
            } else {
                JOptionPane.showMessageDialog(Design.getFrame(), listMessages);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Design.getFrame(), "No se admiten letras ni caracteres especiales");
        }
    }
}
