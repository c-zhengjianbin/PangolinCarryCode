package com.zhengjianbin.test;

import com.zhengjianbin.generatecode.kernel.CodeGenerator;
import com.zhengjianbin.generatecode.mybatis.MyBatisConfiguration;
import com.zhengjianbin.generatecode.mysql.MySQLConnect;
import com.zhengjianbin.generatecode.util.Constants;
import com.zhengjianbin.generatecode.util.StrUtils;
import freemarker.template.TemplateException;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by zhengjianbin on 2019/8/29.
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, TemplateException, InterruptedException, InvalidConfigurationException, XMLParserException {
        String uri = "jdbc:mysql://localhost:3306/risk-management";
        String userName = "root";
        String pwd = "123456789";
        String javaDtoPackage = "zhengjianbin.dto";
        String javaSqlPackage = "zhengjianbin.mapper";
        String mapperInterfacePacke = "zhengjianbin.mapper";
        List<String> tables = new ArrayList<>();
        tables.add("identification_record");
        String fileOutSrcDir = "/Users/zhengjianbin/Desktop/autocode/mybatisfile";
        MyBatisConfiguration myBatisConfiguration = new MyBatisConfiguration(uri, userName, pwd, javaDtoPackage,
                javaSqlPackage,mapperInterfacePacke, tables, fileOutSrcDir);
        myBatisConfiguration.generateMyBatisCode();


//        List<String> warnings = new ArrayList<String>();
//        boolean overwrite = true;
//        File configFile = new File("/Users/zhengjianbin/Desktop/automybatis/generator/generator.xml");
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config  = cp.parseConfiguration(configFile);
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        myBatisGenerator.generate(null);

        //生成Model、Service、Impl 类
        //generateJavaCode();
    }

    private static void generateJavaCode() throws ClassNotFoundException, SQLException, TemplateException, IOException {
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
        generateCode.generateModelFile(modelPara,
                Constants.TEMPLATE_MODEL_FILE_NAME,
                Constants.MODEL);

        //load service template parameter
        Map servicePara = new HashMap();
        String serviceClassName = className+"Service";
        servicePara.put("className", serviceClassName);
        servicePara.put("modelClassName", className);
        servicePara.put("classVariateName", StrUtils.javaClassNameConvertVariate(className));
        servicePara.put("servicePackageName", Constants.SERVICE_BEAN_PACKAGE_NAME);
        servicePara.put("modelPackageName", Constants.MODEL_BEAN_PACKAGE_NAME+"."+className);
        generateCode.generateModelFile(servicePara,
                Constants.TEMPLATE_SERVICE_FILE_NAME,
                Constants.SERVICE);

        //load serviceImpl template parameter
        Map serviceImplPara = new HashMap();
        serviceImplPara.put("className", className+"Impl");
        serviceImplPara.put("modelClassName", className);
        serviceImplPara.put("classVariateName", StrUtils.javaClassNameConvertVariate(className));
        serviceImplPara.put("serviceClassName", serviceClassName);
        serviceImplPara.put("serviceImplPackageName", Constants.SERVICE_IMPL_BEAN_PACKAGE_NAME);
        serviceImplPara.put("modelPackageName", Constants.MODEL_BEAN_PACKAGE_NAME+"."+className);
        serviceImplPara.put("servicePackageName", Constants.SERVICE_BEAN_PACKAGE_NAME +"."+serviceClassName);
        generateCode.generateModelFile(serviceImplPara,
                Constants.TEMPLATE_SERVICE_IMPL_FILE_NAME,
                Constants.SERVICE_IMPL);


    }



    public void generateMybatisCode() throws InvalidConfigurationException, InterruptedException, SQLException, IOException {
        //生成MyBatis 相关类
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        Configuration config = new Configuration();
        Context context = new Context(null);
        context.setId("DB2Tables");
        context.setTargetRuntime("MyBatis3");

        //数据库连接
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
        jdbcConnectionConfiguration.setConnectionURL("jdbc:mysql://localhost:3306/risk-management");
        jdbcConnectionConfiguration.setUserId("root");
        jdbcConnectionConfiguration.setPassword("123456789");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        //commentGenerator 配置
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
        //javaTypeResolverConfiguration 配置
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        javaTypeResolverConfiguration.addProperty("forceBigDecimals","false");
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
        //javamodel 相关配置
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        String javaTargetPackage = "com.elens.data.oauth.dao.client.mybatis.domain";
        String javaTargetProject = "/Users/zhengjianbin/Desktop/automybatis/generator/src";
        javaModelGeneratorConfiguration.setTargetPackage(javaTargetPackage);
        javaModelGeneratorConfiguration.setTargetProject(javaTargetProject);
        javaModelGeneratorConfiguration.addProperty("enableSubPackages","true");
        javaModelGeneratorConfiguration.addProperty("trimStrings","true");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
        //sqlMap相关配置
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        String sqlTargetPackage = "com.elens.data.oauth.dao.client.mybatis.mapper";
        String sqlTargetProject = "/Users/zhengjianbin/Desktop/automybatis/generator/src";
        sqlMapGeneratorConfiguration.setTargetPackage(sqlTargetPackage);
        sqlMapGeneratorConfiguration.setTargetProject(sqlTargetProject);
        sqlMapGeneratorConfiguration.addProperty("enableSubPackages","true");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
        //javaClient 相关配置
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        String javaClientTargetPackge = "com.elens.data.oauth.dao.client.mybatis.mapper";
        String javaClientProjectPackge = "/Users/zhengjianbin/Desktop/automybatis/generator/src";
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        javaClientGeneratorConfiguration.setTargetProject(javaClientProjectPackge);
        javaClientGeneratorConfiguration.setTargetPackage(javaClientTargetPackge);
        javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
        //table 配置
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("identification_record");
        tableConfiguration.setDomainObjectName("IdentificationRecord");
        tableConfiguration.setCountByExampleStatementEnabled(false);
        tableConfiguration.setUpdateByExampleStatementEnabled(false);
        tableConfiguration.setDeleteByExampleStatementEnabled(false);
        tableConfiguration.setSelectByExampleStatementEnabled(false);
        tableConfiguration.setSelectByExampleStatementEnabled(false);
        context.addTableConfiguration(tableConfiguration);
        //添加上下文
        config.addContext(context);
        /**
         * 注意：生成代码目录为：/Users/zhengjianbin/Desktop/automybatis/generator/src ，
         * 如果不存在src 目录，不会报错提示。需手动创建
         *
         */
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}
