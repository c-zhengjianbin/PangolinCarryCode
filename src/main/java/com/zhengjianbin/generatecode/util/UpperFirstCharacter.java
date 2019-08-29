package com.zhengjianbin.generatecode.util;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Created by zhengjianbin on 2019/8/29.
 */
public class UpperFirstCharacter implements TemplateDirectiveModel {

    @Override
    public   void  execute(Environment env,
                           Map params, TemplateModel[] loopVars,
                           TemplateDirectiveBody body)
            throws TemplateException, IOException {
        // Check if no parameters were given:
        if  (!params.isEmpty()) {
            throw   new TemplateModelException(
                    "This directive doesn't allow parameters." );
        }
        if  (loopVars.length !=  0 ) {
            throw   new TemplateModelException(
                    "This directive doesn't allow loop variables." );
        }

        // If there is non-empty nested content:
        if  (body !=  null ) {
            // Executes the nested body. Same as <#nested> in FTL, except
            // that we use our own writer instead of the current output writer.
            body.render(new  UpperCaseFilterWriter(env.getOut()));
        } else  {
            throw   new  RuntimeException( "missing body" );
        }
    }

    /**
     * A {@link Writer} that transforms the character stream to upper case
     * and forwards it to another {@link Writer}.
     */
    private   static   class  UpperCaseFilterWriter  extends  Writer {

        private   final  Writer out;

        UpperCaseFilterWriter (Writer out) {
            this .out = out;
        }

        @Override
        public   void  write(char [] cbuf, int  off, int  len)
                throws  IOException {

            //如果发现下划线,采用驼峰命名模式,数组增删改难以处理,转化成list处理
            for (int i = 0; i < cbuf.length; i++) {
                if(i == 0){
                    cbuf[0] = Character.toUpperCase(cbuf[0]);
                    continue;
                }

                if(String.valueOf(cbuf[i]).equals("_")){
                    cbuf[i+1] = Character.toUpperCase(cbuf[i+1]);
                }
            }

            out.write(String.valueOf(cbuf).replace("_", "").trim());///把右边空格去掉
        }

        @Override
        public   void  flush()  throws  IOException {
            out.flush();
        }

        @Override
        public   void  close()  throws  IOException {
            out.close();
        }
    }

}
