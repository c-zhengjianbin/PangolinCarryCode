package  ${ servicePackageName };

import ${modelPackageName};
import java.util.List;

public interface ${className} {

    void save(${modelClassName} ${classVariateName});

    void delete(${keyIdClass} id);

    void modify(${modelClassName} ${classVariateName});

    ${modelClassName} getOneById(${keyIdClass} id);

    List<${modelClassName}> listObjects(${modelClassName} ${classVariateName});

}