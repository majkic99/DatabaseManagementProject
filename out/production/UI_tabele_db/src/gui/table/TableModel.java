package gui.table;


import resource.data.Row;
import resource.implementation.Attribute;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;
import java.util.Vector;


public class TableModel extends DefaultTableModel {

    private List<Row> rows;


    private void updateModel(){

        int columnCount = rows.get(1).getFields().keySet().size();

        Vector columnVector = DefaultTableModel.convertToVector(rows.get(1).getFields().keySet().toArray());
        Vector dataVector = new Vector(columnCount);

        for (Row row : rows) {
            dataVector.add(DefaultTableModel.convertToVector(row.getFields().values().toArray()));
        }
        setDataVector(dataVector, columnVector);
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
        updateModel();
    }

    public List<Row> getRows() {
        return rows;
    }
}
