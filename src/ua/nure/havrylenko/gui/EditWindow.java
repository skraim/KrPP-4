package ua.nure.havrylenko.gui;

import ua.nure.havrylenko.transport.Car;
import ua.nure.havrylenko.transport.Transport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ua.nure.havrylenko.Demo.*;

public class EditWindow {
    JTextField modelEditor;
    Transport editableT;
    JTextField concernEditor;
    JTextField yearEditor;
    JTextField hPowerEditor;
    JFrame frame;
    JButton editB;
    int index;

    public void showEditWindow(int i) {
        index = i;
        editableT = (Transport)MainWindow.transport.getItemAt(i);
        frame = new JFrame("Editing");
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        editB = new JButton("Edit");
        editB.setPreferredSize(new Dimension(100,30));
        editB.addActionListener(new EditClicked());
        JButton cancelB = new JButton("Cancel");
        cancelB.setPreferredSize(new Dimension(100,30));
        cancelB.addActionListener(new CancelClicked());
        JLabel model = new JLabel("Model*");
        modelEditor = new JTextField();
        model.setPreferredSize(new Dimension(130,35));
        modelEditor.setPreferredSize(new Dimension(130,35));
        modelEditor.setText(editableT.getModel());
        JLabel concern = new JLabel("Concern*");
        concernEditor = new JTextField();
        concern.setPreferredSize(new Dimension(130,35));
        concernEditor.setPreferredSize(new Dimension(130,35));
        concernEditor.setText(editableT.getConcern());
        JLabel year = new JLabel("Year");
        yearEditor = new JTextField();
        year.setPreferredSize(new Dimension(130,35));
        yearEditor.setPreferredSize(new Dimension(130,35));
        yearEditor.setText(editableT.getYear()+"");
        JLabel hPower = new JLabel("Horse Power");
        hPowerEditor = new JTextField();
        hPower.setPreferredSize(new Dimension(130,35));
        hPowerEditor.setPreferredSize(new Dimension(130,35));
        hPowerEditor.setText(editableT.getHorsePower()+"");
        leftPanel.setPreferredSize(new Dimension(140,350));
        rightPanel.setPreferredSize(new Dimension(140,350));
        leftPanel.add(model);
        leftPanel.add(concern);
        leftPanel.add(year);
        leftPanel.add(hPower);
        rightPanel.add(modelEditor);
        rightPanel.add(concernEditor);
        rightPanel.add(yearEditor);
        rightPanel.add(hPowerEditor);
        bottomPanel.add(editB);
        bottomPanel.add(cancelB);
        frame.getContentPane().add(BorderLayout.WEST,leftPanel);
        frame.getContentPane().add(BorderLayout.EAST,rightPanel);
        frame.getContentPane().add(BorderLayout.SOUTH,bottomPanel);
        frame.setSize(285,230);
        frame.setVisible(true);
    }

    class EditClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Car car;
            if (!concernEditor.getText().equals("") && !modelEditor.getText().equals("")) {
                car = new Car(concernEditor.getText(), modelEditor.getText());
                if (!hPowerEditor.getText().equals("")) {
                    try {
                        car.setHorsePower(Integer.parseInt(hPowerEditor.getText()));
                    } catch (Exception ex) {
                        System.out.println("Only integer is available!");
                        return;
                    }
                }
                if (!yearEditor.getText().equals("")) {
                    try {
                        car.setYear(Integer.parseInt(yearEditor.getText()));
                    } catch (Exception ex) {
                        System.out.println("Only integer is available!");
                        return;
                    }
                }
                trans.remove(editableT);
                trans.add(car);
                editB.setText("Edited");
            } else {
                System.out.println("Fields marked \"*\" must be initialize!");
            }
            MainWindow.fillingComboBox();
        }
    }

    class CancelClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            frame.dispose();
        }
    }
}
