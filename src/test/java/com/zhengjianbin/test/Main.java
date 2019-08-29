package com.zhengjianbin.test;

import com.zhengjianbin.generatecode.field.FieldGenerator;
import com.zhengjianbin.generatecode.kernel.CodeGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengjianbin on 2019/8/29.
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String templateDirPath = "/Users/zhengjianbin/IdeaProjects/PangolinCarryCode/src/main/java/com/zhengjianbin/generatecode/template/";
        String outFilePath = "/Users/zhengjianbin/Desktop/autocode";
        CodeGenerator generateCode = new CodeGenerator(templateDirPath, outFilePath);
        Map para = loadModelPara();
        try {
            generateCode.generateModelFile(para, "ModelDto.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, String>> testField() throws SQLException, ClassNotFoundException {
        String mysqlUrl = "jdbc:mysql://localhost:3306/risk-management";
        FieldGenerator fieldGenerator = new FieldGenerator(mysqlUrl, "root", "123456789");
        List<Map<String, String>> field = fieldGenerator.getField("admin_user");
        return field;
    }

    private static Map loadModelPara() throws SQLException, ClassNotFoundException {
        List<Map<String, String>> modelClassAndFieldList = testField();
        Map classValue = new HashMap();

        Map<String,String> modelClassAndField1 = new HashMap<>();
        modelClassAndField1.put("className","String");
        modelClassAndField1.put("fieldName","name");

        Map<String,String> modelClassAndField2 = new HashMap<>();
        modelClassAndField2.put("className","Integer");
        modelClassAndField2.put("fieldName","age");


        classValue.put("className", "TestModel");
        classValue.put("beanPackageName", "test.model");
        classValue.put("properties", modelClassAndFieldList);
        return classValue;
    }

}
