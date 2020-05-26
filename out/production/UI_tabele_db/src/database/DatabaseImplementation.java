package database;


import resource.DBNode;
import resource.data.Row;

import java.util.List;


public class DatabaseImplementation implements Database {

    private Repository repository;

    public DatabaseImplementation(Repository repository) {
        this.repository = repository;
    }


    @Override
    public DBNode loadResource() {
        return repository.getSchema();
    }

    @Override
    public List<Row> readDataFromTable(String tableName) {
        return repository.get(tableName);
    }

    @Override
    public List<Row> filterAndSort(String firstPart, String secondPart, String name) {
        return repository.filterAndSort(firstPart, secondPart, name);
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
