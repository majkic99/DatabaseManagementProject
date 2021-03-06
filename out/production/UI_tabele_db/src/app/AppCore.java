package app;

import database.Database;
import database.DatabaseImplementation;
import database.MSSQLrepository;
import database.settings.Settings;
import database.settings.SettingsImplementation;
import gui.table.TableModel;
import observer.Notification;
import observer.enums.NotificationCode;
import observer.implementation.PublisherImplementation;
import resource.implementation.Entity;
import resource.implementation.InformationResource;
import utils.Constants;

public class AppCore extends PublisherImplementation {

    private Database database;
    private Settings settings;
    private TableModel mainTableModel;
    private TableModel relationTableModel;
    private InformationResource irRoot;
    private Entity currentEntity;

    public String getCurrentRelationName() {
        return currentRelationName;
    }

    public void setCurrentRelationName(String currentRelationName) {
        this.currentRelationName = currentRelationName;
    }

    private String currentRelationName;


    public InformationResource getIrRoot() {
        return irRoot;
    }

    public void setIrRoot(InformationResource irRoot) {
        this.irRoot = irRoot;
    }
    public AppCore() {
        this.settings = initSettings();
        this.database = new DatabaseImplementation(new MSSQLrepository(this.settings));
        mainTableModel = new TableModel();
        relationTableModel = new TableModel();
    }

    private Settings initSettings() {
        Settings settingsImplementation = new SettingsImplementation();
        settingsImplementation.addParameter("mssql_ip", Constants.MSSQL_IP);
        settingsImplementation.addParameter("mssql_database", Constants.MSSQL_DATABASE);
        settingsImplementation.addParameter("mssql_username", Constants.MSSQL_USERNAME);
        settingsImplementation.addParameter("mssql_password", Constants.MSSQL_PASSWORD);
        return settingsImplementation;
    }


    public void loadResource(){
        irRoot = (InformationResource) this.database.loadResource();
        this.notifySubscribers(new Notification(NotificationCode.RESOURCE_LOADED,irRoot));
    }

    public void readDataFromTable(String fromTable){

        mainTableModel.setRows(this.database.readDataFromTable(fromTable));

        //Zasto ova linija moze da ostane zakomentarisana?
        //this.notifySubscribers(new Notification(NotificationCode.DATA_UPDATED, this.getTableModel()));
    }

    public void readDataFromTableRelation(String fromTableRelation) {
        relationTableModel.setRows(this.database.readDataFromTable(fromTableRelation));
        currentRelationName = fromTableRelation;
    }

    public TableModel getTableModelFromTable(String fromTable){
        TableModel tableMod = new TableModel();
        tableMod.setRows(this.database.readDataFromTable(fromTable));
        return tableMod;

    }

    public void filterAndSort(String firstPart, String secondPart){
        mainTableModel.setRows(this.database.filterAndSort(firstPart, secondPart, this.currentEntity.getName()));
    }

    public TableModel getMainTableModel() {
        return mainTableModel;
    }

    public void setMainTableModel(TableModel mainTableModel) {
        this.mainTableModel = mainTableModel;
    }

    public TableModel getRelationTableModel() {
        return relationTableModel;
    }

    public void setRelationTableModel(TableModel relationTableModel) {
        this.relationTableModel = relationTableModel;
    }

    public Entity getCurrentEntity() {
        return currentEntity;
    }

    public void setCurrentEntity(Entity currentEntity) {
        this.currentEntity = currentEntity;
    }

    public void insert(String values) {
        this.database.insert(values, this.currentEntity.getName());
        mainTableModel.setRows(this.database.readDataFromTable(this.currentEntity.getName()));

    }

    public void deleteFromTable(String upit) {
        this.database.delete(upit, this.currentEntity.getName());
        mainTableModel.setRows(this.database.readDataFromTable(this.currentEntity.getName()));

    }

    public void updateInTable(String values, String upit) {
        this.database.update(values, upit, this.currentEntity.getName());
        mainTableModel.setRows(this.database.readDataFromTable(this.currentEntity.getName()));

    }

    public void search(String upit) {
        mainTableModel.setRows(this.database.searchDataFromTable(this.currentEntity.getName(),upit));
    }

    public void updateRelationTable(String upit, String currentRelationName) {
        relationTableModel.setRows(this.database.updateRelationTable(upit,currentRelationName));
    }

    public void report(String operation, String col, String groupBy) {
        mainTableModel.setRows(this.database.report(operation, col, groupBy));
    }
}
