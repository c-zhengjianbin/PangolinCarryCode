package com.zhengjianbin.generatecode.field;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengjianbin on 2019/8/29.
 */
public class FieldGenerator {

    private String mysqlUrl;
    private String mysqlUser;
    private String mysqlPwd;
    private Map<String,String> classTypeMap = new HashMap<>();

    public FieldGenerator(String mysqlUrl, String mysqlUser, String mysqlPwd) {
        this.mysqlUrl = mysqlUrl;
        this.mysqlUser = mysqlUser;
        this.mysqlPwd = mysqlPwd;
        classTypeMap.put("java.lang.Integer", "Integer");
        classTypeMap.put("java.lang.String", "String");
        classTypeMap.put("java.sql.Timestamp", "Timestamp");
        classTypeMap.put("java.lang.Long", "Long");
    }

    public Connection getConn() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPwd);
    }

    public List<Map<String, String>> getField(String tableName) throws SQLException, ClassNotFoundException {
        List<Map<String, String>> fields = new ArrayList<>();

        Connection conn = getConn();
        String sql = "select * from " + tableName;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            ResultSet pkSet = conn.getMetaData().getPrimaryKeys(null, null, tableName);
            ResultSet columnSet = conn.getMetaData().getColumns(null, "", tableName, "%");
            for(int i = 1; i <= columnSet.getMetaData().getColumnCount(); i++){
                System.out.println(i);
                Map<String, String> field = new HashMap<>();
                String columnName = data.getColumnName(i);
                String columnClassName = data.getColumnClassName(i);
                field.put("className", classTypeMap.get(columnClassName));
                field.put("fieldName", columnName);
                fields.add(field);
            }
        }catch (Exception e){

        }
        return fields;
    }

}
