package  com.zhengjianbin.generatecode.generatefile.controller;

import com.github.pagehelper.PageInfo;
import com.zhengjianbin.generatecode.generatefile.model.AdminUser;
import com.zhengjianbin.generatecode.generatefile.service.AdminUserService;
import com.zhengjianbin.generatecode.util.result.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminUser")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping(value = "/getById")
    public Result getById(@RequestParam(value = "id") Integer id) {
        try {
            adminUserService.getOneById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.wrap(Result.ERROR_CODE, e.getMessage());
        }
        return ResultWrap.ok();
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody AdminUser adminUser) {
        try {
            adminUserService.save(adminUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.wrap(Result.ERROR_CODE, e.getMessage());
        }
        return ResultWrap.ok();
    }

    @PostMapping(value = "/modify")
    public Result modify(@RequestBody AdminUser adminUser) {
        try {
            adminUserService.modify(adminUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.wrap(Result.ERROR_CODE, e.getMessage());
        }
        return ResultWrap.ok();
    }

    @PostMapping(value = "/delete")
    public Result delete(@RequestParam(value = "id") Integer id) {
        try {
            adminUserService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.wrap(Result.ERROR_CODE, e.getMessage());
        }
        return ResultWrap.ok();
    }

    @PostMapping(value = "/query")
    public Result queryUser(@RequestBody(required = false) AdminUser adminUser) {
        try {
            PageInfo<AdminUser> pageInfo =new PageInfo<>(adminUserService.listObjects(adminUser));
            return ResultWrap.wrap(Result.SUCCESS_CODE, Result.SUCCESS_MESSAGE, pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.wrap(Result.ERROR_CODE, e.getMessage());
        }
    }

}