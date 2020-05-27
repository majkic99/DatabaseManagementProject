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

    @Override
    public void insert(String values, String name) {
        repository.insert(values, name);
    }

    @Override
    public void delete(String upit, String name) {
        repository.delete(upit,name);
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
