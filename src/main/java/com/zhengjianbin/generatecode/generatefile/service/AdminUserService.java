package  com.zhengjianbin.generatecode.generatefile.service;

import com.zhengjianbin.generatecode.generatefile.model.AdminUser;
import java.util.List;

public interface AdminUserService {

    void save(AdminUser adminUser);

    void delete(Integer id);

    void modify(AdminUser adminUser);

    AdminUser getOneById(Integer id);

    List<AdminUser> listObjects(AdminUser adminUser);

}