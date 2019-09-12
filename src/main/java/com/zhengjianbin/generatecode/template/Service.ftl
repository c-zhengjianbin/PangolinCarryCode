package  ${ servicePackageName };

import ${modelPackageName};
import java.util.List;

public interface ${className} {

    void save(${modelClassName} ${classVariateName});

    void delete(Long id);

    void modify(${modelClassName} ${classVariateName});

    ${modelClassName} get${modelClassName}ById(Long id);

    List<${modelClassName}> listObjects(${modelClassName} ${classVariateName});

}