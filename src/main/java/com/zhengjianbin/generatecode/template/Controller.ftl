package  ${ controllerPackageName };

import com.github.pagehelper.PageInfo;
import ${modelPackageName};
import ${servicePackageName};
import ${wrapPackageName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${classVariateName}")
public class ${className} {

    @Autowired
    private ${serviceClassName} ${serviceClassVariateName};

    @GetMapping(value = "/getById")
    public Wrapper getById(@RequestParam(value = "id") ${keyIdClass} id) {
        try {
            ${serviceClassVariateName}.getOneById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        }
        return WrapMapper.ok();
    }

    @PostMapping(value = "/add")
    public Wrapper add(@RequestBody ${modelClassName} ${classVariateName}) {
        try {
            ${serviceClassVariateName}.save(${classVariateName});
        } catch (Exception e) {
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        }
        return WrapMapper.ok();
    }

    @PostMapping(value = "/modify")
    public Wrapper modify(@RequestBody ${modelClassName} ${classVariateName}) {
        try {
            ${serviceClassVariateName}.modify(${classVariateName});
        } catch (Exception e) {
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        }
        return WrapMapper.ok();
    }

    @PostMapping(value = "/delete")
    public Wrapper delete(@RequestParam(value = "id") ${keyIdClass} id) {
        try {
            ${serviceClassVariateName}.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        }
        return WrapMapper.ok();
    }

    @PostMapping(value = "/query")
    public Wrapper queryUser(@RequestBody(required = false) ${modelClassName} ${classVariateName}) {
        try {
            PageInfo<${modelClassName}> pageInfo =new PageInfo<>(${serviceClassVariateName}.listObjects(${classVariateName}));
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        }
    }

}