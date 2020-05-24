package gui.table;

import gui.MainFrame;
import gui.Toolbar;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private Toolbar toolbar;
    private int ID;

    public TableView(int ID) {
        super();
        scrollPane = new JScrollPane();
        toolbar = new Toolbar();
        table = new JTable();
        this.setLayout(new BorderLayout());
        scrollPane.setViewportView(table);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(toolbar, BorderLayout.NORTH);
        this.ID = ID;

    }

    private void setupToolbar() {

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
