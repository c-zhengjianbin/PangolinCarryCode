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
 * @author : zhengjianbin
 * @version: 1.0
 * @time : 2019/9/12 - 10:32 AM
 * @Param :
 * @function : 代码生成类
 */
public class CodeGenerator {

    private Configuration cfg;

    private String templateDirPath;

    private String outFileBasePath;

    private String modelFilePath;

    private String serviceFilePath;

    private String ControllerFilePath;

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

    /**
     * @author : zhengjianbin
     * @version: 1.0
     * @time : 2019/9/12 - 10:32 AM
     * @Param : templateParameters - 模板所需的参数
     *          templateFileName   - 模板名称，例如：ModelDto.ftl
     *          templateType - 模板类型，例如：model,service
     *          generatePath - 生成文件相对路径，例如：controller，server/impl
     * @function : 生成指定模板代码
     */
    public void generateModelFile(Map templateParameters, String templateFileName,
                                  String generatePath) throws IOException, TemplateException {
        Template template = cfg.getTemplate(templateFileName);
        String generateFilePath = outFileBasePath + File.separator + generatePath + File.separator;
        FileUtils.checkAndMkdir(generateFilePath);
        String filePath = generateFilePath + templateParameters.get("className").toString() + ".java";
        FileOutputStream fos = new FileOutputStream(new File(filePath));
        template.process(templateParameters, new OutputStreamWriter(fos, "utf-8"));
        fos.flush();
    }

}
