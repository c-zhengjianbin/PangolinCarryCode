package  ${ controllerPackageName };

import com.github.pagehelper.PageInfo;
import ${modelPackageName};
import ${servicePackageName};
import ${resultPackageName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${classVariateName}")
public class ${className} {

    @Autowired
    private ${serviceClassName} ${serviceClassVariateName};

    @GetMapping(value = "/getById")
    public Result getById(@RequestParam(value = "id") ${keyIdClass} id) {
        try {
            ${serviceClassVariateName}.getOneById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.wrap(Result.ERROR_CODE, e.getMessage());
        }
        return ResultWrap.ok();
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody ${modelClassName} ${classVariateName}) {
        try {
            ${serviceClassVariateName}.save(${classVariateName});
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.wrap(Result.ERROR_CODE, e.getMessage());
        }
        return ResultWrap.ok();
    }

    @PostMapping(value = "/modify")
    public Result modify(@RequestBody ${modelClassName} ${classVariateName}) {
        try {
            ${serviceClassVariateName}.modify(${classVariateName});
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.wrap(Result.ERROR_CODE, e.getMessage());
        }
        return ResultWrap.ok();
    }

    @PostMapping(value = "/delete")
    public Result delete(@RequestParam(value = "id") ${keyIdClass} id) {
        try {
            ${serviceClassVariateName}.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.wrap(Result.ERROR_CODE, e.getMessage());
        }
        return ResultWrap.ok();
    }

    @PostMapping(value = "/query")
    public Result queryUser(@RequestBody(required = false) ${modelClassName} ${classVariateName}) {
        try {
            PageInfo<${modelClassName}> pageInfo =new PageInfo<>(${serviceClassVariateName}.listObjects(${classVariateName}));
            return ResultWrap.wrap(Result.SUCCESS_CODE, Result.SUCCESS_MESSAGE, pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.wrap(Result.ERROR_CODE, e.getMessage());
        }
    }

}