package com.zhengjianbin.generatecode.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengjianbin on 2019/9/12.
 */
public class MySQLConnect {

    private String uri;
    private String userName;
    private String pwd;
    private Map<String,String> classTypeMap = new HashMap<>();

    public MySQLConnect(String uri, String userName, String pwd) {
        this.uri = uri;
        this.userName = userName;
        this.pwd = pwd;
        classTypeMap.put("java.lang.Integer", "Integer");
        classTypeMap.put("java.lang.String", "String");
        classTypeMap.put("java.sql.Timestamp", "Date");
        classTypeMap.put("java.lang.Long", "Long");
        classTypeMap.put("java.lang.Boolean", "Integer");
        classTypeMap.put("java.lang.Double", "Double");
    }

    public Connection getConn() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(uri, userName, pwd);
    }

    /**
     * @author : zhengjianbin
     * @version: 1.0
     * @time : 2019/9/12 - 10:40 AM
     * @Param :
     * @function : 获取对应表对应的Java 字段
     */
    public Map<Object, Object> getField(String tableName) throws SQLException, ClassNotFoundException {
        List<Map<String, String>> fields = new ArrayList<>();
        Map result = new HashMap();
        Connection conn = getConn();
        String sql = "select * from " + tableName;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String primaryKeyColumns= "";
        String primaryKeyType = "";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            ResultSet pkSet = conn.getMetaData().getPrimaryKeys(null, null, tableName);

            while(pkSet.next()) {
                primaryKeyColumns = pkSet.getObject(4).toString();
            }
            ResultSet columnSet = conn.getMetaData().getColumns(null, "", tableName, "%");
            for(int i = 1; i <= columnSet.getMetaData().getColumnCount(); i++){
                Map<String, String> field = new HashMap<>();
                String columnName = data.getColumnName(i);
                String columnClassName = data.getColumnClassName(i);
                System.out.println("columnName:"+columnName+"columnClassName:"+columnClassName);
                if(columnName.equals(primaryKeyColumns)){
                    primaryKeyType = classTypeMap.get(columnClassName);
                }
                field.put("className", classTypeMap.get(columnClassName));
                field.put("fieldName", columnName);
                fields.add(field);
            }
        }catch (Exception e){

        }
        result.put("fields", fields);
        result.put("primaryKeyType",primaryKeyType);
        return result;
    }

}

