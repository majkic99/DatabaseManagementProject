package database;

import resource.DBNode;
import resource.data.Row;

import java.util.List;

public interface Repository {

    DBNode getSchema();

    List<Row> get(String from);

    List<Row> filterAndSort(String firstPart, String secondPart, String name);

    void insert(String values, String name);

    void delete(String upit, String name);
}
