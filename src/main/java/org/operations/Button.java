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

    public String validateOperations(ArrayList<Pair<Integer, Integer>> list) {
        var setsReflexive = new ArrayList<Pair<Integer, Integer>>();
        var setsSymmetric = new ArrayList<Pair<Integer, Integer>>();
        var setsTransitive = new ArrayList<Pair<Integer, Integer>>();

        for (int i = 0; i < list.size(); i++) {
            // Validate Reflexive
            if (list.get(i).getValue0() == list.get(i).getValue1()) {
                setsReflexive.add(list.get(i));
            }

            // Validate Symmetric
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    if (list.get(i).getValue0() != list.get(i).getValue1()) {
                        if ((list.get(i).getValue0() == list.get(j).getValue1()) && (list.get(i).getValue1() == list.get(j).getValue0())) {
                            if (!setsSymmetric.contains(list.get(i)) && !setsSymmetric.contains(list.get(j))) {
                                setsSymmetric.add(list.get(i));
                                setsSymmetric.add(list.get(j));
                            }
                        }
                    }
                }
            }
        }

        String message = "";

        if (setsReflexive.size() > 0) {
            message += "Las siguientes propiedades son reflexivas: " + getTextMessage(setsReflexive) + "\n";
        }

        if (setsSymmetric.size() > 0) {
            message += "Las siguientes propiedades son simetricas: " + getTextMessage(setsSymmetric) + "\n";
        }

        return message;
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

        if (!nodeTrim.startsWith("(") || !nodeTrim.endsWith(")") || !nodeTrim.contains(",")) {
            JOptionPane.showMessageDialog(Design.getFrame(), String.format("Debe ingresar 2 valores entre parentesis y separados por coma"));
            isValid = false;
        }

        return isValid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String text = Design.getTextField().getText();
            var sets = text.split("\\|");
            var listTuples = new ArrayList<Pair<Integer, Integer>>();
            for (String node : sets) {
                String nodeTrim = node.trim();
                if (!validateText(nodeTrim)) {
                    return;
                }

                var numbersSet = nodeTrim.substring(1, nodeTrim.length() - 1).split(",");
                int num1 = Integer.parseInt(numbersSet[0].trim());
                int num2 = Integer.parseInt(numbersSet[1].trim());
                listTuples.add(new Pair<Integer, Integer>(num1, num2));
            }

            var message = validateOperations(listTuples);

            if (message == "") {
                JOptionPane.showMessageDialog(Design.getFrame(), "El conjunto no cuenta con ninguna propiedad");
            } else {
                JOptionPane.showMessageDialog(Design.getFrame(), message);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Design.getFrame(), "No se admiten letras ni caracteres especiales");
        }
    }
}
