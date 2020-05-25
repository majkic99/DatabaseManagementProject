package gui.table;

import gui.MyToolbar;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private MyToolbar myToolbar;
    private int ID;

    public TableView(int ID) {
        super();
        scrollPane = new JScrollPane();
        myToolbar = new MyToolbar();
        table = new JTable();
        this.setLayout(new BorderLayout());
        scrollPane.setViewportView(table);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(myToolbar, BorderLayout.NORTH);
        this.ID = ID;

    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public int getID() {
        return ID;
    }
}
