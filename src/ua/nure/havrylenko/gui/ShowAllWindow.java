package ua.nure.havrylenko.gui;

import ua.nure.havrylenko.comparators.ConcernComparator;
import ua.nure.havrylenko.comparators.ModelComparator;
import ua.nure.havrylenko.transport.Transport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static ua.nure.havrylenko.Demo.*;

public class ShowAllWindow {
    JFrame frame;
    JTextArea text;
    JRadioButton modelR;
    JRadioButton concernR;
    JRadioButton horsesR;
    Transport[] transArray;


    public void showShowAll() {
        transArray = trans.toArray();
        frame = new JFrame("Showing all");
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.WEST,leftPanel);
        frame.getContentPane().add(BorderLayout.EAST,rightPanel);
        frame.getContentPane().add(BorderLayout.SOUTH,bottomPanel);
        text = new JTextArea(10,25);
        JScrollPane scroller = new JScrollPane(text);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        leftPanel.add(scroller);
        rightPanel.setPreferredSize(new Dimension(100,150));
        JButton sortB = new JButton("Sort by:");
        sortB.setPreferredSize(new Dimension(90, 30));
        sortB.addActionListener(new SortClicked());
        modelR = new JRadioButton("model");
        modelR.setPreferredSize(new Dimension(90, 20));
        concernR = new JRadioButton("concern");
        concernR.setPreferredSize(new Dimension(90, 20));
        horsesR = new JRadioButton("HP");
        horsesR.setPreferredSize(new Dimension(90, 20));
        ButtonGroup bG = new ButtonGroup();
        bG.add(concernR);
        bG.add(modelR);
        bG.add(horsesR);
        rightPanel.add(sortB);
        rightPanel.add(concernR);
        rightPanel.add(modelR);
        rightPanel.add(horsesR);
        JButton cancelB = new JButton("Cancel");
        cancelB.setPreferredSize(new Dimension(100,30));
        cancelB.addActionListener(new CancelClicked());
        bottomPanel.add(cancelB);
        fillingArea();
        frame.setSize(400,200);
        frame.setVisible(true);
    }

    private void fillingArea() {
        text.setText("");
        for (Transport tr : transArray) {
            text.append(tr + "; HP = " + tr.getHorsePower() + "\n");
        }
    }

    class SortClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (horsesR.isSelected()) {
                Arrays.sort(transArray);
            }
            if (concernR.isSelected()) {
                ConcernComparator cc = new ConcernComparator();
                Arrays.sort(transArray,cc);
            }
            if (modelR.isSelected()) {
                ModelComparator mc = new ModelComparator();
                Arrays.sort(transArray,mc);
            }
            fillingArea();
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
