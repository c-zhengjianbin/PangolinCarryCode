package  ${ beanPackageName };

import lombok.Data;
import java.util.Date;

@Data
public class ${className} {

<#list properties as pro>
    private ${pro.className} <@convertJavaField>${pro.fieldName}</@convertJavaField>;
</#list>

}