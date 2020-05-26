package database;

import database.settings.Settings;
import org.w3c.dom.Attr;
import resource.DBNode;
import resource.data.Row;
import resource.enums.AttributeType;
import resource.enums.ConstraintType;
import resource.implementation.Attribute;
import resource.implementation.AttributeConstraint;
import resource.implementation.Entity;
import resource.implementation.InformationResource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MSSQLrepository implements Repository{

    private Settings settings;
    private Connection connection;

    public MSSQLrepository(Settings settings) {
        this.settings = settings;
    }

    private void initConnection() throws SQLException, ClassNotFoundException{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        String ip = (String) settings.getParameter("mssql_ip");
        String database = (String) settings.getParameter("mssql_database");
        String username = (String) settings.getParameter("mssql_username");
        String password = (String) settings.getParameter("mssql_password");
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+"/"+database,username,password);
    }

    private void closeConnection(){
        try{
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connection = null;
        }
    }


    @Override
    public DBNode getSchema() {

        try{
            this.initConnection();

            DatabaseMetaData metaData = connection.getMetaData();
            InformationResource ir = new InformationResource("tim_8_bp2020");

            String tableType[] = {"TABLE"};
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, tableType);

            while (tables.next()){

                String tableName = tables.getString("TABLE_NAME");
                if (tableName.equals("trace_xe_action_map")){break;}
                Entity newTable = new Entity(tableName, ir);
                ir.addChild(newTable);
                System.out.println(tableName);
                //Koje atribute imaja ova tabela?

                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableName, null);

                while (columns.next()){

                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    int columnSize = Integer.parseInt(columns.getString("COLUMN_SIZE"));
                    Attribute attribute = new Attribute(columnName, newTable, AttributeType.valueOf(columnType.toUpperCase()), columnSize);
                    newTable.addChild(attribute);

                    try {
                        AttributeType test = AttributeType.valueOf(columnType.toUpperCase());
                    }catch (IllegalArgumentException e){
                        System.out.println(columnType);
                        AttributeConstraint domainValue = new AttributeConstraint(columnType,attribute,ConstraintType.DOMAIN_VALUE);
                        attribute.addChild(domainValue);
                    }

                    String defValue = columns.getString("COLUMN_DEF");
                    if (defValue != null){
                        AttributeConstraint defValCons = new AttributeConstraint(defValue, attribute, ConstraintType.DEFAULT_VALUE);
                        attribute.addChild(defValCons);
                    }

                    String isNullable = columns.getString("IS_NULLABLE");
                    if (isNullable.equals("NO")){
                        AttributeConstraint nullConstraint = new AttributeConstraint("not null", attribute, ConstraintType.NOT_NULL);
                        attribute.addChild(nullConstraint);
                    }

                    ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, newTable.getName());
                    while(primaryKeys.next()){
                            if (columnName.equals(primaryKeys.getString("COLUMN_NAME"))) {
                                AttributeConstraint pkConstraint = new AttributeConstraint("Primary Key", attribute, ConstraintType.PRIMARY_KEY);
                                attribute.addChild(pkConstraint);
                            }
                    }
                    ResultSet foreignKeys = metaData.getImportedKeys(connection.getCatalog(), null, newTable.getName());
                    while(foreignKeys.next()){


                        if (columnName.equals(foreignKeys.getString("FKCOLUMN_NAME"))) {
                            newTable.getRelacije().add(foreignKeys.getString("PKTABLE_NAME"));
                            //System.out.println("test" + foreignKeys.getString("PKTABLE_NAME"));

                            AttributeConstraint fkConstraint = new AttributeConstraint("Foreign Key", attribute, ConstraintType.FOREIGN_KEY);
                            attribute.addChild(fkConstraint);
                        }

                    }


                }



            }


            //TODO Ogranicenja nad kolonama? Relacije?

            return ir;
            // String isNullable = columns.getString("IS_NULLABLE");
            // ResultSet foreignKeys = metaData.getImportedKeys(connection.getCatalog(), null, table.getName());
            // ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, table.getName());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return null;
    }

    @Override
    public List<Row> get(String from) {

        List<Row> rows = new ArrayList<>();


        try{
            this.initConnection();

            String query = "SELECT * FROM " + from;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                Row row = new Row();
                row.setName(from);

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return rows;
    }

    @Override
    public List<Row> filterAndSort(String firstPart, String secondPart, String name) {

        List<Row> rows = new ArrayList<>();


        try{
            this.initConnection();

            String query = "SELECT " + firstPart + " FROM " + name;
            if (!(secondPart.equals(""))){
                query+= " ORDER BY " + secondPart;
            }
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                Row row = new Row();
                row.setName(name);

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return rows;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
