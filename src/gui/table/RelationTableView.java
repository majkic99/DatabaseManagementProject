package gui.table;

import javax.swing.*;

public class RelationTableView extends JScrollPane {

    private JTable table;


    public RelationTableView() {

        super();
        table = new JTable();
        setViewportView(table);

    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}
