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
    public Wrapper getById(@RequestParam(value = "id") Integer id) {
        try {
            adminUserService.getOneById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        }
        return WrapMapper.ok();
    }

    @PostMapping(value = "/add")
    public Wrapper add(@RequestBody AdminUser adminUser) {
        try {
            adminUserService.save(adminUser);
        } catch (Exception e) {
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        }
        return WrapMapper.ok();
    }

    @PostMapping(value = "/modify")
    public Wrapper modify(@RequestBody AdminUser adminUser) {
        try {
            adminUserService.modify(adminUser);
        } catch (Exception e) {
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        }
        return WrapMapper.ok();
    }

    @PostMapping(value = "/delete")
    public Wrapper delete(@RequestParam(value = "id") Integer id) {
        try {
            adminUserService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        }
        return WrapMapper.ok();
    }

    @PostMapping(value = "/query")
    public Wrapper queryUser(@RequestBody(required = false) AdminUser adminUser) {
        try {
            PageInfo<AdminUser> pageInfo =new PageInfo<>(adminUserService.listObjects(adminUser));
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        }
    }

}