package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataBaseCommonMethods {
    public static List<Map<String,String>> getDataBaseTableData(String query){
        Connection connection=null;
        Statement statement=null;
        ResultSet result=null;
        ResultSetMetaData resultSetMetaData=null;
        List<Map<String,String>> tableData=null;
        try {
            connection  = DriverManager.getConnection(FileReader.getPropertyValue("dbUrl"),FileReader.getPropertyValue("dbUserName"),FileReader.getPropertyValue("dBPassword"));
            statement=connection.createStatement();
            result= statement.executeQuery (query);
            resultSetMetaData=result.getMetaData();
           tableData=new ArrayList<>();

            while (result.next()){
                Map<String ,String>rowData=new LinkedHashMap<>();
                //we have started count from 1 becuase first columna name is id
                for (int i = 1 ; i < resultSetMetaData.getColumnCount(); i++) {
                    rowData.put(resultSetMetaData.getColumnName(i),result.getString(resultSetMetaData.getColumnName(i)));
                }
                tableData.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {//always follow this sequence of calling follwing statements 1# closeresultset 2#close statments and so on
            DataBaseCommonMethods.closeResultSet(result);
            DataBaseCommonMethods.closeStatement(statement);
            DataBaseCommonMethods.closeConnection(connection);
        }
        return tableData;
    }

    public static void closeResultSet(ResultSet result) {
        if(result !=null) {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeStatement(Statement statement) {
        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeConnection(Connection connection) {
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
