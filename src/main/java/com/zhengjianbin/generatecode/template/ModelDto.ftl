package  ${ beanPackageName };

import lombok.Data;

@Data
public class ${className} {

<#list properties as pro>
    private ${pro.className} <@convertJavaField>${pro.fieldName}</@convertJavaField>
</#list>

}