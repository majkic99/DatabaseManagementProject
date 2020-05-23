package gui.table;

import gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private JToolBar toolbar;
    private int ID;

    public TableView(int ID) {
        super();
        scrollPane = new JScrollPane();
        toolbar = new JToolBar();
        toolbar.add(new JLabel("Ovaj toolbar postoji :O "));
        toolbar.add(new JLabel("Ali gde je tabela dole????????"));
        table = new JTable();
        this.setLayout(new BorderLayout());
        scrollPane.add(table);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(toolbar, BorderLayout.NORTH);
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
