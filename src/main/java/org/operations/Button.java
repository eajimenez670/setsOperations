package org.operations;

import org.javatuples.Pair;

import javax.swing.*;
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

    public String validateOperationsInt(ArrayList<Pair<Object, Object>> list) {
        var setsReflexive = new ArrayList<Pair<Object, Object>>();
        var setsSymmetric = new ArrayList<Pair<Object, Object>>();
        var setsTransitive = new ArrayList<Pair<Object, Object>>();

        for (int i = 0; i < list.size(); i++) {
            // Validate Reflexive
            if (list.get(i).getValue0() == list.get(i).getValue1()) {
                setsReflexive.add(list.get(i));
            }

            // Validate Symmetric
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    if (
                            (list.get(i).getValue0() == list.get(j).getValue1()) &&
                                    (list.get(i).getValue1() == list.get(j).getValue0())
                    ) {
                        if (
                                !setsReflexive.contains(list.get(i)) &&
                                        !setsSymmetric.contains(list.get(i)) &&
                                        !setsSymmetric.contains(list.get(j))
                        ) {
                            setsSymmetric.add(list.get(i));
                            setsSymmetric.add(list.get(j));
                        }
                    }
                }

                // Validate Transitive
                for (int y = 0; y < list.size(); y++) {
                    if (i != y && j != y) {
                        if (
                                (list.get(i).getValue0() == list.get(y).getValue0()) &&
                                        (list.get(i).getValue1() == list.get(j).getValue0()) &&
                                        (list.get(j).getValue1() == list.get(y).getValue1())
                        ) {
                            if (
                                    !setsReflexive.contains(list.get(i)) &&
                                            !setsSymmetric.contains(list.get(i)) &&
                                            !setsSymmetric.contains(list.get(j)) &&
                                            !setsTransitive.contains(list.get(i)) &&
                                            !setsTransitive.contains(list.get(j)) &&
                                            !setsTransitive.contains(list.get(y))
                            ) {
                                setsTransitive.add(list.get(i));
                                setsTransitive.add(list.get(j));
                                setsTransitive.add(list.get(y));
                            }
                        }
                    }
                }
            }
        }

        return generateMessage(setsReflexive, setsSymmetric, setsTransitive);
    }

    public String validateOperationsString(ArrayList<Pair<Object, Object>> list) {
        var setsReflexive = new ArrayList<Pair<Object, Object>>();
        var setsSymmetric = new ArrayList<Pair<Object, Object>>();
        var setsTransitive = new ArrayList<Pair<Object, Object>>();

        for (int i = 0; i < list.size(); i++) {
            // Validate Reflexive
            if (list.get(i).getValue0().equals(list.get(i).getValue1())) {
                setsReflexive.add(list.get(i));
            }

            // Validate Symmetric
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    if (
                            (list.get(i).getValue0().equals(list.get(j).getValue1())) &&
                                    (list.get(i).getValue1().equals(list.get(j).getValue0()))
                    ) {
                        if (
                                !setsReflexive.contains(list.get(i)) &&
                                        !setsSymmetric.contains(list.get(i)) &&
                                        !setsSymmetric.contains(list.get(j))
                        ) {
                            setsSymmetric.add(list.get(i));
                            setsSymmetric.add(list.get(j));
                        }
                    }
                }

                // Validate Transitive
                for (int y = 0; y < list.size(); y++) {
                    if (i != y && j != y) {
                        if (
                                (list.get(i).getValue0().equals(list.get(y).getValue0())) &&
                                        (list.get(i).getValue1().equals(list.get(j).getValue0())) &&
                                        (list.get(j).getValue1().equals(list.get(y).getValue1()))
                        ) {
                            if (
                                    !setsReflexive.contains(list.get(i)) &&
                                            !setsSymmetric.contains(list.get(i)) &&
                                            !setsSymmetric.contains(list.get(j)) &&
                                            !setsTransitive.contains(list.get(i)) &&
                                            !setsTransitive.contains(list.get(j)) &&
                                            !setsTransitive.contains(list.get(y))
                            ) {
                                setsTransitive.add(list.get(i));
                                setsTransitive.add(list.get(j));
                                setsTransitive.add(list.get(y));
                            }
                        }
                    }
                }
            }
        }

        return generateMessage(setsReflexive, setsSymmetric, setsTransitive);
    }

    public String generateMessage(
            ArrayList<Pair<Object, Object>> setsReflexive,
            ArrayList<Pair<Object, Object>> setsSymmetric,
            ArrayList<Pair<Object, Object>> setsTransitive
    ) {
        boolean isEquivalent = false;
        String message = "";

        if (setsReflexive.size() > 0) {
            message += "Las siguientes propiedades son reflexivas: " + getTextMessage(setsReflexive) + "\n";
        }

        if (setsSymmetric.size() > 0) {
            message += "Las siguientes propiedades son simetricas: " + getTextMessage(setsSymmetric) + "\n";
        }

        if (setsTransitive.size() > 0) {
            message += "Las siguientes propiedades son transitivas: " + getTextMessage(setsTransitive) + "\n";
        }

        if (setsReflexive.size() > 0 && setsSymmetric.size() > 0 && setsTransitive.size() > 0) {
            message += "El conjunto cuenta con una relación de equivalencia \n";
            isEquivalent = true;
        }

        if (setsSymmetric.size() > 0 && setsTransitive.size() > 0 && !isEquivalent) {
            message += "El conjunto cuenta con una relación de orden estricto \n";
        }

        return message;
    }

    public String getTextMessage(ArrayList<Pair<Object, Object>> list) {
        String s = "";

        for (Pair<Object, Object> pair : list) {
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

    public void execute(String text, int type) {
        var sets = text.split("\\|");
        var listTuples = new ArrayList<Pair<Object, Object>>();

        for (String node : sets) {
            String nodeTrim = node.trim();
            if (!validateText(nodeTrim)) {
                return;
            }

            var numbersSet = nodeTrim.substring(1, nodeTrim.length() - 1).split(",");
            if (type == 0) {
                String num1 = numbersSet[0].trim();
                String num2 = numbersSet[1].trim();

                if (!num1.matches("[a-zA-Z]+") || !num2.matches("[a-zA-Z]+")) {
                    throw new StringIndexOutOfBoundsException("No se admiten números");
                }

                listTuples.add(new Pair<Object, Object>(num1, num2));
            } else {
                int num1 = Integer.parseInt(numbersSet[0].trim());
                int num2 = Integer.parseInt(numbersSet[1].trim());
                listTuples.add(new Pair<Object, Object>(num1, num2));
            }
        }
        String message = "";

        if (type == 0) {
            message = validateOperationsString(listTuples);
        } else {
            message = validateOperationsInt(listTuples);
        }


        if (message == "") {
            JOptionPane.showMessageDialog(Design.getFrame(), "El conjunto no cuenta con ninguna propiedad");
        } else {
            JOptionPane.showMessageDialog(Design.getFrame(), message);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Extract form data
            String text = Design.getTextField().getText();
            var comboBox = Design.getComboBox().getSelectedIndex();
            execute(text, comboBox);
        }
        catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(Design.getFrame(), "No se admiten letras ni caracteres especiales");
        }
        catch (StringIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(Design.getFrame(), ex.getMessage());
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(Design.getFrame(), ex.getMessage());
        }
    }
}
