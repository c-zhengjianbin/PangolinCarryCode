package com.zhengjianbin.test;

import com.zhengjianbin.generatecode.kernel.CodeGenerator;
import com.zhengjianbin.generatecode.mysql.MySQLConnect;
import com.zhengjianbin.generatecode.util.Constants;
import com.zhengjianbin.generatecode.util.StrUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengjianbin on 2019/8/29.
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, TemplateException {

        String templateDirPath = "/Users/zhengjianbin/IdeaProjects/PangolinCarryCode/src/main/java/com/zhengjianbin/generatecode/template/";
        String outCodeFilePath = "/Users/zhengjianbin/IdeaProjects/PangolinCarryCode/src/main/java/com/zhengjianbin/generatecode/testfile";
        String mysqlUri = "jdbc:mysql://localhost:3306/risk-management";
        String mysqlUserName = "root";
        String mysqlPwd = "123456789";
        //connect mysql
        MySQLConnect mySQLConnect = new MySQLConnect(mysqlUri, mysqlUserName, mysqlPwd);
        //generate code
        CodeGenerator generateCode = new CodeGenerator(templateDirPath, outCodeFilePath);

        generateCode(mySQLConnect, generateCode);
    }

    private static void generateCode(MySQLConnect mySQLConnect, CodeGenerator generateCode) throws SQLException, ClassNotFoundException, IOException, TemplateException {
        String tableName = "admin_user";
        String className = StrUtils.tableConvertJavaClassName(tableName);
        //table field convert java field
        List<Map<String, String>> dbTableField = mySQLConnect.getField(tableName);

        //load model template file parameter
        Map modelPara = new HashMap();
        modelPara.put("beanPackageName", Constants.MODEL_BEAN_PACKAGE_NAME);
        modelPara.put("className", className);
        modelPara.put("properties", dbTableField);
        //generate template code
        generateCode.generateModelFile(modelPara, Constants.TEMPLATE_MODEL_FILE_NAME,
                Constants.MODEL, Constants.MODEL);

        //load service template parameter
        Map servicePara = new HashMap();
        servicePara.put("className", className+"Service");
        servicePara.put("modelClassName", className);
        servicePara.put("classVariateName", StrUtils.javaClassNameConvertVariate(className));
        servicePara.put("servicePackageName", Constants.SERVICE_BEAN_PACKAGE_NAME);
        servicePara.put("modelPackageName", Constants.MODEL_BEAN_PACKAGE_NAME+"."+className);
        generateCode.generateModelFile(servicePara, Constants.TEMPLATE_SERVICE_FILE_NAME,
                Constants.SERVICE, Constants.SERVICE);
    }

}
