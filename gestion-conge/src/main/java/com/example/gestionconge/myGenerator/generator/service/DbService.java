
package generator.service;

import generator.dao.DbConnection;
import generator.utils.ObjectUtility;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbService {

    public static String formatString(String column){
        String[] splited = column.split("_");
        if(splited.length <2)
            return column;
        String res = splited[0];
        for(int i = 1; i < splited.length; i++){
            res += ObjectUtility.capitalize(splited[i]);
        }
        return res;
    }
    public static List<String> getAllTables(Connection con) throws Exception{
        List<String> lst = new ArrayList<>();
        DatabaseMetaData meta = (DatabaseMetaData) con.getMetaData();
        ResultSet rs = meta.getTables(null, null,  null, new String[] {
         "TABLE"
      });
        while (rs.next()) {
          lst.add(rs.getString(3));
        }
        return lst;
    }

    public static List<String> getAllViews(Connection con) throws SQLException {
        List<String> views = new ArrayList<>();
        DatabaseMetaData metaData = con.getMetaData();
        ResultSet rs = metaData.getTables(null, null, null, new String[]{"VIEW"});

        while (rs.next()) {
            String viewName = rs.getString(3);
            views.add(viewName);

        }

        return views;
    }

    public static String[] getAllTablesArrays(Connection con) throws Exception{
        List<String> lst = getAllTables(con);
        String[] array= new String[lst.size()];
        for(int i = 0; i < lst.size(); i++){
            array[i] = lst.get(i);
        }
        return array;
    }

    public static String[] getAllViewsArrays(Connection con) throws Exception{
        List<String> lst = getAllViews(con);
        String[] array= new String[lst.size()];
        for(int i = 0; i < lst.size(); i++){
            array[i] = lst.get(i);
        }
        return array;
    }

    public static String getPrimaryKeyInTable(Connection con, String tableName) {
        String query = "SELECT a.attname AS column_name FROM   pg_index i"+
            " JOIN   pg_attribute a ON a.attrelid = i.indrelid AND a.attnum = ANY(i.indkey)"+
            " WHERE i.indrelid = '"+tableName+"'::regclass AND i.indisprimary";

        try {

            if (con == null) {
                con = new DbConnection().connect();
            }
            try(Statement prstmt = con.createStatement()) {
                try(ResultSet rs = prstmt.executeQuery(query)) {
                    while (rs.next()) {
                        return rs.getString("column_name");
                    }
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }
    public static HashMap<String, String> getColumnNameAndType(Connection con, String tableName) throws Exception{
        HashMap<String, String> map = new LinkedHashMap<>();
        HashMap<String, String> map1 = new LinkedHashMap<>();


        String query = "SELECT * FROM "+tableName;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        boolean inc = true;
        for(int i = 1; i <= count ; i++){
            String key = rsmd.getColumnName(i);
            String value = rsmd.getColumnClassName(i);

            String primaryKeyName = getPrimaryKeyInTable(con, tableName);
            if (key.equalsIgnoreCase(primaryKeyName)) {
                key += "_pk";
                map.put(key, value);
                //deplacer le primary key au debut
                Map.Entry<String, String> entry = map.entrySet().iterator().next();
                map.remove(entry.getKey());
                map.put(entry.getKey(), entry.getValue());
                inc = false;
            }

            map.put(key, value);

        }
        if (inc) {
            int i = 0;
            for (Map.Entry<String, String> item : map.entrySet()) {
                if (i == 0) map1.put(item.getKey()+"_pk", item.getValue());
                else map1.put(item.getKey(), item.getValue());
                i++;
            }
            return map1;
        }
        return map;
    }

    /*POUR lES FOREIGN KEY */
    public static HashMap<String, String> getColumnsWithForeignKeys(Connection con, String tableName) throws SQLException {
        HashMap<String, String> map = new LinkedHashMap<>();

        String query = "SELECT conname, conrelid::regclass AS table_name, a.attname AS column_name, confrelid::regclass AS referenced_table " +
            "FROM pg_constraint c " +
            "JOIN pg_namespace n ON n.oid = c.connamespace " +
            "JOIN pg_attribute a ON a.attnum = ANY(c.conkey) AND a.attrelid = c.conrelid " +
            "WHERE contype = 'f' AND conrelid::regclass::text = '" + tableName + "'";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String  key = rs.getString("column_name");
            String value = rs.getString("referenced_table");
            map.put(key, value);
        }

        return map;
    }

    public static HashMap<String, String> getColumnNameAndTypeWithForeignKey(Connection con, String tableName) throws Exception {
        HashMap<String, String> map = new LinkedHashMap<String, String>();
        HashMap<String, String> allColumns = getColumnNameAndType(con, tableName);
        HashMap<String, String> listTablesForeignKey = getTableWhoUsePrimaryKeyForForeignKeys(con, tableName);
        for (Map.Entry<String, String> column : allColumns.entrySet()) {
            String name = column.getKey();
            String type = column.getValue();

            map.put(name, type);
        }
        for (Map.Entry<String, String> entry : listTablesForeignKey.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public static HashMap<String, String> getTableWhoUsePrimaryKeyForForeignKeys(Connection con , String tableName) throws SQLException {
        HashMap<String, String> map = new LinkedHashMap<String, String>();

        String sql = "SELECT conname AS constraint_name, conrelid::regclass AS table_name, a.attname AS column_name, confrelid::regclass AS foreign_table_name, af.attname AS foreign_column_name"+
            " FROM pg_constraint c"+
            " JOIN pg_attribute a ON a.attnum = ANY(c.conkey) AND a.attrelid = c.conrelid"+
            " JOIN pg_attribute af ON af.attnum = ANY(c.confkey) AND af.attrelid = c.confrelid"+
            " WHERE confrelid = '"+tableName+"'::regclass";

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String table = rs.getString("table_name");
            if (!tableName.equalsIgnoreCase(table)) {
                String type = tableName+":java.util.List";//POUR Jpa annotaion
                String variableName = table;
                map.put(variableName, type);
            }
        }

        return map;
    }


     public static HashMap<String, String> getColumnNameAndTypeWithForeignKeyObject(Connection con, String tableName) throws Exception {
        HashMap<String, String> allColumns = getColumnNameAndTypeWithForeignKey(con, tableName);
        HashMap<String, String> columnsWithForeignKey = getColumnsWithForeignKeys(con, tableName);
        for (Map.Entry<String, String> column : columnsWithForeignKey.entrySet()) {
            String name = column.getKey()+"_CLASS";
            String type = column.getValue()+"_CLASS";
            allColumns.put(name, type);
        }
        return allColumns;
    }


    public static void getTableConstraints(Connection con, String tableName) throws Exception{
//        HashMap<String, String> map = new HashMap<>();

        String query = "SELECT * FROM "+tableName;

        DatabaseMetaData meta = con.getMetaData();
        ResultSet tablesRs = meta.getTables(null, null, tableName, new String[]{"TABLE"});
        ResultSet primaryKeys = meta.getPrimaryKeys(null, null, tableName);

        ResultSetMetaData rsmd = primaryKeys.getMetaData();
        int count  = rsmd.getColumnCount();

        System.out.println("--------- PRIMARY KEY ----------------");
        while(primaryKeys.next()){
            for(int i = 1; i <= count; i++){
                System.out.println(" column NAME = " + rsmd.getColumnName(i) + " column VALUES = "+primaryKeys.getObject(i) + " column TYPE = " + rsmd.getColumnClassName(i));
            }
        }

        ResultSet foreignKeys = meta.getExportedKeys(null, null, tableName);
        ResultSetMetaData rsmd2 = foreignKeys.getMetaData();

        int count2  = rsmd2.getColumnCount();
        System.out.println("--------- FOREIGN KEY ----------------");
        System.out.println(foreignKeys.next());
//        while(foreignKeys.next()){
//            for(int i = 1; i <= count2; i++){
//                System.out.println(" column NAME = " + rsmd2.getColumnName(i) + " column VALUES = " + foreignKeys.getString(i) + " column TYPE = "+rsmd2.getColumnClassName(i));
//            }
//            System.out.println("-------------------------------------");
//        }
//        return map;
    }

}
