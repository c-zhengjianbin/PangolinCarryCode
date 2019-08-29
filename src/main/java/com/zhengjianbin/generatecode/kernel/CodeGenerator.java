package com.zhengjianbin.generatecode.kernel;

import com.zhengjianbin.generatecode.util.FileUtils;
import com.zhengjianbin.generatecode.util.MysqlFieldConvertJavaField;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * Created by zhengjianbin on 2019/8/29.
 */
public class CodeGenerator {

    private Configuration cfg;

    private String templateDirPath;

    private String outFileBasePath;

    public CodeGenerator(String templatePathDir, String outFileBasePath) {
        this.templateDirPath = templatePathDir;
        this.outFileBasePath = outFileBasePath;
        cfg = new Configuration();
        cfg.setDefaultEncoding("utf-8");
        try {
            cfg.setDirectoryForTemplateLoading(new File(templateDirPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg.setSharedVariable("convertJavaField", new MysqlFieldConvertJavaField());
    }

    public void generateModelFile(Map templateParameters, String templateFileName) throws IOException, TemplateException {
        Template template = cfg.getTemplate(templateFileName);
        String modelPath = outFileBasePath + File.separator + "model" + File.separator;
        FileUtils.checkAndMkdir(modelPath);
        String filePath = modelPath + templateParameters.get("className").toString() + ".java";
        FileOutputStream fos = new FileOutputStream(new File(filePath));
        template.process(templateParameters, new OutputStreamWriter(fos, "utf-8"));
        fos.flush();
    }

}
