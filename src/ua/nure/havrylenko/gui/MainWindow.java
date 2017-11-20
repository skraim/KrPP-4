package ua.nure.havrylenko.gui;

import ua.nure.havrylenko.list.MyList;
import ua.nure.havrylenko.list.MyListImpl;
import ua.nure.havrylenko.transport.*;
import ua.nure.havrylenko.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ua.nure.havrylenko.Demo.*;

public class MainWindow {
    static JComboBox transport;

    /*public static void main(String[] args) {
        MainWindow gui = new MainWindow();
        gui.go();
    }*/

    public void go() {
        /*создание главного фрейма и панелей*/
        JFrame frame = new JFrame("Transport");
        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(130,100));
        leftPanel.setPreferredSize(new Dimension(270,100));
        /*создание кнопок*/
        JButton addB = new JButton("Add New");
        addB.setPreferredSize(new Dimension(100,40));
        addB.addActionListener(new AddClicked());
        JButton showAllB = new JButton("Show All");
        showAllB.setPreferredSize(new Dimension(100,40));
        showAllB.addActionListener(new ShowAllClicked());
        JButton deleteB = new JButton("Delete");
        deleteB.setPreferredSize(new Dimension(100,40));
        deleteB.addActionListener(new DeleteClicked());
        JButton editB = new JButton("Edit");
        editB.setPreferredSize(new Dimension(100,40));
        editB.addActionListener(new EditClicked());
        JButton exitB = new JButton("Exit");
        exitB.setPreferredSize(new Dimension(100,35));
        exitB.addActionListener(new ExitClicked());
        /*создание и заполнение ComboBox*/
        transport = new JComboBox();
        transport.setPreferredSize(new Dimension(250,35));
        fillingComboBox();
        /*размещение элементов*/
        frame.getContentPane().add(BorderLayout.EAST,rightPanel);
        frame.getContentPane().add(BorderLayout.WEST,leftPanel);
        frame.getContentPane().add(BorderLayout.SOUTH,bottomPanel);
        rightPanel.add(editB);
        rightPanel.add(deleteB);
        leftPanel.add(transport);
        leftPanel.add(addB);
        leftPanel.add(showAllB);
        bottomPanel.add(exitB);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,150);
        frame.setVisible(true);
        //makeSomething();
    }

    public void makeSomething() {
        trans = new MyListImpl();
        Bus b = new Bus("ПАЗ", "Евро-5");
        b.setHorsePower(300);
        b.setNumOfSits(55);
        Offroad off = new Offroad("Toyota", "Land Rover");
        off.setHorsePower(520);
        off.setNumOfSits(6);
        Sportcar sp = new Sportcar("Audi", "TT");
        sp.setHorsePower(440);
        sp.setNumOfSits(2);
        Atv at = new Atv("Hammer", "Tipper 200");
        at.setHorsePower(120);
        at.setNumOfSits(1);
        Bicycle cu = new Bicycle("Cube", "Rouge 3000");
        cu.setHorsePower(0);
        cu.setNumOfSits(1);
        trans.add(b);
        trans.add(off);
        trans.add(sp);
        trans.add(at);
        trans.add(cu);
        fillingComboBox();
    }

    private class AddClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AddWindow add = new AddWindow();
            add.showAddWindow();
        }
    }

    private class ShowAllClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ShowAllWindow showAll = new ShowAllWindow();
            showAll.showShowAll();
        }
    }

    private class EditClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            EditWindow edit = new EditWindow();
            edit.showEditWindow(transport.getSelectedIndex());
        }
    }

    private class DeleteClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            trans.remove((Transport)transport.getSelectedItem());
            fillingComboBox();
        }
    }

    public static void fillingComboBox() {
        transport.removeAllItems();
        for (Transport t : trans) {
            transport.addItem(t);
        }
    }

    private class ExitClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
