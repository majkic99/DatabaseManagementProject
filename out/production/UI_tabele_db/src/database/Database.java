package database;

import resource.DBNode;
import resource.data.Row;

import java.util.List;

public interface Database{

    DBNode loadResource();

    List<Row> readDataFromTable(String tableName);


    List<Row> filterAndSort(String firstPart, String secondPart, String name);
}
