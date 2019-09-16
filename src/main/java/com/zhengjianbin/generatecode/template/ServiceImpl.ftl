package  ${ serviceImplPackageName };

import ${modelPackageName};
import ${servicePackageName};
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ${className} implements ${serviceClassName} {

    @Override
    public void save(${modelClassName} ${classVariateName}){

    }

    @Override
    public void delete(${keyIdClass} id){

    }

    @Override
    public void modify(${modelClassName} ${classVariateName}){

    }

    @Override
    public ${modelClassName} getOneById(${keyIdClass} id){

        return null;
    }

    @Override
    public List<${modelClassName}> listObjects(${modelClassName} ${classVariateName}){

        return null;
    }

}