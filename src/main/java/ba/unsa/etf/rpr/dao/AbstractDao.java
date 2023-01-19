package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.sql.*;
import java.util.*;

/**
 * AbstractDao represents an abstract class that implements shared methods by other DaoSqlImpl classes,
 * this class is later inherited in other DaoSqlImpl classes
 * @param <Type> is a type that implements the Idable interface
 */

public abstract class AbstractDao<Type extends Idable> implements Dao<Type> {

    private static Connection connection = null;
    private String tableName;

    /**
     * Public Constructor in which the connection to the Data Base is created
     * @param tableName is a String that represents a concrete table name used in the data base
     */
    public AbstractDao(String tableName){
        this.tableName = tableName;
        createConnection();
    }

    /**
     * Static method which creates the connection to the Data Base
     */
    private static void createConnection(){
        if(AbstractDao.connection==null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("application.properties.sample").openStream());
                String url = p.getProperty("db.connection_string");
                String username = p.getProperty("db.username");
                String password = p.getProperty("db.password");
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(){
                    @Override
                    public void run(){
                        try{
                            connection.close();
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    /**
     * Static method that returns the connection to the Data Base
     * @return Connection to Data Base
     */
    public Connection getConnection(){
        return AbstractDao.connection;
    }


    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     */
    public abstract Type row2object(ResultSet rs) throws HospitalException;

    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> object2row(Type object);

    /**
     * Method that returns an entity from the Data Base which has the
     * same id as the number that is being given as a parameter
     * @param id primary key for entity
     * @return a JavaBean object that represents the table name, aka table in the Data Base
     * @throws HospitalException
     */
    public Type getById(int id) throws HospitalException {
        return executeQueryUnique("SELECT * FROM "+this.tableName+" WHERE id = ?", new Object[]{id});
    }

    /**
     * Method that return all entities from the Data Base table which has the
     * same name as the parameter tableName
     * @return List of JavaBean objects
     * @throws HospitalException
     */
    public List<Type> getAll() throws HospitalException {
        return executeQuery("SELECT * FROM "+ tableName, null);
    }

    /**
     * Method that executes a custom query to the Data Base
     * @param query String that represents a custom query
     * @param params array of needed parameters for the query
     * @return List of JavaBean objects
     * @throws HospitalException
     */
    public List<Type> executeQuery(String query, Object[] params) throws HospitalException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<Type> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }

            return resultList;
        } catch (SQLException e) {
            throw new HospitalException(e.getMessage(), e);
        }
    }

    /**
     * Utility for query execution that always return single record
     * @param query - query that returns single record
     * @param params - list of params for sql query
     * @return Object
     */
    public Type executeQueryUnique(String query, Object[] params) throws HospitalException {
        List<Type> result = executeQuery(query, params);
        if (result != null && result.size() == 1){
            return result.get(0);
        }else{
            throw new HospitalException("Object does not exist");
        }
    }

    public void delete(int id) throws HospitalException {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    public Type add(Type item) throws HospitalException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        }catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }

    public Type update(Type item) throws HospitalException{
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    private String prepareUpdateParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
}

