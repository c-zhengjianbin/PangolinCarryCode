package com.zhengjianbin.generatecode.mybatis;

import com.zhengjianbin.generatecode.exception.PangolinCarryException;
import com.zhengjianbin.generatecode.util.StrUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by zhengjianbin on 2019/9/14.
 */
public class MyBatisConfiguration {

    private String uri;
    private String userName;
    private String pwd;
    private String javaDtoPackage;
    private String mapperPackage;
    private String mapperInterfacePacke;
    private List<String> tableNames;
    private String fileOutSrcDir;

    public MyBatisConfiguration(String uri, String userName, String pwd,
                                String javaDtoPackage, String mapperPackage,
                                String mapperInterfacePacke, List<String> tableNames,
                                String fileOutSrcDir) {
        this.uri = uri;
        this.userName = userName;
        this.pwd = pwd;
        this.javaDtoPackage = javaDtoPackage;
        this.mapperPackage = mapperPackage;
        this.mapperInterfacePacke = mapperInterfacePacke;
        this.tableNames = tableNames;
        this.fileOutSrcDir = fileOutSrcDir;
    }

    private Context setContext() throws PangolinCarryException{
        if(CollectionUtils.isEmpty(tableNames)){
            throw new PangolinCarryException("Mybatis 配置 Context异常：tableConfigurationList为空！！！");
        }
        File srcFile = new File(fileOutSrcDir);
        if(!srcFile.exists()){
            srcFile.mkdirs();
        }
        Context context = new Context(null);
        context.setId("DB2Tables");
        context.setTargetRuntime("MyBatis3");

        //数据库连接
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
        jdbcConnectionConfiguration.setConnectionURL(uri);
        jdbcConnectionConfiguration.setUserId(userName);
        jdbcConnectionConfiguration.setPassword(pwd);
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
        javaModelGeneratorConfiguration.setTargetPackage(javaDtoPackage);
        javaModelGeneratorConfiguration.setTargetProject(fileOutSrcDir);
        javaModelGeneratorConfiguration.addProperty("enableSubPackages","true");
        javaModelGeneratorConfiguration.addProperty("trimStrings","true");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
        //sqlMap相关配置
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetPackage(mapperPackage);
        sqlMapGeneratorConfiguration.setTargetProject(fileOutSrcDir);
        sqlMapGeneratorConfiguration.addProperty("enableSubPackages","true");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
        //javaClient 相关配置
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        javaClientGeneratorConfiguration.setTargetPackage(mapperInterfacePacke);
        javaClientGeneratorConfiguration.setTargetProject(fileOutSrcDir);
        javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
        //table 配置
        tableNames.forEach(tableName -> {
            TableConfiguration tableConfiguration = new TableConfiguration(context);
            tableConfiguration.setTableName(tableName);
            tableConfiguration.setDomainObjectName(StrUtils.tableConvertJavaClassName(tableName));
            tableConfiguration.setCountByExampleStatementEnabled(false);
            tableConfiguration.setUpdateByExampleStatementEnabled(false);
            tableConfiguration.setDeleteByExampleStatementEnabled(false);
            tableConfiguration.setSelectByExampleStatementEnabled(false);
            tableConfiguration.setSelectByExampleStatementEnabled(false);
            context.addTableConfiguration(tableConfiguration);
        });
        return context;
    }

    public void generateMyBatisCode() throws InvalidConfigurationException, InterruptedException, SQLException, IOException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        Configuration config = new Configuration();
        Context context = setContext();
        config.addContext(context);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}
